

package View;

import Model.Supervisao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.neuroph.core.*;
import org.neuroph.core.learning.LearningRule;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.learning.SupervisedLearning;
import org.neuroph.nnet.*;
import org.neuroph.util.*;
import Controller.*;
import Model.*;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.neuroph.core.input.InputFunction;


        
        
        
public class Redeneural {

  
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        
        
        DataSet data = new DataSet(6);
        String[] paths = new String[1];
        paths[0] = "../BD_partidas.txt";
        RoundReader r = new RoundReader(paths);
        Controller c = new Controller();
        
        LinkedList<String> teams = r.getTeams();
        int round=1, index = 0;
        double atk = 0, def = 0;
        final double divNormScore = 10;
        
        
        
        
   
        
        Iterator it = teams.iterator();
        
        while(it.hasNext()){
            c.getListaTimes().add((new Team((String)it.next())));
        }
       
        
        for(int j = 0; j<r.returnNumberOfAvaliableRounds(); j++){
            it = c.getListaTimes().iterator();
            
            while(it.hasNext()){
                Team team = (Team)it.next();
                double[] atkDef = r.returnAtkDefScore(team.getName(), j+1);
                
                if(atkDef != null){
                    
                    team.setMatchCount(team.getMatchCount()+1);
                    team.addAttackValue(atkDef[0]);
                    team.addDefenseValue(atkDef[1]);
                    
                    
                    double[] row = new double[6];
                    
                    row[0] = (double) team.getID();
                    row[1] = team.getAttackValue()/divNormScore;
                    row[2] = team.getDefenseValue()/divNormScore;
                    double[] last = r.returnAtkDefScore(team.getName(), j+1);
                   
                    if(last != null){
                        /*String[] lastSplit = last.split("#");
                        if(lastSplit[0].equals(team.getName())){
                            atk = Double.parseDouble(lastSplit[2]);
                            def = Double.parseDouble(lastSplit[3]);
                        }
                        else{
                            atk = Double.parseDouble(lastSplit[3]);
                            def = Double.parseDouble(lastSplit[2]);
                        }
                        */
                        atk = last[0];
                        def = last[1];
                        
                        row[3] = c.normalize((double)(1-((atk+1)/(def+1))));
                        
                        
                    }
                    else{
                        row[3] = 0.0;
                        
                    }
                                       
                    row[4] = r.isHome(team.getName(), j+1);
                    row[5] = atkDef[0]/divNormScore;
                    
                    data.addRow(row);
                    
                }
                
            }
            
        }
            
        
        File f = new File("ultimosResultados.txt");
        FileWriter fw = new FileWriter(f);
        PrintWriter pF = new PrintWriter(fw);
        
        it = c.getListaTimes().iterator();
        
        while(it.hasNext()){
            
            Team t = (Team)it.next();
            
            String line = "";
            
            double atkTeam = t.getAttackValue()/divNormScore;
            double defTeam = t.getDefenseValue()/divNormScore;
            
            
            line += t.getName() + ";";
            line += atkTeam +";";
            line += defTeam;
            
            pF.println(line);
            
            
        }
        
        pF.close();
        fw.close();
        

            /*LOGICA DE TREINAR A REDE NEURAL*/
            
            
        //Classe de função de ativação    
        InputFunction function = new InputFunction(){
            
            
            @Override
            public double getOutput(List<Connection> inputConnections) {
                
                Iterator it = inputConnections.iterator();
                double result=0;
                
                
                
                while(it.hasNext()){
                    
                    Connection c = (Connection)it.next();
                    
                    double input =c.getInput();
                    double weight = c.getWeight().getValue();
                    result += input*weight;
                    
                    Neuron n = c.getFromNeuron();
                    n.setOutput(result);
                    /*
                    Neuron n = c.getToNeuron();               
                    List<Connection> n1 = n.getInputConnections();                  
                    System.out.println(n1.get(0).getWeightedInput());
                    */
                    
                    
                    
                }
                
                
                
                return result; 
            }
        };
           
        
        
        
        
        
        
                
       
       File file = new File("BDRedeNeural.txt"); 
       FileWriter fileW = new FileWriter(file);
       PrintWriter write = new PrintWriter(fileW);
      
        
       
       for(int i=0; i<data.size(); i++){
           
           DataSetRow row = data.get(i);
           double[] values = row.getInput();
           String line = "";
           
           for(int j =1; j<values.length-1; j++){
               
               
               line += values[j] + ";";
               
           }
           
           line += values[5];
           
         
           
           
           write.println(line);

       }
       
       write.close();
       fileW.close();
       
       
        
        
        
        
        
    }
    
    
  
    
}
