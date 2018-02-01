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
		<div class="button-selection container">
			<button type="button" id="button-selection-option-all" onclick="dashboardPage();" class="button-selection-option">All</button>
			<button type="button" id="button-selection-option-temp" onclick="temp();" class="button-selection-option">Temperature</button> 
			<button type="button" id="button-selection-option-downfall" onclick="downfall();" class="button-selection-option">Downfall</button>
			<button type="button" id="button-selection-option-snowfall" onclick="" class="button-selection-option">Snowfall</button>
			<button type="button" id="button-selection-option-custom" onclick="custom();" class="button-selection-option">Custom</button>
			<a href="logout.php"><button type="submit" id="button-selection-option-logout" class="button-selection-option">Logout</button></a>
		</div>
		<div class="container">
			<h1>Dashboard page</h1>
			<div id="dashboard-items">
				<!--<div class="dashboardItem" id="tempGauge"></div>
				<div class="dashboardItem" id="downfallGraph"></div>-->
			</div>
		</div>
		<footer>	
		</footer>
		<script src="https://code.highcharts.com/highcharts.src.js"></script>
		<script src="https://code.highcharts.com/highcharts-more.js"></script>
		<script src="https://code.highcharts.com/modules/exporting.js"></script>
		<script src="https://code.highcharts.com/modules/offline-exporting.js"></script>
		<script src="https://highcharts.github.io/export-csv/export-csv.js"></script>
		<script src="resources/javascript/highchartsGlobalSettings.js"></script>
		<script src="resources/javascript/tempGauge.js"></script>
		<script src="resources/javascript/downfallGraph.js"></script>
		<script src="resources/javascript/main.js"></script>
		<script type="text/javascript">
			dashboardPage();
		</script>

		
		
	</body>
</html>