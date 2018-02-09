var temphtml = '<div class="dashboardItem" id="tempGauge"></div>';
var downfallhtml = '<div class="dashboardItem" id="downfallGraph"></div> '
//var customhtml = '<div class="selector" id="custom_selector"><h3>Select options to create a graph:</h3><table class="selector-table"><tr></tr><tr><th>start date:</th><td><input type="date" id="startDate"></td></tr><tr><th>start time:</th><td><input type="time" id="startName"></td></tr><tr><th>end date:</th><td><input type="date" id="endDate"></td></tr><tr><th>end time:</th><td><input type="time" id="endTime"></td></tr><tr><th colspan="3"></th></tr></table></div><div class="selector" id="selector-graph"><p><b>Select a graph:</b></p><select id="selector-select-graph"><option value="temperature" id="selector-option-temp">Temperature</option><option value="rain" id="selector-option-rain">Rainfall</option><option value="snowfall" id="selector-option-snowfall">Snowfall</option></select><select id="selector-select-graph-time"><option value="hour" id="selector-option-hour">Hour</option><option value="minute" id="selector-option-minute">Minute</option><option value="second" id="selector-option-second">Second</option></select></div>';
var allhtml = temphtml + "" + downfallhtml;
var currentPage;
var measurementSystem = 'metric';
var downfallInterval;
var tempInterval;
var previousButton;
var errorhtml = '<p class="error dashboard-error-message"><b><span class="fas fa-exclamation-triangle"></span>No internet connection</b></p>';

 function setDashboardItemWidth() {
	  var dashboardItems = document.getElementsByClassName("dashboardItem");
	  var i;
	  for (i=0; i < dashboardItems.length; i++) {
		  dashboardItems[i].style.cssFloat = 'none';
		  dashboardItems[i].style.maxWidth = '80%';
	  }
  }
  function configureButton(){
	  document.getElementById(previousButton).style.backgroundColor = '#07275b';
  }
  function buttonReset() {
	  document.getElementById(previousButton).style.backgroundColor = '';
  }
  function check_id_not_null() {
    if(document.getElementById("dashboard-items") !== null) {
      return true;
    }else if (document.getElementById("error-message") !== null) {
    	return true;
    }
    return false;
  }
  function dashboardPage() {
    if(check_id_not_null()) {
		if (previousButton != null){
			buttonReset();
		}
		previousButton = 'button-selection-option-all';
		currentPage = 'dashboard';
		document.getElementById("dashboard-items").innerHTML = allhtml;
		configureButton();
		drawDownfallGraph();
		tempGauge();
	  
    } 
  }
  function temp() {
    if(check_id_not_null()) {
	  buttonReset();
	  previousButton = 'button-selection-option-temp';
      document.getElementById("dashboard-items").innerHTML = temphtml;
	  configureButton();
	  setDashboardItemWidth();
	  tempGauge();
    }
  }
  function downfall() {
    if(check_id_not_null()) {
	  buttonReset();
	  previousButton = 'button-selection-option-rainfall';
	  currentPage = 'rain';
	  document.getElementById("dashboard-items").innerHTML = '<div class="SelectorWrapper" id="downfallSelectorWrapper">' + stationSelectorGenerator() + '</div>' + downfallhtml;
	  configureButton();
	  setDashboardItemWidth();
	  drawDownfallGraph();
	  var graphHeight = window.getComputedStyle(document.getElementById("downfallGraph")).getPropertyValue('height');
	  document.getElementById("downfallSelectorWrapper").style.height = graphHeight;
    }
  }
   function sownFall() {
    if(check_id_not_null()) {
	  buttonReset();
	  previousButton = 'button-selection-option-snowfall';
	  currentPage = 'snow';
	  document.getElementById("dashboard-items").innerHTML = '<div class="SelectorWrapper" id="downfallSelectorWrapper">' + stationSelectorGenerator() + '</div>' + downfallhtml;
	  configureButton();
	  setDashboardItemWidth();
	  drawDownfallGraph();
	  var graphHeight = window.getComputedStyle(document.getElementById("downfallGraph")).getPropertyValue('height');
	  document.getElementById("downfallSelectorWrapper").style.height = graphHeight;
    }
  }
  function custom() {
		if(check_id_not_null()) {
			buttonReset();
			previousButton = 'button-selection-option-custom';
			document.getElementById("dashboard-items").innerHTML = '<div class="SelectorWrapper" id="customSelectorWrapper">' + customGenerator() + stationSelectorGenerator()  + '</div>';
			configureButton();
	/*var graphHeight = window.getComputedStyle(document.getElementById("customGraph")).getPropertyValue('height');
      document.getElementById("customSelectorWrapper").style.height = graphHeight;*/

    }
  }
  function readForm() {
	var formInput = document.getElementsByClassName("customForm");
	var locations ='';
	var i;
	for (i = 0; i < formInput.length; i++){
		if (formInput[i].type == "checkbox"){
			if (formInput[i].checked){
					locations += formInput[i].value + ",";
			}
		}
	}
	locations = locations.slice(0, -1);
	if (locations.length < 1){
		window.alert("Select at least one station.");
	}
	if (locations.length > 0){
			time = Math.floor(((new Date()).getTime() /1000)-5);
			var timeToSend ='';
			//timeToSend += (time - 60) + " AND " + time;
			timeToSend = "1518165554 and 1518165566";
			var per = 'min';
			handleQuery('precipitation', locations, timeToSend, per, currentPage);		
	}
  }
  function toggleAll(source){
	  var allSelectorBoxes = document.getElementsByClassName("customForm");
	  var i;
	  for (i = 0; i < allSelectorBoxes.length; i++){
		  allSelectorBoxes[i].checked = source.checked;
	  }
  }

  function sortByKey(array, key) {
    return array.sort(function(a, b) {
        var x = a[key]; var y = b[key];
        return ((x < y) ? -1 : ((x > y) ? 1 : 0));
    });
  }

  function check_internet_connection() {
  	var rand = Math.round(Math.random() * 10000);
	var request = new XMLHttpRequest();
  	var file = "/empty.html";
  	request.open('HEAD', file + "?rand=" + rand, true);
  	request.send();
  	request.addEventListener("readystatechange", processRequest, false);

  	function processRequest() {
  		if(request.readyState == 4) {
  			if(request.status >= 200 && request.status < 304) {
  				document.getElementById("error-message").innerHTML = '';
				return "true";
  			} else {
  				document.getElementById("error-message").innerHTML = errorhtml;
				return "false";
  			}
  		}
  	}
  }

  function measurement() {
  	var checkBox = document.getElementById("switch-selection-option-check");
  	if (checkBox.checked == true) {
  		measurementSystem = 'metric';
  	} else {
  		measurementSystem = "imperial";
  	}
  }
  function drawDownfallGraph()
  {
	  downfallGraph();
	  if (currentPage == 'dashboard')
	  {
		DownfallGraphChart.update({
			title: {
				text: 'Cumulative downfall in the last hour of all stations'
			},
			yAxis: {
				title: {
					text: 'Downfall in cm'
				}
			}
		})
		var locations ="";
		var time = Math.floor((new Date()).getTime()/1000/60)*60;
		console.log(time);
		for(object in stationList){
			locations += stationList[object]['stationNumber'] + ","}
		locations = locations.slice(0, -1);
		console.log(locations);
		//setInterval(function(){handleQuery('precipitation', locations, time + ' and ' + time, 'min')}, 6000)
	  }
	  if (currentPage == 'rain'){
		DownfallGraphChart.update({
			title: {
				text: 'Rain in the last hour per station'
			},
			yAxis: {
				title: {
					text: 'rain in cm'
				},
			}
	  })}
		if (currentPage == 'snow'){
			DownfallGraphChart.update({
				title: {
					text: 'Snow in the last hour per station'
				},
				yAxis: {
						title: {
						text: 'snow in cm'
					}
				}
			})
		}
	  }
//setInterval(function(){handleQuery()}, 1000)