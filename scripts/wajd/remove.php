<?php
require_once('connect.php');

$title=$_GET['title'];
$sql = "DELETE FROM books WHERE ISBN = '$title' ";

$c= new Config();
$conn=$c->getConnexion();
$conn->query($sql);
?>