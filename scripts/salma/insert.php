<?php
require_once('connect.php');

$rating=intval($_GET['rating']);
$title=$_GET['title'];
$content=$_GET['content'];
$idUser2=$_GET['idUser2'];
$idUser=$_GET['idUser'];
$sql = "INSERT INTO reviews ( rating, title, content,idUser2,idUser)
VALUES ( '$rating','$title','$content','$idUser2','$idUser')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>