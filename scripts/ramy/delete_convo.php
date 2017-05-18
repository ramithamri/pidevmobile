<?php
require_once('connect.php');

$id=$_GET['id'];

$sql="delete from conversation where id='$id'";
$conn->query($sql);
$sql2= "delete from message where conversationId='$id'";
$conn->query($sql2);
?>
