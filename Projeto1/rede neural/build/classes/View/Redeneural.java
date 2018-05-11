

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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.neuroph.core.input.InputFunction;


        
        
        
public class Redeneural {

  
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        
        
        DataSet data = new DataSet(7);
        String[] paths = new String[1];
        paths[0] = "../BD_partidas.txt";
        RoundReader r = new RoundReader(paths);
        Controller c = new Controller();
        
        LinkedList<String> teams = r.getTeams();
        int round=1, index = 0;
        double atk = 0, def = 0;
        
   
        
        Iterator it = teams.iterator();
        
        while(it.hasNext()){
            c.getListaTimes().add((new Team((String)it.next())));
        }
       
        
        for(int j = 0; j<r.returnNumberOfAvaliableRounds(); j++){
            it = c.getListaTimes().iterator();
            
            while(it.hasNext()){
                Team team = (Team)it.next();
                int[] atkDef = r.returnAtkDefScore(team.getName(), j+1);
                
                if(atkDef != null){
                    team.addAttackValue(atkDef[0]);
                    team.addDefenseValue(atkDef[1]);
                    team.setMatchCount(team.getMatchCount()+1);
                    
                    double[] row = new double[7];
                    
                    row[0] = (double) team.getID();
                    row[1] = team.getAttackValue();
                    row[2] = team.getDefenseValue();
                    String last = r.returnLastMatch(team.getName(), j+1);
                   
                    if(last != null){
                        String[] lastSplit = last.split("#");
                        if(lastSplit[0].equals(team.getName())){
                            atk = Double.parseDouble(lastSplit[2]);
                            def = Double.parseDouble(lastSplit[3]);
                        }
                        else{
                            atk = Double.parseDouble(lastSplit[3]);
                            def = Double.parseDouble(lastSplit[2]);
                        }
                        
                        row[3] = (double)(1-((atk+1)/(def+1)));
                        
                       
                    }
                    else{
                        row[3] = 0.0;
                    }
                                        
                    row[4] = r.isHome(team.getName(), j+1);
                    row[5] = atkDef[0];
                    row[6] = atkDef[1];
                    
                    data.addRow(row);
                    
                }
                
            }
            
        }
            

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
            
            
            
           
        
        NeuralNetwork web = new NeuralNetwork();
        Supervisao s = new Supervisao();
        
        
        //Camada intermediaria
        Layer layer2 = new Layer();
        
        //Neuronios intermediarios
        Neuron scoreAtk = new Neuron();
        Neuron scoreDef = new Neuron();
        Neuron advantage = new Neuron();
        Neuron homeAdvantage = new Neuron();

        scoreAtk.setInputFunction(function);
        scoreDef.setInputFunction(function);
        advantage.setInputFunction(function);
        homeAdvantage.setInputFunction(function);
        
        /*
        layer2.addNeuron(scoreAtk);
        layer2.addNeuron(scoreDef);
        layer2.addNeuron(advantage);
        layer2.addNeuron(homeAdvantage);
        */
        
        
        //Camada de entrada
        Layer layer1  = new Layer();
        
        Neuron input1 = new Neuron();
        Neuron input2  = new Neuron();
        Neuron input3 = new Neuron();
        Neuron input4 = new Neuron();
        
        input1.setInputFunction(function);
        input2.setInputFunction(function);
        input3.setInputFunction(function);
        input4.setInputFunction(function);
        
        //Neuronios de entrada
        /*layer1.addNeuron(input1);
        layer1.addNeuron(input2);
        layer1.addNeuron(input3);
        layer1.addNeuron(input4);*/
        
        ArrayList<Neuron> listInput = new ArrayList<Neuron>();
        
        listInput.add(input1);
        listInput.add(input2);
        listInput.add(input3);
        listInput.add(input4);
        
        web.setInputNeurons(listInput);

        
        //Conexões entre primeira e segunda camada com pesos
        /*
        web.createConnection(input1, scoreAtk, 0.7);
        web.createConnection(input2, scoreDef, 0.3);
        web.createConnection(input3, advantage, 0.6);
        web.createConnection(input4, homeAdvantage, 0.4);
        */
        scoreAtk.addInputConnection(input1, 0.7);
        scoreDef.addInputConnection(input2, 0.3);
        advantage.addInputConnection(input3, 0.6);
        homeAdvantage.addInputConnection(input4, 0.4);
        
        

        //Camada final
        Layer layer3 = new Layer();
        
        //Neuronios finais
        Neuron neuronFinal = new Neuron();
        //layer3.addNeuron(neuronFinal);
        
        neuronFinal.setInputFunction(function);
        
       /* web.createConnection(scoreAtk, neuronFinal, 0.5);
        web.createConnection(scoreDef, neuronFinal, 0.5);
        web.createConnection(advantage, neuronFinal, 0.5);
        web.createConnection(homeAdvantage, neuronFinal, 0.5);
        */
        neuronFinal.addInputConnection(scoreAtk, 0.5);
        neuronFinal.addInputConnection(scoreDef, 0.5);
        neuronFinal.addInputConnection(advantage, 0.5);
        neuronFinal.addInputConnection(homeAdvantage, 0.5);

        
        
        ArrayList<Neuron> listOutput = new ArrayList<Neuron>();
        listOutput.add(neuronFinal);
        web.setOutputNeurons(listOutput);
        
        
        
        
        web.learn(data, s);
        
        
    }
    
}
