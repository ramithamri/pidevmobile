<?php
require_once('connect.php');

require_once ('PHPMailer/PHPMailerAutoload.php');

$type=$_GET['type'];
$content=$_GET['content'];
$dateTime=$_GET['datetime'];
$idUser=$_GET['iduser'];
$sql = "INSERT INTO Complaints (type,content,dateTime,idUser, status)
VALUES ( '$type','$content','$dateTime','$idUser', 'Unseen')";
$c= new Config();
$conn=$c->getConnexion();
$conn->query($sql);




$mail = new PHPMailer;

$mail->isSMTP();                            // Set mailer to use SMTP
$mail->Host = 'smtp.gmail.com';             // Specify main and backup SMTP servers
$mail->SMTPAuth = true;                     // Enable SMTP authentication
$mail->Username = 'wajd.meskini@esprit.tn';          // SMTP username
$mail->Password = 'Na7nafibledna3edna'; // SMTP password
$mail->SMTPSecure = 'tls';                  // Enable TLS encryption, `ssl` also accepted
$mail->Port = 587;                          // TCP port to connect to

$mail->setFrom('wajd.meskini@esprit.tn', 'CodexWorld');
$mail->addReplyTo('wajd.meskini@esprit.tn', 'CodexWorld');
$mail->addAddress('wajd.meskini@esprit.tn');   // Add a recipient
$mail->addCC('wajd.meskini@esprit.tn');
$mail->addBCC('wajd.meskini@esprit.tn');

$mail->isHTML(true);  // Set email format to HTML

$bodyContent = '<h1>Notification</h1>';
$bodyContent .= '<p>We have received a new complaint from <b>  </b></p>';

$mail->Subject = 'Complaint notification';
$mail->Body    = $bodyContent;

if(!$mail->send()) {
    echo 'Message could not be sent.';
    echo 'Mailer Error: ' . $mail->ErrorInfo;
} else {
    echo 'Message has been sent';
}

?>
