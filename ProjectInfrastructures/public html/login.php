<?php
if($_SERVER["REQUEST_METHOD"] == "POST") {
   	  $error = '';
      //kijkt of username of password niet leeg is 
      if(!empty($_POST['username']) AND !empty($_POST['password'])) {
      	$myusername = $_POST['username'];
      	$mypassword = $_POST['password'];
      	$sql = "SELECT password FROM users WHERE username = '$myusername'";
      	if($result = $db->query($sql)) {
      		if(mysqli_num_rows($result) > 0) {
                        $pwhash = $result->fetch_assoc();
                        if(password_verify($mypassword, $pwhash['password'])) {
                              $_SESSION['username'] = $myusername;
                              $_SESSION['loggedin'] = true;
                              header("Location: dashboard.php");            
                        }
      			else {
                              $error = 'wrong password';
                        }

      		else {
      			$error = 'username or password cannot be found';
      		}
      	}
      	mysqli_close($db);
      }
      else {
      	$error = 'username or password is empty';
      }
   }  
?>