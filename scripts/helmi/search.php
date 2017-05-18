<?php

require_once('connect.php');

$citysource=$_GET['citysource'];

$sql = "SELECT id,price,citysource,citydestination,nbrPlaces FROM ridedriver WHERE citysource='$citysource'";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        $mydata = $json->addChild('ridedriver');
        $mydata->addChild('id',$row['id']);
        $mydata->addChild('price',$row['price']);
        $mydata->addChild('citysource',$row['citysource']);
        $mydata->addChild('citydestination',$row['citydestination']);
        $mydata->addChild('nbrPlaces',$row['nbrPlaces']);
    }
} else {
    echo "0 results";
}
$conn->close();
echo( json_encode ($json));
?>