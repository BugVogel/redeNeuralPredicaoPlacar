
package View;

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

        
        
        
public class Redeneural {

  
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        
        
        DataSet data = new DataSet(0);
        
        
        File dataBase = new File("../2015BD.txt");
        FileReader textData = new FileReader(dataBase);
        BufferedReader b = new BufferedReader(textData);
        
        while(b.ready()){
            
            String line = b.readLine();

            String[] row = 
        
        
        
        }
        
        b.close();
        textData.close();
        
        
        
        
        
        Weight w = new Weight();
        w.setValue(0.5);
        
        
        Neuron neuron1l1 = new Neuron();
        Neuron neuron2l1 = new Neuron();
        Neuron neuron3l1 = new Neuron();
        
        Neuron neuron1l2 = new Neuron();
        
        Connection cN1l1 = new Connection(neuron1l1,neuron1l2);
        Connection cN2l1 = new Connection(neuron2l1,neuron1l2);
        Connection cN3l1 = new Connection(neuron3l1,neuron1l2);
        
        Layer layer1  = new Layer();
        
        
       
       
        

        NeuralNetwork rede = new NeuralNetwork();
        
        
        
       
       
        
        Supervisao s = new Supervisao();
        s.setNeuralNetwork(rede);
       
       
        
        
        cN1l1.setWeight(w);
        
        
       
       
        
       
        
        
        
    }
    
}
