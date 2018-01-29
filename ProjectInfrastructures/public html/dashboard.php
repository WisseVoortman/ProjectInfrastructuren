<?php
	session_start();
	include_once 'functions/check_login.php';
?>
<!DOCTYPE html>
<html>
	<head>
		<title>Weather data royal university of Bhutan</title>
		<link rel="stylesheet" type="text/css" href="resources/css/main.css">
		<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
		<script src="resources/javascript/main.js"></script>
		<meta charset="utf-8";>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="robots" content="noindex, nofollow">
		<meta name="description" content="een super awesome website">
		<link rel="shortcut icon" href="assets\images\initialPicture.jpg">

	</head>
	<body>
		<header>
		</header>
		<nav>
			<div class="button-selection container">
				<button type="button" id="button-selection-option-all" class="button-selection-option">All</button>
				<button type="button" id="button-selection-option-temp" class="button-selection-option">Temp</button> 
				<button type="button" id="button-selection-option-downfall" class="button-selection-option">Downfall</button>
				<button type="button" id="button-selection-option-snowfall" class="button-selection-option">Snowfall</button>
				<button type="button" id="button-selection-option-custom" class="button-selection-option">Custom</button>
			</div>
		</nav>
		<div id="test">
		
		</div>
		
		<div class="container">
			<h1>Dashboard page</h1>
			
			<div class="">
				<div class="dashboardItem" id="tempGauge"></div>
				<div class="dashboardItem" id="downfallGraph"></div>
			</div>
			
			<div class="selector" id="custom_selector">
				<form action="#" onsubmit="return false;">
					<table class="selector-table">
						<tr></tr>
						<tr>				
							<th>start date and time:</th>
							<td><input type="date" id="startDate"></td>
							<td><input type="time" id="startName"></td>
						</tr>
						<tr>
							<th>end date and time:</th>
							<td><input type="date" id="endDate"></td>
							<td><input type="time" id="endTime"></td>
						</tr>
						<!--<tr>	
							<th>starttime:</th>
							<td><input type="time" name="starttime"></td>
						</tr>
						<tr>
							<th>enddate:</th>
							<td><input type="time" name="endtime"></td>
						</tr>-->
						<tr>
							<th colspan="3"><button type="submit">Send</button></th>
						</tr>
					</table>
				</form>
			</div>
			
		</div>
		<footer>
		
		<script src="https://code.highcharts.com/highcharts.src.js"></script>
		<script src="https://code.highcharts.com/highcharts-more.js"></script>
		<script src="https://code.highcharts.com/modules/exporting.js"></script>
		<script src="https://code.highcharts.com/modules/offline-exporting.js"></script>
		<script src="resources/javascript/tempGauge.js"></script>
		<script src="resources/javascript/downfallGraph.js"></script>

		<script> 
			
		</script>
		</footer>
	</body>
</html>