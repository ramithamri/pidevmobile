<?php
require_once('connect.php');

$mail=$_GET['mail'];
$password=$_GET['password'];
$sql = "INSERT INTO user (email,password,username,username_canonical,email_canonical,enabled,salt,roles)
VALUES ( '$mail','$password','','','$mail','1','','a:0:{}')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>