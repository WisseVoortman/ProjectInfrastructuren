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
				<button type="button" id="button-selection-option-all" onclick="dashboardPage();" class="button-selection-option"><span class="fas fa-home"></span>Dashboard</button>
				<button type="button" id="button-selection-option-temp" onclick="temp();" class="button-selection-option"><span class="fas fa-thermometer-quarter"></span>Temperature</button> 
				<button type="button" id="button-selection-option-rainfall" onclick="downfall();" class="button-selection-option"><span class="fas fa-tint"></span>Rainfall</button>
				<button type="button" id="button-selection-option-snowfall" onclick="" class="button-selection-option"><span class="fas fa-snowflake"></span>Snowfall</button>
				<button type="button" id="button-selection-option-custom" onclick="custom();" class="button-selection-option"><span class="fas fa-cogs"></span>Custom</button>
				<a href="logout.php"><button type="submit" id="button-selection-option-logout" class="button-selection-option"><span class="fas fa-sign-out-alt"></span>Logout</button></a>
				<div class="switch-selection">
					<span id="switch-selection-text-metric" class="switch-selection-text">Metric</span>
					<label class="switch-selection-option-data">
						<input type="checkbox" id="switch-selection-option-check">
						<span class="slider round" onclick="measurement();"></span>
					</label>
					<span id="switch-selection-text-metric" class="switch-selection-text">Imperial</span>
				</div>
				<div class="user-profile">
					<?php if($user_found == true) {echo '<span class="fas fa-user"></span>Welcome,' . $_SESSION['username'];} ?>
				</div>
			</div>
		</nav>
		<header>
			<button type="button" id="" onclick="handleQuery();" class="button-selection-option">test query</button>
			<div id="queryTest">
			</div>
		</header>
		<div class="container">
			<div id="error-message">
			</div>
			<div id="dashboard-items">
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
		<script src="resources/javascript/queryHandler.js"></script>
		<script src="resources/javascript/html/stationSelector.js"></script>
		<script src="resources/javascript/html/customSelector.js"></script>
		<script src="resources/javascript/highchartsGlobalSettings.js"></script>
		<script src="resources/javascript/tempGauge.js"></script>
		<script src="resources/javascript/downfallGraph.js"></script>
		<script src="resources/javascript/main.js"></script>
		<script type="text/javascript">
			dashboardPage();
		</script>

		
	</body>
</html>