<?php
require_once('connect.php');



$sql = "SELECT id,price,citySource,cityDestination FROM ridepassenger";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      $mydata = $json->addChild('ridepassenger');
	      $mydata->addChild('id',$row['id']);
        $mydata->addChild('price',$row['price']);
        $mydata->addChild('citySource',$row['citySource']);
        $mydata->addChild('cityDestination',$row['cityDestination']);
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>