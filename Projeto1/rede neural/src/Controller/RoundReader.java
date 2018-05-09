
package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/* 
  Esta classe tem como funcionalidade ler os arquivos extraídos com as rodadas, coloca-los na memória e retorná-los.
  Algumas de suas funcionalidades incluem ler os arquivos, separar as rodadas e instanciar os objetos dos times.
 */
public class RoundReader {
    
    private String[] paths; //Essa variável guarda todos os caminhos dos arquivos .txt. Note que essa informação deve
                            //Ser apresentada em ordem cronológica, do 0 ao X.
    
    public RoundReader(String[] paths){
        this.paths = paths;
    }
    
    /*Função que retorna o número de partidas em que o nome de um time aparece, dado todo o banco de informações disponível.
      Esta informação é útil para saber a quantidade de saltos que é necessário fazer para obter todas as partidas em que o time participa.*/
    
    public int teamMatchAmount(String teamName) throws FileNotFoundException, IOException{
        String currentPath;
        String line;
        int count = 0;
        
        for(int i = 0; i<paths.length; i++){
            currentPath = paths[i];
            line = "";
            
            File file = new File(currentPath);
            if(file.isFile()){
                BufferedReader br = new BufferedReader(new FileReader(currentPath));
                do{
                    line = br.readLine();
                    if(line != null){
                        String[] lineSplit = line.split("#");
                        if(lineSplit[0].equals(teamName) || lineSplit[1].equals(teamName)){
                            count++;
                        }
                    }
                }while(line != null);  
                br.close();
            }
            else{
                System.out.println("Erro no endereçamento dos caminhos dos arquivos de informações.");
            }
        }
        return count;
    }
    
    /*Retorna a quantidade de vezes que um time A enfrentou um time B*/
    
    public int teamVersusAmount(String teamName1, String teamName2) throws FileNotFoundException, IOException{
        String currentPath;
        String line;
        int count = 0;
        
        for(int i = 0; i<paths.length; i++){
            currentPath = paths[i];
            line = "";
            
            File file = new File(currentPath);
            if(file.isFile()){
                BufferedReader br = new BufferedReader(new FileReader(currentPath));
                do{
                    line = br.readLine();
                    if(line != null){
                        String[] lineSplit = line.split("#");
                        if(lineSplit[0].equals(teamName1) || lineSplit[1].equals(teamName1)){
                            if(lineSplit[0].equals(teamName2) || lineSplit[1].equals(teamName2)){
                                count++;
                            }
                        }
                    }
                }while(line != null);  
                br.close();
            }
            else{
                System.out.println("Erro no endereçamento dos caminhos dos arquivos de informações.");
            }
        }
        return count;
    }
    
    /*Retorna uma string com as informações da partida referente à rodada desejada.*/
    
    public String returnMatch(String teamName, int round) throws FileNotFoundException, IOException{
        String currentPath;
        String line;
        int count = 0;
        
        for(int i = 0; i<paths.length; i++){
            currentPath = paths[i];
            line = "";
            
            File file = new File(currentPath);
            if(file.isFile()){
                BufferedReader br = new BufferedReader(new FileReader(currentPath));
                do{
                    line = br.readLine();
                    if(line != null){
                        String[] lineSplit = line.split("#");
                        if(lineSplit[0].equals(teamName) || lineSplit[1].equals(teamName)){
                            count++;
                        }
                        if(count == round){
                            return line;
                        }
                    }
                }while(line != null);  
                br.close();
            }
            else{
                System.out.println("Erro no endereçamento dos caminhos dos arquivos de informações.");
            }
        }
        return null; //Significa que não foi encontrado uma partida com a rodada denominada ou com o nome do time desejado
    }
    
    /*Retorna um vetor de String com todas as ocorrências de partidas de um único time*/
    
    public String[] returnAllMatches(String teamName) throws FileNotFoundException, IOException{
        String currentPath;
        String line;
        String[] returning = new String[this.teamMatchAmount(teamName)];
        int count = 0;
        
        for(int i = 0; i<paths.length; i++){
            currentPath = paths[i];
            line = "";
            
            File file = new File(currentPath);
            if(file.isFile()){
                BufferedReader br = new BufferedReader(new FileReader(currentPath));
                do{
                    line = br.readLine();
                    if(line != null){
                        String[] lineSplit = line.split("#");
                        if(lineSplit[0].equals(teamName) || lineSplit[1].equals(teamName)){
                            returning[count++] = line;
                        }
                    }
                }while(line != null);  
                br.close();
            }
            else{
                System.out.println("Erro no endereçamento dos caminhos dos arquivos de informações.");
            }
            
        }
        return returning; //Significa que não foi encontrado uma partida com a rodada denominada ou com o nome do time desejado
    }
    
    /*Função que retorna o score de ataque na posição 0 e de defesa na posição 1 de um time, para uma determinada rodada.
      Essa função é útil para obter rapidamente os dados necessários para treinar a rede neural.*/
    
