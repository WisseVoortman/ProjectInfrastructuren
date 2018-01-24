<?php
session_start();

 ?>
<!DOCTYPE html>
<html>
<head>
	<title>Overview page</title>
</head>
<body>
<h1> <?php echo "Welcome on the overview page" . $_SESSION["username"] ?> </h1>
</body>
</html>