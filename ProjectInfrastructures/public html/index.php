<?php
	session_start();
   	require_once("config.php");
   	require_once("login.php");
   	
   	if(isset($_SESSION['loggedin']) && $_SESSION['loggedin']) {
		header("Location: dashboard.php");
	}
	if(isset($_POST["logout"]) && $_POST["logout"]) {
		$_SESSION['loggedin'] = false;
	}
	
?>
<!DOCTYPE html>
<html>
<?php
include 'functions/head.php';
?>
<body>
	<div class="container">
		<div class="image-container">
			<img src="assets\images\logoBhutan.png" alt="Bhutan" class="image">
		</div>
		<h1>Please login to continue</h1>
	</div>
	<?php if(!empty($error))  echo '<p style=color:red;>'. $error .'</p>';    ?>
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