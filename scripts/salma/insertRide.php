<?php
require_once('connect.php');

$price=intval($_GET['price']);
$citySource=$_GET['citySource'];
$cityDestination=$_GET['cityDestination'];
$sql = "INSERT INTO ridepassenger ( price, citySource ,cityDestination)
VALUES ( '$price','$citySource','$cityDestination')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>