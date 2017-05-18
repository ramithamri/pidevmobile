<?php
require_once('connect.php');

//$currentJob=$_GET['currentJob'];
$id=$_GET['id'];
$curr=$_GET['curr'];
$prof=$_GET['prof'];
$addr=$_GET['addr'];
$lan=$_GET['lan'];
$sum=$_GET['sum'];
$ex=$_GET['ex'];
$ed=$_GET['ed'];
$em=$_GET['em'];
//$professionalField=$_GET['professionalField'];
$sql = "UPDATE profile SET currentJob = '$curr',professionalField = '$prof',address = '$addr',language = '$lan',summary ='$sum',experience = '$ex',education = '$ed',contactEmail = '$em' WHERE profileId = '$id'";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>