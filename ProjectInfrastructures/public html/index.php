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
			<h1>Please login to continue</h1>
		</div>
	</div>
	<div class="container login-container">
		<form action="index.php" method="post">
			<div class="container">
				<?php if(!empty($error))  echo '<p class="error"><span class="fas fa-exclamation-triangle"></span><b>'. $error .'</b></p>';    ?>
				<label><b>Username</b></label>
				<input type="text" placeholder="Enter Username" name="username" required>

				<label><b>Password</b></label>
				<input type="password" placeholder="Enter Password" name="password" required>

				<button type="submit">Login</button>
			</div>
		</form>
	</div>
	<?php
	include 'functions/footer.php';
	?>	
</body>
</html>