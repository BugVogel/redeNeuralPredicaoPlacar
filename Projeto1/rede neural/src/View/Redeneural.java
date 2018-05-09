

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
        Controller c = new Controller();
        
        LinkedList<String> teams = r.getTeams();
        Iterator it =teams.iterator();
        int round=1, index = 0;
        
        
        for(int j = 0; j<1/*NUMERO DE RODADAS*/; j++){
            while(it.hasNext()){

                String team = (String)it.next();
                Team newTeam = new Team(team);

                double[] row = new double[4];
                int atk = 0, def = 0;

                int[][] placares = r.returnAllAtkDefScores(team);

                newTeam.addAttackValue(placares[j][0]);
                newTeam.addDefenseValue(placares[j][1]);
            }

            /*LOGICA DE TREINAR A REDE NEURAL*/
            
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
