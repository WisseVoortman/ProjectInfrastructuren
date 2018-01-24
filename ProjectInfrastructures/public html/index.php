<?php
   include("config.php");
   session_start();
   
   if($_SERVER["REQUEST_METHOD"] == "POST") {
      // username and password sent from form 
      
      $myusername = mysqli_real_escape_string($db,$_POST['username']);
      $mypassword = mysqli_real_escape_string($db,$_POST['password']); 
      
      $sql = "SELECT id FROM user WHERE username = '$myusername' and passcode = '$mypassword'";
      $result = mysqli_query($db,$sql);
      echo $result;
      $row = mysqli_fetch_array($result,MYSQLI_ASSOC);
      $active = $row['active'];
      
      $count = mysqli_num_rows($result);
      
      // If result matched $myusername and $mypassword, table row must be 1 row
		
      if($count == 1) {
         session_register("myusername");
         $_SESSION['login_user'] = $myusername;
         
         header("Location: overview.php");
      }else {
         $error = "Your Login Name or Password is invalid";
      }
   }
?>
<!DOCTYPE html>
<html>
<?php
include '/functions/head.php';
?>
<body>
	<h1>Please login to continue</h1>
	<p>The login in required</p>
	<form action="" method="post">

	  <div class="container">
	    <label><b>Username</b></label>
	    <input type="text" placeholder="Enter Username" name="username" required>

	    <label><b>Password</b></label>
	    <input type="password" placeholder="Enter Password" name="password" required>

	    <button type="submit">Login</button>
	  </div>
	</form>
</body>
</html>