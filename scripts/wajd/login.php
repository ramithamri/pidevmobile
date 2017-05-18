<?php
require_once('connect.php');

$return_arr = array();
$username=$_GET['username'];
$password=$_GET['password'];

$sql = "SELECT * FROM user where username='$username' and password='$password'";
$c= new Config();
$conn=$c->getConnexion();

$resultat = $conn->query($sql);
$result=$resultat->fetchAll();
 if (isset($result)){
foreach ($result as $row) {
       $row_array['name'] = utf8_encode($row['name']);
       $row_array['id'] = utf8_encode($row['id']);
       $row_array['surname'] = utf8_encode($row['surname']);
       $row_array['email'] = utf8_encode($row['email']);
       $row_array['username'] = utf8_encode($row['username']);
       $row_array['password'] = utf8_encode($row['password']);
       $row_array['phone'] = utf8_encode($row['phone']);
       /*$row_array['dateBirth'] = utf8_encode($row['dateBirth']);
       $row_array['dateInscription'] = utf8_encode($row['dateInscription']);*/
       $row_array['address1'] = utf8_encode($row['adress']);
       /*$row_array['address2'] = utf8_encode($row['address2']);
       $row_array['codePostal'] = utf8_encode($row['codePostal']);
       $row_array['gender'] = utf8_encode($row['gender']);
       $row_array['newsletter'] = utf8_encode($row['newsletter']);
       $row_array['secretQuestion'] = utf8_encode($row['secretQuestion']);
       $row_array['secretAnswer'] = utf8_encode($row['secretAnswer']);*/
       $row_array['photo'] = utf8_encode($row['photo']);

 array_push($return_arr,$row_array);
} 
 }
echo json_encode($return_arr);


?>