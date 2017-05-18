<?php
require_once('connect.php');



$id=$_GET['id'];
$price=doubleval($_GET['price']);
$citysource=$_GET['citysource'];
$citydestination=$_GET['citydestination'];
$nbrPlaces=intval($_GET['nbrPlaces']);
$sql = "UPDATE ridedriver SET price='$price',citysource='$citysource',citydestination='$citydestination',nbrPlaces='$nbrPlaces' WHERE id=$id";

if (mysqli_query($conn, $sql)) {
    echo "successfully updated";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>