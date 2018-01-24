<?php
	session_start();
   	require_once("config.php");
   	require_once("login.php");
?>
<!DOCTYPE html>
<html>
<head>
	<title>Welcome @ Dean Environmental Research Facility</title>
	<link rel="stylesheet" type="text/css" href="resources/css/main.css">
	<script type="text/javascript" src="resources/javascript/main.js"></script>
</head>
<body>
	<h1>Please login to continue</h1>
	<?php if(!empty($error))  echo '<p color="red">'. $error .'</p>';    ?>
	<form action="index.php" method="post">

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