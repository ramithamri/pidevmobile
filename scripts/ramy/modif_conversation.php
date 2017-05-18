<?php
require_once('connect.php');

$Id=$_GET['id'];
$status=$_GET['status']
$sql = "UPDATE conversation set status='$status' WHERE id='$Id'";

if (mysqli_query($conn, $sql))
{
    echo "success";
} else
{
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>