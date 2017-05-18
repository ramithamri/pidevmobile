<?php
require_once('connect.php');

$senderId=$_GET['senderId'];
$receiverId=$_GET['receiverId'];

$sql = "INSERT INTO conversation (userOneId,userTwoId,date,status)
VALUES ( '$senderId', '$receiverId' , now() , '0')";

if (mysqli_query($conn, $sql))
{
    echo "success";
} else
{
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>