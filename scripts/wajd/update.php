<?php
require_once('connect.php');



$id=$_GET['id'];
$title=$_GET['title'];
$author=$_GET['author'];
$category=$_GET['category'];
$isbn=$_GET['isbn'];
$sql = "UPDATE books SET title='$title',author='$author',category='$category',isbn='$isbn' WHERE id=$id";

$c= new Config();
$conn=$c->getConnexion();
$conn->query($sql);
?>