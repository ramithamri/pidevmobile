<?php
require_once('connect.php');



$sql = "SELECT id, username from user";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      $mydata = $json->addChild('user');
        $mydata->addChild('id',$row['id']); 
		$mydata->addChild('username',$row['username']);
  
      
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>