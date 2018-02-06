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
		<nav>
			<div class="button-selection">
				<button type="button" id="button-selection-option-all" onclick="dashboardPage();" class="button-selection-option">Dashboard</button>
				<button type="button" id="button-selection-option-temp" onclick="temp();" class="button-selection-option">Temperature</button> 
				<button type="button" id="button-selection-option-downfall" onclick="downfall();" class="button-selection-option">Downfall</button>
				<button type="button" id="button-selection-option-snowfall" onclick="" class="button-selection-option">Snowfall</button>
				<button type="button" id="button-selection-option-custom" onclick="custom();" class="button-selection-option">Custom</button>
				<a href="logout.php"><button type="submit" id="button-selection-option-logout" class="button-selection-option">Logout</button></a>
				<button type="button" id="button-selection-option-internet" onclick="check_internet_connection();" class="button-selection-option">check internet</button>
				<div class="switch-selection">
					<span id="switch-selection-text-metric" class="switch-selection-text">Metric</span>
					<label class="switch-selection-option-data">
						<input type="checkbox" id="switch-selection-option-check">
						<span class="slider round" onclick="measurement();"></span>
					</label>
					<span id="switch-selection-text-metric" class="switch-selection-text">Imperial</span>
				</div>
			</div>
		</nav>
		<header>
		</header>
		<div class="container">
			<div id="error-message">
				<!--
				<p class="error dashboard-error-message"><b>No internet connection</b></p>
			-->
			</div>
			<div id="dashboard-items">
				<!--<div class="dashboardItem" id="tempGauge"></div>
				<div class="dashboardItem" id="downfallGraph"></div>-->
				<!--
				<div class="dashboardItem" id="downfallSelector">
					<select>
						<option value="weatherstation-1">weatherstation 1</option>
						<option value="weatherstation-2">weatherstation 2</option>
						<option value="weatherstation-3">weatherstation 3</option>
						<option value="weatherstation-4">weatherstation 4</option>
					</select>
				</div>
				-->
				
			</div>
			
		</div>
		
		<?php
			include 'functions/footer.php';
		?>
		
		<script src="https://code.highcharts.com/highcharts.src.js"></script>
		<script src="https://code.highcharts.com/highcharts-more.js"></script>
		<script src="https://code.highcharts.com/modules/exporting.js"></script>
		<script src="https://code.highcharts.com/modules/offline-exporting.js"></script>
		<script src="https://highcharts.github.io/export-csv/export-csv.js"></script>
		<script src="resources/javascript/main.js"></script>
		<script src="resources/javascript/html/stationSelector.js"></script
		<script src="resources/javascript/highchartsGlobalSettings.js"></script>
		<script src="resources/javascript/tempGauge.js"></script>
		<script src="resources/javascript/downfallGraph.js"></script>
		<script type="text/javascript">
			dashboardPage();
		</script>

		
	</body>
</html>