<?php


require 'simple_html_dom.php';

class Crawler {


private $html ='';

 function __construct($url){

       $this->html = file_get_html($url);

     


      
      
       
  }





  public function getTeam1(){

    

    $result = array();
    
    preg_match_all("/<div class=\"col-md-4 fittext game-team-1 cell-center-middle\">(.*?)<\/div>/",$this->html,$result);
    
    $result = $result[0];

    for($i=0; $i<sizeof($result); $i++){

        $result[$i] = str_replace(" ","",$result[$i]);
        $result[$i] = str_replace("\\n","",$result[$i]);
        $result[$i] = str_replace("<divclass=\"col-md-4fittextgame-team-1cell-center-middle\">		<span>","", $result[$i]);
        $result[$i] = str_replace("</span>	</div>","", $result[$i]);
        

    }

    return $result;


   
    

  }
  
  public function getTeam2(){



    preg_match_all("/<div class=\"col-md-4 fittext game-team-2 cell-center-middle\">(.*?)<\/div>/",$this->html,$result);

    $result = $result[0];

    for($i=0; $i<sizeof($result); $i++){

        $result[$i] = str_replace(" ", "",$result[$i]);
        $result[$i] = str_replace("\\n","",$result[$i]);
        $result[$i] = str_replace("<divclass=\"col-md-4fittextgame-team-2cell-center-middle\">		<span>","", $result[$i]);
        $result[$i] = str_replace("</span>	</div>","", $result[$i]);
        
       

    }

    return $result;

  }



  public function getPoints(){


    preg_match_all("/<div class=\"col-md-2 game-score cell-center-middle\">(.*?)<\/div>/",$this->html,$result);

    $result = $result[0];

    for($i=0; $i<sizeof($result); $i++){

        $result[$i] = str_replace(" ", "",$result[$i]);
        $result[$i] = str_replace("<divclass=\"col-md-2game-scorecell-center-middle\">		<span>","", $result[$i]);
        $result[$i] = str_replace("</span>	</div>","", $result[$i]);
        

    }

    return $result;

  }


  public function getJudge(){


    preg_match_all("/<a href=\".*?\" class=\"fpopup-1020-600 clean\" target=\"_blank\">(.*?)<\/a>/",$this->html,$result);

    $result = $result[1];

    for($i=0; $i<sizeof($result); $i++){

        $result[$i] = str_replace(" ", "",$result[$i]);
        $result[$i] = str_replace("<ahref=\"(.*?)\"class=\"fpopup-1020-600clean\"target=\"_blank\">","", $result[$i]);
        $result[$i] = str_replace("</span>	</div>","", $result[$i]);
        
        

    }

    return $result;

    
  }


  public function getStadium(){

    preg_match_all("/<div class=\"col-md-3 full-game-location cell-center-middle\">(.*?)<\/div>/",$this->html,$result);

    $result = $result[0];

    for($i=0; $i<sizeof($result); $i++){

        $result[$i] = str_replace(" ", "",$result[$i]);
        $result[$i] = str_replace("\\n","",$result[$i]);
        $result[$i] = str_replace("<divclass=\"col-md-3full-game-locationcell-center-middle\">","", $result[$i]);
        $result[$i] = str_replace("<span>","", $result[$i]);
        $result[$i] = str_replace("</span>","", $result[$i]);
        $result[$i] = str_replace("</div>","", $result[$i]);
       

    }

    return $result;




  }




  


}






