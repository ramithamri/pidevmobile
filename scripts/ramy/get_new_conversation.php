<?php
require_once('connect.php');

$id1=$_GET['senderId'];
$id2=$_GET['receiverId'];

$sql="select * from conversation where userOneId= '$id1' and userTwoId='$id2'";
$result = $conn->query($sql);
$return_array = array();
if($result->num_rows > 0){

//$row = $result->fetch();
    while($row= $result->fetch_assoc()){
        $row_array['id'] = $row['id'];
        $row_array['userOneId'] = $row['userOneId'];
        $row_array['userTwoId'] = $row['userTwoId'];
        $row_array['date'] = $row['date'];
        $row_array['status'] = $row['status'];
        array_push($return_array,$row_array);
    }
}
//}
//echo $auth;
//echo $roles[0];
echo json_encode($return_array);
?>