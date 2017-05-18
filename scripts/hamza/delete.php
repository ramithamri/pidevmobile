<?php
require_once('connect.php');
if (isset($_GET['table'])) {
	$table = $_GET['table'] ;
} else {
	$table = NULL ;
}

if ($table == "ridepassenger") {
    if (isset($_GET['idtrajet'])) {
        $sql = "DELETE FROM ridepassenger WHERE id=".$_GET['idtrajet'] ;
        $result = $conn->query($sql);
    } 
} else if ($table == "ridepackage") {
    if (isset($_GET['idpackage'])) {
        $sql = "DELETE FROM ridepackage WHERE id_package=".$_GET['idpackage'];
        $result = $conn->query($sql);
    } 
} else if ($_GET['table'] == NULL) {
	echo "no table selected" ;
}
$conn->close();
	 echo( json_encode ($json));
?>