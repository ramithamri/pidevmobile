<?php
require_once('connect.php');


$user=$_GET['user'];
$sql = "SELECT rating,title,content FROM reviews where idUser = '$user'";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      $mydata = $json->addChild('reviews');
        $mydata->addChild('rating',$row['rating']);
        $mydata->addChild('title',$row['title']);
        $mydata->addChild('content',$row['content']);
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>