<?php
require_once('connect.php');

$return_arr = array();

$sql = "SELECT * FROM user ";
$c= new Config();
$conn=$c->getConnexion();

$resultat = $conn->query($sql);
$result=$resultat->fetchAll();
 
foreach ($result as $row) {
       $row_array['name'] = utf8_encode($row['name']);
       $row_array['surname'] = utf8_encode($row['surname']);
       $row_array['email'] = utf8_encode($row['email']);
       $row_array['password'] = utf8_encode($row['password']);
       $row_array['phone'] = utf8_encode($row['phone']);
       /*$row_array['datebirth'] = utf8_encode($row['datebirth']);
       $row_array['dateinscription'] = utf8_encode($row['dateinscription']);*/
       $row_array['adress'] = utf8_encode($row['address1']);
      /* $row_array['address2'] = utf8_encode($row['address2']);*/
      /* $row_array['codepostal'] = utf8_encode($row['codepostal']);
       $row_array['gender'] = utf8_encode($row['gender']);
       $row_array['newsletter'] = utf8_encode($row['newsletter']);
       $row_array['secretquestion'] = utf8_encode($row['secretquestion']);
       $row_array['secretanswer'] = utf8_encode($row['secretanswer']);*/
       $row_array['photo'] = utf8_encode($row['photo']);

 array_push($return_arr,$row_array);
} 
 
echo json_encode($return_arr);


?>