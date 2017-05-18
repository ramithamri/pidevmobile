<?php
require_once('connect.php');

$idRidedriver=$_GET['idRidedriver'];
$idUser=$_GET['idUser'];

$sql = "INSERT INTO bookings ( idRidedriver, idUser)
VALUES ( '$idRidedriver','$idUser')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>