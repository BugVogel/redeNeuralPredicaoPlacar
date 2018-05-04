
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
import java.util.Iterator;
import java.util.LinkedList;


        
        
        
public class Redeneural {

  
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        
        
        DataSet data = new DataSet(5);
        String[] paths = new String[2];
        paths[0] = "../2015BD.txt";
        paths[1] = "../2016BD.txt";
        RoundReader r = new RoundReader(paths);
        
        LinkedList<String> teams = r.getTeams();
        
        Iterator i =teams.iterator();
        
        while(i.hasNext()){
        
            
            String team = (String)i.next();
            
            int[][] scores = r.returnAllAtkDefScores(team);
            double homeMatchers = r.homeMatch(team);
            double[] row = new double[5];
            double wins=0;
            
            //Soma todos os pontos de ataque e defesa
            for(int a =0; a<scores.length; a++){
                
                
                row[0] = data.size();
                row[1] += scores[a][0];
                row[2] += scores[a][1];
                if(scores[a][0] > scores[a][1] ){
                    wins++;
                }
                
                
    
            }   
            row[1] /= scores.length; //media de gols feitos por partida
            row[2] /= scores.length; //media de gols recebidos por partida
            row[3] = wins/scores.length; //porcentagem de vitórias por partida
            row[4] = homeMatchers/scores.length; //porcentagem de partidas em casa por partida
            
            row[1] = Math.round(row[1]);
            row[2] = Math.round(row[2]);

            data.addRow(row);
            
            
            System.out.println(row[1]);
            
           
        }
        
        NeuralNetwork web = new NeuralNetwork();
        Supervisao s = new Supervisao();
        
        
        Layer layer1  = new Layer();
        
        
        

        
        
        
        
        
        
        Weight w = new Weight();
        w.setValue(0.5);
        
        
        Neuron neuron1l1 = new Neuron();
        Neuron neuron2l1 = new Neuron();
        Neuron neuron3l1 = new Neuron();
        
        Neuron neuron1l2 = new Neuron();
        
        Connection cN1l1 = new Connection(neuron1l1,neuron1l2);
        Connection cN2l1 = new Connection(neuron2l1,neuron1l2);
        Connection cN3l1 = new Connection(neuron3l1,neuron1l2);
        
        
        
        
       
       
        

      
        
        
        
       
       
        
        
        
       
      
        
        
        cN1l1.setWeight(w);
        
        
       
       
        
       
        
        
        
    }
    
}
