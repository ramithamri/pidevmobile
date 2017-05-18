<?php
require_once('connect.php');

$price=doubleval($_GET['price']);
$citysource=$_GET['citysource'];
$citydestiantion=$_GET['citydestination'];
$nbrplace=intval($_GET['nbrPlaces']);
$sql = "INSERT INTO ridedriver ( price, citysource, citydestination,nbrPlaces)
VALUES ( '$price','$citysource','$citydestiantion','$nbrplace')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>