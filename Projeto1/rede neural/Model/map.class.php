<?php




class Map{



    private $file;
    private $teamsName = array();
    private $judgesName = array();
    private $teamsCode = array();
    private $judgesCode = array();

    public function __construct(){


        $this->file = fopen("../crawler/2015BD.txt", "r");

        while(!feof($this->file)){


            $line = fgets($this->file);

            $line = explode("#",$line);

            $team = $line[0];

            if(!in_array($team, $this->teamsName)){
                array_push($this->teamsName, $team);
            }

           

        }
        
       



    }










    
}