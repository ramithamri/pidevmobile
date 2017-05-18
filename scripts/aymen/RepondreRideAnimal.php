<?php
require_once('connect.php');

$id_demande=$_GET['id_demande'];
$idrep=$_GET['idrep'];



$sql = "UPDATE demande set idrep='$idrep',etat=1 where id_demande='$id_demande'";
$result = $conn->query($sql);

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>