/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Team;
import java.util.ArrayList;

/**
 *
 * @author diogo
 */
public class Controller {
    
    ArrayList<Team> listaTimes = new ArrayList<Team>();

    public ArrayList<Team> getListaTimes() {
        return listaTimes;
    }

    public void setListaTimes(ArrayList<Team> listaTimes) {
        this.listaTimes = listaTimes;
    }
    
    
      public double normalize(double num){
        
        double numWithSign = num;
  
        num = (num-1)/9;
        
        if(numWithSign<0 && num >0){
            num *= -1;
        }
        else if(numWithSign >0 && num<0){
            num*= -1;
        }
        
        return num;
    }

    
    
    
    public double retornaAcerto(String arquivo) throws FileNotFoundException, IOException{
        String line;
        String[] splitLine, splitLine2;
        double mediaErro, erroAtual, erroTotal = 0.0, countAcertos = 0.0;
        int countTotal = 0;
        
        
        File file = new File(arquivo);
            if(file.isFile()){
                BufferedReader br = new BufferedReader(new FileReader(file));
                do{
                    line = br.readLine();
                    
                    if(line != null){
                        splitLine = line.split("Error: ");
                        splitLine2 = splitLine[1].split(";");
                        splitLine2[0] = splitLine2[0].replace(',', '.');
                        erroAtual = Double.parseDouble(splitLine2[0]);
                        
                        if(erroAtual >= (-0.0500) && erroAtual <(0.0500)){
                            erroAtual = 0;
                        }
                        else{
                            erroAtual = 1;
                        }
                    
                        countAcertos = countAcertos + erroAtual;
                        countTotal++;
                    }  
                }while(line != null);
        
            }
        return countAcertos/((double)countTotal)*100;
    }

}
