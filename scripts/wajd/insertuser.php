<?php
require_once('connect.php');

$name=$_GET['name'];
$surname=$_GET['surname'];
$username=$_GET['username'];
$email=$_GET['email'];
$password=$_GET['password'];
$telephone=(int)$_GET['telephone'];
/*$datebirth=$_GET['datebirth'];
$dateinscription=$_GET['dateinscription'];*/
$address1=$_GET['address1'];
/*$address2=$_GET['address2'];
$codepostal=$_GET['codepostal'];
$gender=$_GET['gender'];
$newsletter=$_GET['newsletter'];
$secretquestion=$_GET['secretquestion'];
$secretanswer=$_GET['secretanswer'];*/
$photo="aucune";
$type=" a:1:{i:0;s:13:"+"ROLE_EMPLOYEE"+";} ";
$e=(int)0;

/*$sql = "INSERT INTO users
(name,surname,username,email,
pass,telephone,datebirth,dateinscription,
address1,address2,codepostal,gender,
newsletter,secretquestion,secretanswer,photo)
VALUES
( '$name','$surname','$username','$email',
 '$password','$telephone','$datebirth','$dateinscription',
  '$address1','$address2','$codepostal','$gender',
   '$newsletter','$secretquestion','$secretanswer','$photo')";*/
$c= new Config();
$conn=$c->getConnexion();


$sql = "INSERT INTO user
 ( username,username_canonical,email,email_canonical,enabled,
password,roles,name,surname,phone,
photo,adress
)
VALUES 
( '$username','$username','$email','$email','$e',
 '$password','$type','$name','$surname','$telephone',
  
   '$photo','$address1')";

$conn->query($sql);

?>
