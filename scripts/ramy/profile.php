<?php
require_once('connect.php');

$return_arr = array();
$id = $_GET['id'];

$sql = "SELECT * FROM profile p,user u where p.profileId='$id' and u.id='$id'";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
       $row_array['profileId'] = $row['profileId'];
       $row_array['professionalField'] = $row['professionalField'];
	   $row_array['currentJob'] = $row['currentJob'];
	   $row_array['address'] = $row['address'];
	   $row_array['summary'] = $row['summary'];
	   $row_array['language'] = $row['language'];
	   $row_array['education'] = $row['education'];
	   $row_array['experience'] = $row['experience'];
	   $row_array['contactEmail'] = $row['contactEmail'];
	   $row_array['firstName'] = $row['firstName'];
	   $row_array['lastName'] = $row['lastName'];
	   
       
 array_push($return_arr,$row_array);
    }
} 


echo json_encode($return_arr);

?>
