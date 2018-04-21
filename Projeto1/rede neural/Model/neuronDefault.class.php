

<?php




class NeuronDefault {



    private $score1;
    private $score2;
    private $place;
    private $p;
    private $W = array();


    public function __construct( $score1, $score2, $place,$p){



        $this->score1 = $score1;
        $this->score2 = $score2;
        $this->place = $place; 
        $this->p = $p;


    }



}