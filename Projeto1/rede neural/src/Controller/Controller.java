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


}