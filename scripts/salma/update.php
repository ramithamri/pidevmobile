<?php
require_once('connect.php');



$id=$_GET['id'];
$price=doubleval($_GET['price']);
$citysource=$_GET['citySource'];
$citydestination=$_GET['cityDestination'];

$sql = "UPDATE ridepassenger SET price='$price',citySource='$citysource',cityDestination='$citydestination' WHERE id=$id";

if (mysqli_query($conn, $sql)) {
    echo "successfully updated";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>