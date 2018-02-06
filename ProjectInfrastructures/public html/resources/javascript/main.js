var temphtml = '<div class="dashboardItem" id="tempGauge"></div>';
var downfallhtml = '<div class="dashboardItem" id="downfallGraph"></div> '
var customhtml = '<div class="selector" id="custom_selector"><form action="#" onsubmit="return false;"><table class="selector-table"><tr></tr><tr><th>start date and time:</th><td><input type="date" id="startDate"></td><td><input type="time" id="startName"></td></tr><tr><th>end date and time:</th><td><input type="date" id="endDate"></td><td><input type="time" id="endTime"></td></tr><tr><th colspan="3"><button type="submit">Send</button></th></tr></table></form></div>';
var allhtml = temphtml + "" + downfallhtml;
var errorhtml = '<p class="error dashboard-error-message"><b>No internet connection</b></p>';
var previousButton;


 function setDashboardItemWidth() {
	  var dashboardItems = document.getElementsByClassName("dashboardItem");
	  var i;
	  for (i=0; i < dashboardItems.length; i++) {
		  dashboardItems[i].style.cssFloat = 'none';
		  dashboardItems[i].style.width = '80%';
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
    } else if (document.getElementById("error-message") !== null) {
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
		document.getElementById("dashboard-items").innerHTML = allhtml;
		configureButton();
		downfallGraph();
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
	  previousButton = 'button-selection-option-downfall';
	  document.getElementById("dashboard-items").innerHTML = stationSelectorGenerator() + downfallhtml;
	  configureButton();
	  setDashboardItemWidth();
	  downfallGraph();
	  var graphHeight = window.getComputedStyle(document.getElementById("downfallGraph")).getPropertyValue('height');
	  document.getElementById("downfallSelectorWrapper").style.height = graphHeight;
    }
  }
  function custom() {
		if(check_id_not_null()) {
			buttonReset();
			previousButton = 'button-selection-option-custom';
			document.getElementById("dashboard-items").innerHTML = customhtml;
			configureButton();
    }
  }
  function readForm() {
	  var formInput = document.getElementsByTagName("input");
	  var locations = [];
	  var i;
	  for (i = 0; i < formInput.length; i++){
		if (formInput[i].type == "checkbox" && formInput[i].value != "selectAll"){
			if (formInput[i].checked){
				locations.push(formInput[i].value);
			}
		}
	  }
	  try{
		if (locations.length < 1) throw "Select at least one station";
	  }
	  catch(err){
		  window.alert(err);
		  console.log(err);
	  }
	  document.getElementById('test').innerHTML = locations;
  }
  function toggleAll(source){
	  var allSelectorBoxes = document.getElementsByTagName("input");
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
  				alert("connection exists!");
  				document.getElementById("error-message").innerHTML = '';
  			} else {
  				document.getElementById("error-message").innerHTML = errorhtml;
  			}
  		}
  	}

  	function measurement() {
  		var checkBox = document.getElementById("switch-selection-option-check");
  		if (checkBox.checked == true) {
  			alert("metric");
  		} else {
  			alert("Imperial");
  		}
  	}

  }
