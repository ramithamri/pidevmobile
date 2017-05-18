<?php
require_once('connect.php');

$senderId=$_GET['senderId'];
$content=$_GET['content'];
$convoId=$_GET['convoId'];
$sql = "INSERT INTO message (senderUserId,messageContent,time,conversationId)
VALUES ( '$senderId', '$content' , now() , '$convoId')";

if (mysqli_query($conn, $sql))
{
    echo "success";
} else
    {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>