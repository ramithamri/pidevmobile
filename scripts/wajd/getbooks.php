<?php
require_once('connect.php');

$return_arr = array();

$id=$_GET['id'];
$sql = "SELECT * FROM complaints where iduser='$id' order by dateTime desc";
$c= new Config();
$conn=$c->getConnexion();

$resultat = $conn->query($sql);
$result=$resultat->fetchAll();

foreach ($result as $row) {
       $row_array['Name'] = $row['type'];
       $row_array['Author'] = $row['content'];
	   $row_array['Category'] = $row['dateTime'];
	   $row_array['ISBN'] = $row['Status'];
array_push($return_arr,$row_array);
}

echo json_encode($return_arr);


?>