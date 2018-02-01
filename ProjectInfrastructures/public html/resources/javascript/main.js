var temphtml = '<div class="dashboardItem" id="tempGauge"></div>';
var downfallhtml = '<div class="dashboardItem" id="downfallGraph"></div>';
var customhtml = '<div class="selector" id="custom_selector"><form action="#" onsubmit="return false;"><table class="selector-table"><tr></tr><tr><th>start date and time:</th><td><input type="date" id="startDate"></td><td><input type="time" id="startName"></td></tr><tr><th>end date and time:</th><td><input type="date" id="endDate"></td><td><input type="time" id="endTime"></td></tr><tr><th colspan="3"><button type="submit">Send</button></th></tr></table></form></div>';
var allhtml = temphtml + "" + downfallhtml;  

  function setDashboardItemWidth() {
	  var dashboardItems = document.getElementsByClassName("dashboardItem");
	  var i;
	  for (i=0; i < dashboardItems.length; i++) {
		  dashboardItems[i].style.cssFloat = 'none';
		  dashboardItems[i].style.maxWidth = '100%';
	  }
  }
  function check_id_not_null() {
    if(document.getElementById("dashboard-items") !== null) {
      return true;
    }
    return false;
  }
  function dashboardPage() {
    if(check_id_not_null()) {
      document.getElementById("dashboard-items").innerHTML = allhtml;
	  downfallGraph();
	  tempGauge();
    } 
  }
  function temp() {
    if(check_id_not_null()) {
      document.getElementById("dashboard-items").innerHTML = temphtml;
	  setDashboardItemWidth();
	  tempGauge();
    }
  }
  function downfall() {
    if(check_id_not_null()) {
      document.getElementById("dashboard-items").innerHTML = downfallhtml;
	  setDashboardItemWidth();
	  downfallGraph();
    }
  }
  function custom() {
		if(check_id_not_null()) {
			document.getElementById("dashboard-items").innerHTML = customhtml;
    }
  }
