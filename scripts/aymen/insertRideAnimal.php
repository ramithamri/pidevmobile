<?php
require_once('connect.php');

$villesource=$_GET['villesource'];
$placesource=$_GET ['placesource'];
$villedestination=$_GET['villedestination'];
$placedestination=$_GET['placedestination'];
$typeAnimal=$_GET['typeAnimal'];
$extraInfo=$_GET['extraInfo'];
$date=$_GET['date'];


$sql = "INSERT INTO rideanimal ( citySource	, placeSource, cityDestination, placeDestination,speciesAnimal, specialNeeds,dateSource,idUser) VALUES ( '$villesource','$placesource','$villedestination','$placedestination','$typeAnimal','$extraInfo','$date','3')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>