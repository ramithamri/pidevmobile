<?php

require 'PHP/PHPMailerAutoload.php';

$mail = new PHPMailer;
$email=$_GET['receiver'];
$objet=$_GET['objet'];
$contenu=$_GET['contenu'];
$mail->isSMTP();                                      // Set mailer to use SMTP
$mail->Host = "smtp.gmail.com";  // Specify main and backup SMTP servers
$mail->SMTPAuth = true;                               // Enable SMTP authentication
$mail->Username = 'rami.thamri@esprit.tn';                 // SMTP username
$mail->Password = '20125018';                           // SMTP password
$mail->SMTPSecure = 'tls';                            // Enable encryption, 'ssl' also accepted
$mail->Port = 587;

$mail->From = 'from@example.com';
$mail->FromName = 'Mailer';
$mail->addAddress($email, 'Joe User');     // Add a recipient


$mail->WordWrap = 50;                                 // Set word wrap to 50 characters
$mail->isHTML(true);                                  // Set email format to HTML

$mail->Subject = $objet;
$mail->Body    = $contenu;
$mail->AltBody = 'This is the body in plain text for non-HTML mail clients';

if(!$mail->send())
{

$reponse= 'Message could not be sent.';
}
else

{
$reponse = 'Message has been sent';
}
$return_array = array();
$row_array['reponse'] = $reponse;
array_push($return_array,$row_array);
echo json_encode($return_array);
?>