<?php
if($_SERVER["REQUEST_METHOD"] == "POST") {
   	  $error = '';
      //kijkt of username of password niet leeg is 
      if(!empty($_POST['username']) AND !empty($_POST['password'])) 
      {
      	$myusername = $_POST['username'];
      	$mypassword = $_POST['password'];
      	$sql = "SELECT id FROM users WHERE username = '$myusername' and password = '$mypassword'";
      	if($result = $db->query($sql)) {
      		if(mysqli_num_rows($result) > 0)
      		{
      			$_SESSION['username'] = $myusername;
      			header("Location: overview.php");
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