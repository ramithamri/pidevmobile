<?php
require_once('connect.php');
if (isset($_GET['table'])) {
	$table = $_GET['table'] ;
} else {
	$table = NULL ;
}

if ($table == "ridepassenger") {
    if (isset($_GET['idtrajet'])) {
        $sql = "SELECT * FROM ridepassenger WHERE id=".$_GET['idtrajet'] ;
        $result = $conn->query($sql);
        $json = new SimpleXMLElement('<xml/>');
        if ($result->num_rows > 0) {
        // output data of each row
        while($row = $result->fetch_assoc()) {
            $mydata = $json->addChild('ridepassengershow');
            $mydata->addChild('id',$row['id']);
            $mydata->addChild('citySource',$row['citySource']);
            $mydata->addChild('longSource',$row['longSource']);
            $mydata->addChild('latSource',$row['latSource']);
            $mydata->addChild('cityDestination',$row['cityDestination']);
            $mydata->addChild('longDestination',$row['longDestination']);
            $mydata->addChild('latDestination',$row['latDestination']);
             }
        } 
    }
    else {
        $sql = "SELECT * FROM ridepassenger";
        $result = $conn->query($sql);
        $json = new SimpleXMLElement('<xml/>');
        if ($result->num_rows > 0) {
        // output data of each row
        while($row = $result->fetch_assoc()) {
            $mydata = $json->addChild('ridepassenger');
            $mydata->addChild('id',$row['id']);
            $mydata->addChild('citySource',$row['citySource']);
            $mydata->addChild('longSource',$row['longSource']);
            $mydata->addChild('latSource',$row['latSource']);
            $mydata->addChild('cityDestination',$row['cityDestination']);
            $mydata->addChild('longDestination',$row['longDestination']);
            $mydata->addChild('latDestination',$row['latDestination']);
             }
        } 
    }
} else if ($table == "ridepackage") {
    if (isset($_GET['idpackage'])) {
        $sql = "SELECT * FROM ridepackage WHERE id_package=".$_GET['idpackage'];
        $result = $conn->query($sql);
        $json = new SimpleXMLElement('<xml/>');
        if ($result->num_rows > 0) {
        // output data of each row
        while($row = $result->fetch_assoc()) {
            $mydata = $json->addChild('ridepackageshow');
            $mydata->addChild('id_package',$row['id_package']);
            $mydata->addChild('idtrajet',$row['idtrajet']);
            $mydata->addChild('description',$row['description']);
            $mydata->addChild('size',$row['size']);
            $mydata->addChild('typePackage',$row['typePackage']);
            $mydata->addChild('quantity',$row['quantity']);
            $mydata->addChild('poids',$row['poids']);
            $mydata->addChild('prix',$row['prix']);
            $mydata->addChild('idUser',$row['idUser']);
             }
        }
    } else {
        $sql = "SELECT * FROM ridepackage";
        $result = $conn->query($sql);
        $json = new SimpleXMLElement('<xml/>');
        if ($result->num_rows > 0) {
        // output data of each row
        while($row = $result->fetch_assoc()) {
            $mydata = $json->addChild('ridepackage');
            $mydata->addChild('id_package',$row['id_package']);
            $mydata->addChild('idtrajet',$row['idtrajet']);
            $mydata->addChild('description',$row['description']);
            $mydata->addChild('size',$row['size']);
            $mydata->addChild('typePackage',$row['typePackage']);
            $mydata->addChild('quantity',$row['quantity']);
            $mydata->addChild('poids',$row['poids']);
            $mydata->addChild('prix',$row['prix']);
            $mydata->addChild('idUser',$row['idUser']);
             }
        }
    }
} else if ($_GET['table'] == NULL) {
	echo "no table selected" ;
}
$conn->close();
	 echo( json_encode ($json));
?>