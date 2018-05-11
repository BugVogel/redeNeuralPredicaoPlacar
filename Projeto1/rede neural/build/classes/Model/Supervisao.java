
package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.neuroph.core.Connection;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.core.Weight;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.learning.LearningRule;
import org.neuroph.core.learning.SupervisedLearning;

public class Supervisao  extends  LearningRule{

    
    
    @Override
    public void learn(DataSet trainingSet) {

               
           NeuralNetwork web = super.getNeuralNetwork();
           ArrayList<Neuron> neurons = (ArrayList<Neuron>)web.getInputNeurons();
           
           
           Iterator it = neurons.iterator();
           
           Neuron atkScore = (Neuron) it.next();
           Neuron defScore = (Neuron) it.next();
           Neuron advantage = (Neuron) it.next();
           Neuron homeAdvantage = (Neuron) it.next();
           DataSetRow row = trainingSet.getRowAt(0);
           double[] values = row.getInput();
           
           atkScore.setOutput(values[0]);
           defScore.setOutput(values[1]);
           advantage.setOutput(values[2]);
           homeAdvantage.setOutput(values[3]);
           
           
           web.calculate();
          
          
          
           double[] outputs = web.getOutput();
           
           System.out.println();
           
           
        
    }



}
    

