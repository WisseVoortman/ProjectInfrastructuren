<?php 
	$user_found = false;
	if(!isset($_SESSION['loggedin']) || !$_SESSION['loggedin']) {
		header("Location: index.php");
	} else if(!empty($_SESSION['username'])) {
		$user_found = true;
	} else {
		header("Location: index.php");
	}
?>