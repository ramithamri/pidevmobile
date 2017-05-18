<?php
require_once('connect.php');

$size=$_GET['size'];
$typePackage=$_GET['typePackage'];
$poids=$_GET['poids'];
$quantity=$_GET['quantity'];
$description=$_GET['description'];
$prix=$_GET['prix'];
$idUser=$_GET['idUser'] ;
$idtrajet=$_GET['idtrajet'] ;
$sql = "INSERT INTO ridepackage ( idtrajet, description, size, typePackage, quantity, poids, prix, idUser)
VALUES ( $idtrajet,'$description','$size', '$typePackage', $quantity, $poids, $prix, $idUser)";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>