    public int[] returnAtkDefScore(String teamName, int round) throws FileNotFoundException, IOException{
        String currentPath;
        String line;
        int count = 0;
        
        for(int i = 0; i<paths.length; i++){
            currentPath = paths[i];
            line = "";
            
            File file = new File(currentPath);
            if(file.isFile()){
                BufferedReader br = new BufferedReader(new FileReader(currentPath));
                do{
                    line = br.readLine();
                    if(line != null){
                        String[] lineSplit = line.split("#");
                        if(lineSplit[0].equals(teamName) || lineSplit[1].equals(teamName)){
                            count++;
                        }
                        if(count == round){

                            int[] returning = new int[2];
                            
                            if(lineSplit[0].equals(teamName) ){
                                returning[0] = Integer.parseInt(lineSplit[2]);
                                returning[1] = Integer.parseInt(lineSplit[3]);
                            }
                            
                            else if(lineSplit[1].equals(teamName)){
                                returning[1] = Integer.parseInt(lineSplit[2]);
                                returning[0] = Integer.parseInt(lineSplit[3]);
                            }
                            
                            return returning;
                        }
                    }
                }while(line != null);  
                br.close();
            }
            else{
                System.out.println("Erro no endereçamento dos caminhos dos arquivos de informações.");
            }
        }
        return null; //Significa que não foi encontrado uma partida com a rodada denominada ou com o nome do time desejado
    }
    
    
    /*Retorna uma matriz com todos os scores de gols feitos e recebidos de um time*/
    
    public int[][] returnAllAtkDefScores(String teamName) throws FileNotFoundException, IOException{
        String currentPath;
        String line;
        int count = 0;
        int[][] returning = new int[this.teamMatchAmount(teamName)][2];
        
        for(int i = 0; i<paths.length; i++){
            currentPath = paths[i];
            line = "";
            
            File file = new File(currentPath);
            if(file.isFile()){
                BufferedReader br = new BufferedReader(new FileReader(currentPath));
                do{
                    line = br.readLine();
                    if(line != null){
                        String[] lineSplit = line.split("#");
                        if(lineSplit[0].equals(teamName) ){
                            returning[count][0] = Integer.parseInt(lineSplit[2]);
                            returning[count][1] = Integer.parseInt(lineSplit[3]);
                            count++;
                        }
                        if(lineSplit[1].equals(teamName)){
                            returning[count][1] = Integer.parseInt(lineSplit[2]);
                            returning[count][0] = Integer.parseInt(lineSplit[3]);
                            count++;
                            
                        }
                        
                    }
                }while(line != null);  
                br.close();
            }
            else{
                System.out.println("Erro no endereçamento dos caminhos dos arquivos de informações.");
            }
            
        }
        return returning; //Significa que não foi encontrado uma partida com a rodada denominada ou com o nome do time desejado
    }
    
    
     /*Retorna um vetor de strings com todas as ocorrências onde um time enfrenta o outro*/
    
    public String[] returnAllVersusMatches(String teamName1, String teamName2) throws FileNotFoundException, IOException{
        String currentPath;
        String line;
        int count = 0;
        String[] returning = new String[this.teamVersusAmount(teamName1, teamName2)];
        
        
        for(int i = 0; i<paths.length; i++){
            currentPath = paths[i];
            line = "";
            
            File file = new File(currentPath);
            if(file.isFile()){
                BufferedReader br = new BufferedReader(new FileReader(currentPath));
                do{
                    line = br.readLine();
                    if(line != null){
                        String[] lineSplit = line.split("#");
                        if(lineSplit[0].equals(teamName1) || lineSplit[1].equals(teamName1)){
                            if(lineSplit[0].equals(teamName2) || lineSplit[1].equals(teamName2)){
                                returning[count] = line;

                                count++;
                            }    
                            //if(count == returning.length-1){
                             //   return returning;
                            //}
                        }
                    }
                }while(line != null);  
                br.close();
            }
            else{
                System.out.println("Erro no endereçamento dos caminhos dos arquivos de informações.");
            }
            
        }
        return returning; //Significa que não foi encontrado uma partida com a rodada denominada ou com o nome do time desejado
    }
    
    
    //Retorna um array de string com todos os times existentes do dataset
    
    public LinkedList<String> getTeams() throws FileNotFoundException, IOException{
        
        
        
        LinkedList<String> teams = new LinkedList<String>();
        String currentPath;
        String line;
        
        
        for(int i=0; i< paths.length; i++){
            
            currentPath = paths[i];
            line = "";
            File file = new File(currentPath);
            
            if(file.isFile()){
            
                 BufferedReader br = new BufferedReader(new FileReader(currentPath));
                 
                 while(br.ready()){
                     
                     line = br.readLine();
                     
                     String[] lineData = line.split("#");
                     String team1 = lineData[0];
                     String team2 = lineData[1];

                     
                     if(!teams.contains(team1)){
                         teams.add(team1);
                         
                     }
                     
                     if(!teams.contains(team2)){
                         teams.add(team2);
                     }
    
                 }

            }
            else{
                System.out.println("Erro no endereçamento dos caminhos dos arquivos de informações.");
            }
             
        }
        
        
        
        return teams;
    }
    
    
    
    
    
