<?php
require_once('connect.php');

$id=$_GET['id'];
$return_array = array();


$sql="select * from profile where profileId= '$id'";

$result = $conn->query($sql);




while($row= $result->fetch_assoc()){

		
		$row_array['profileId'] = $row['profileId'];
       $row_array['professionalField'] = $row['professionalField'];
       $row_array['currentJob'] = $row['currentJob'];
	   $row_array['address'] = $row['address'];
	   $row_array['summary'] = $row['summary'];
	   $row_array['language'] = $row['language'];
	   $row_array['education'] = $row['education'];
	   $row_array['experience'] = $row['experience'];
	   $row_array['contactEmail'] = $row['contactEmail'];

	array_push($return_array,$row_array);
		
	}


//}

echo json_encode($return_array);
?>
