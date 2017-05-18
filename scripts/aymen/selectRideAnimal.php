<?php
require_once('connect.php');

$idUser=$_GET['idUser'];

$sql = "SELECT * FROM rideanimal where idUser != $idUser";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
	$arr = array_map('utf8_encode', $row);
        $mydata = $json->addChild('rideanimal');
        $mydata->addChild('id',$arr['id']);
        $mydata->addChild('citySource',$arr['citySource']);
        $mydata->addChild('placeSource',$arr['placeSource']);
        $mydata->addChild('cityDestination',$arr['cityDestination']);
        $mydata->addChild('placeDestination',$arr['placeDestination']);
        $mydata->addChild('speciesAnimal',$arr['speciesAnimal']);
        $mydata->addChild('specialNeeds',$arr['specialNeeds']);
        $mydata->addChild('dateSource',$arr['dateSource']);
        
         }
  //  echo( json_encode ($mydata));
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>