<?php
require_once('connect.php');

$id=$_GET['id'];

$sql="select * from user where id='$id'";
$result = $conn->query($sql);
$return_array = array();
if($result->num_rows > 0){

//$row = $result->fetch();
while($row= $result->fetch_assoc()){
	   $row_array['id'] = $row['id'];
       $row_array['email'] = $row['email'];
       $row_array['name'] = $row['name'];
	   $row_array['surname'] = $row['surname'];
	   $row_array['password'] = $row['password'];
	   array_push($return_array,$row_array);
	}
}
//}
//echo $auth;
//echo $roles[0];
echo json_encode($return_array);
?>
