<?php

require 'crawler.php';


$crawler = new Crawler('https://www.cbf.com.br/competicoes/brasileiro-serie-a/escala-de-arbitragem/2014#.WttXrC7wbIU');


$teams1 = $crawler->getTeam1();
$teams2 = $crawler->getTeam2();
$score = $crawler->getPoints();
$judges = $crawler->getJudge();
$stadiums = $crawler->getStadium();


for($i=0; $i<sizeof($teams1);$i++){

    $points = explode("X", $score[$i]);
    $outOrHome = 'undefined';

    
    
    $stateTeam1 = explode("-",$teams1[$i]);  
    $team1 = $stateTeam1[0]; 
    $team1 = str_replace(" ","",$team1);
    $stateTeam1 = $stateTeam1[1];
    $stateTeam1 = str_replace(" ", "", $stateTeam1);

   
     
    $judges[$i]=str_replace("						","", $judges[$i]);
    $judges[$i]=str_replace("					","",$judges[$i]);
    

    $stateTeam2 = explode('-',$teams2[$i]);
    $team2 = $stateTeam2[0];
    $team2 = str_replace(" ", "", $team2);
    $stateTeam2 = $stateTeam2[1];
    $stateTeam2 = str_replace(" ","",$stateTeam2);

    $stateStadium = explode('-', $stadiums[$i]);
    $stadium = $stateStadium[0];
    $stadium = explode('>',$stadium);
    $stadium = $stadium[sizeof($stadium)-1];
    $stateStadium = $stateStadium[sizeof($stateStadium)-1];
    $stadium = str_replace(" ","", $stadium);
    $stateStadium = str_replace("					","",$stateStadium);
    
    

    if($stateTeam1 != $stateTeam2){
           
        

        if($stateStadium == $stateTeam1){
            $outOrHome = '1';
            
        }
        else if($stateTeam2 == $stateStadium){
            $outOrHome = '0';  
        }  

    }
    else{

           $pointer = fopen('mapeamentoCASAFORA.txt','r'); 

           while(!feof($pointer)){

                $line = fgets($pointer);
                $data = explode("#", $line);
                

                if($team1 == $data[0]){

                    if($stadium == $data[1]){
                        $outOrHome = '1';
                    }
                }


                if($team2 == $data[0]){

                    if($stadium == $data[1]){
                        $outOrHome = '0';
                    }
                }

           }


           fclose($pointer);

    }


    $game = $teams1[$i]. "#".$teams2[$i] ."#". $points[0] . "#". $points[1] ."#". $judges[$i] . "#" .$outOrHome. PHP_EOL ;

    
    
    file_put_contents("Data.txt",$game, FILE_APPEND);
}

