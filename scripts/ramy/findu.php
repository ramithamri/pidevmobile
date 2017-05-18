<?php
require_once('connect.php');

$idu=$_GET['idu'];


$sql="select * from user where profileId= '$id'";

$result = $conn->query($sql);
if($result->num_rows > 0){


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
	   
		
	}
}
	else{
		echo "Profile Not Found";
		}
//}

echo json_encode($row_array);
?>
