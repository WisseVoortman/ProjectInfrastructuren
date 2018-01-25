<?php
	session_start();
	include_once 'functions/check_login.php';
?>
<!DOCTYPE html>
<html>
	<?php
		include 'functions/head.php';
	?>
	<body>
		<header>
		</header>
		<nav>
			test text
		</nav>
		<div class="container">
			<h1>Is dit een teletubbie?</h1>
			
			<div class="selector">
				<form action="/dashboard.php">
					<table class="selector-table">
						<tr></tr>
						<tr>				
							<th>startdate:</th>
							<td><input type="date" name="startdate"></td>
						</tr>
						<tr>
							<th>enddate:</th>
							<td><input type="date" name="enddate"></td>
						</tr>
						<tr>	
							<th>starttime:</th>
							<td><input type="time" name="starttime"></td>
						</tr>
						<tr>
							<th>enddate:</th>
							<td><input type="time" name="endtime"></td>
						</tr>
						<tr>
							<th colspan="2"><button type="submit">Send</button></th>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<footer>
		</footer>
	</body>
</html>