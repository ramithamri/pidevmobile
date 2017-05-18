<?php
require_once('connect.php');



$sql = "SELECT AVG(rating) as moy, username as nom from reviews R , user U WHERE R.idUser2=U.id Group by idUser2";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      $mydata = $json->addChild('reviews');
        $mydata->addChild('moy',$row['moy']);
  
      
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>