<?php
require_once('connect.php');
$id=$_GET['id'];
$return_arr = array();

$sql = "SELECT * FROM user Where id!='$id'";


$result = $conn->query($sql);

if($result->num_rows > 0)
{
    while ($row =  $result->fetch_assoc()) {
        $row_array['name'] = utf8_encode($row['name']);
        $row_array['surname'] = utf8_encode($row['surname']);
        $row_array['id'] = utf8_encode($row['id']);


        array_push($return_arr,$row_array);
    }

}

 
echo json_encode($return_arr);


?>