    public int homeAllMatch(String teamName) throws IOException{
        
        
        String[] lines = this.returnAllMatches(teamName);
        int contHome=0;
        
        for(int i =0; i <lines.length; i++){
            String[] currentSplit = lines[i].split("#");
            if(Integer.parseInt(currentSplit[5]) == 1){
                contHome++;
            }
        }
        return contHome;
    }
    
    
  
    
    
    
    public int isHome(String teamName, int round) throws IOException{
        
        
        String line = this.returnMatch(teamName, round);
        
        String[] lineSplit = line.split("#");
        
        
        if(lineSplit[5].equals("undefined"))
            return 0;
        
        else if(Integer.parseInt(lineSplit[5]) == 1)
            return 1;
        else
            return 0;
        
    }
    
     public int[][] returnAllAtkDefUntilRound(String teamName, int round) throws FileNotFoundException, IOException{
        String currentPath;
        String line;
        int count = 0;
        int[][] returning = new int[this.teamMatchAmount(teamName)][2];
        
        for(int i = 0; i<paths.length; i++){
            currentPath = paths[i];
            line = "";
            
            File file = new File(currentPath);
            if(file.isFile()){
                BufferedReader br = new BufferedReader(new FileReader(currentPath));
                do{
                    line = br.readLine();
                    if(line != null){
                        String[] lineSplit = line.split("#");
                        if(lineSplit[0].equals(teamName) ){
                            returning[count][0] = Integer.parseInt(lineSplit[2]);
                            returning[count][1] = Integer.parseInt(lineSplit[3]);
                            count++;
                        }
                        if(lineSplit[1].equals(teamName)){
                            returning[count][1] = Integer.parseInt(lineSplit[2]);
                            returning[count][0] = Integer.parseInt(lineSplit[3]);
                            count++;
                            
                        }
                        
                    }
                }while(line != null || count<round);  
                br.close();
                
                if(count == round){
                    return returning;
                }
            }
            else{
                System.out.println("Erro no endereçamento dos caminhos dos arquivos de informações.");
            }
            
        }
        return returning; //Significa que não foi encontrado uma partida com a rodada denominada ou com o nome do time desejado
    }
     
     
     /*Método que retorna a vantagem (score/gols recebidos) de um time contra o outro até uma certa rodada
      Caso o parâmetro round seja 0, retorna a vantagm até a última rodada possível*/
    
    public double returnAdvantageUntilRound(String team1, int round) throws IOException{
        
        
        
        String line = this.returnMatch(team1, round);
        String lineSplit[] = line.split("#");
        String adversary = "";
        int i = 0;
        double atk = 0, def = 0;
       
        if(lineSplit[0].equals(team1)){
            adversary = lineSplit[1];
        }
        else if(lineSplit[1].equals(team1)){
            adversary = lineSplit[0];
        }
        
        String[] matches = this.returnAllVersusMatches(team1, adversary);
        
        if(matches.length < round){
            System.out.println("Round inserido maior que o possivel."+round+" "+matches.length);
            return 0;
        }
        
        if(round == 0){
            round = matches.length;
        }
        
        
        for(i = 0; line.equals(matches[i]); i++){
            lineSplit = matches[i].split("#");    
            System.out.println("i = "+i);
            if(lineSplit[0].equals(team1)){
                atk = atk+(Double.parseDouble(lineSplit[2]));
                System.out.println("Ataque: "+atk);
                def = def+(Double.parseDouble(lineSplit[3]));
                System.out.println("Defesa: "+def);
            }
            else if(lineSplit[1].equals(team1)){
                atk = atk+(Double.parseDouble(lineSplit[3]));
                System.out.println("Ataque: "+atk);
                def = def+(Double.parseDouble(lineSplit[2]));
                System.out.println("Defesa: "+def);
            } 
          
        }

        double retorno = (atk+1)/(def+1);
        retorno = 1 - retorno;
        return retorno;
    }

     
     public String returnLastMatch(String team1, int round) throws IOException{
        
        String line = this.returnMatch(team1, round);
        String lineSplit[] = line.split("#");
        String adversary = "";
        int num_partida = 0;
        double atk = 0.0, def = 0.0;
        
        if(lineSplit[0].equals(team1)){
            adversary = lineSplit[1];
        }
        else if(lineSplit[1].equals(team1)){
            adversary = lineSplit[0];
        }
       
        String[] matches = this.returnAllVersusMatches(team1, adversary);
       
        for(int i = 0; i<matches.length; i++){
            String currentLine = matches[i];
            if(matches[i].equals(line)){
                num_partida = i;
                System.out.println("Esta é a "+(num_partida+1)+"a partida");
            }
        }
        
        if(num_partida > 1){
            return matches[num_partida];
        }
        else{
            return null;
        }
        
    }
     
    
}
 
