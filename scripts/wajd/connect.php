<?php  
class Config
{
    private $connexion;

    function __construct()
    {
        $this->connexion = new PDO("mysql:host=localhost;dbname=pidev","root","",array(PDO::ATTR_ERRMODE=> PDO::ERRMODE_EXCEPTION));
    }

    function getConnexion(){
        return $this->connexion;
    }
}
 ?>