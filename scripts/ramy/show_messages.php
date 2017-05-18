<?php
require_once('connect.php');

$id=$_GET['id'];

$sql="select * from message where conversationId='$id' order by time asc";
$result = $conn->query($sql);
$return_array = array();
if($result->num_rows > 0){

//$row = $result->fetch();
while($row= $result->fetch_assoc()){
	   $row_array['id'] = $row['id'];
       $row_array['senderUserId'] = $row['senderUserId'];
       $row_array['messageContent'] = $row['messageContent'];
	   $row_array['time'] = $row['time'];
	   $row_array['conversationId'] = $row['conversationId'];
	   array_push($return_array,$row_array);
	}
}
//}
//echo $auth;
//echo $roles[0];
echo json_encode($return_array);
?>
