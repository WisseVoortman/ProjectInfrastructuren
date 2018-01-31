
  function all() {
    if (document.getElementById('tempGaugeOut') !== null) {
      document.getElementById("tempGaugeOut").innerHTML = '<div class="dashboardItem" id="tempGauge"></div>';
    }
    if (document.getElementById('downfallGraphOut') !== null) {
      document.getElementById("tempGaugeOut").innerHTML = '<div class="dashboardItem" id="tempGauge"></div>';  
    }
    if (document.getElementById('custom_selector_out') !== null) {
      document.getElementById('custom_selector_out').innerHTML = '<div class="selector" id="custom_selector"><form action="#" onsubmit="return false;"><table class="selector-table"><tr></tr><tr><th>start date and time:</th><td><input type="date" id="startDate"></td><td><input type="time" id="startName"></td></tr><tr><th>end date and time:</th><td><input type="date" id="endDate"></td><td><input type="time" id="endTime"></td></tr><tr><th colspan="3"><button type="submit">Send</button></th></tr></table></form></div>';
    }  
  }
  function temp() {
    document.getElementById("tempGauge").innerHTML = '<div class="dashboardItem" id="tempGauge"></div>';
    document.getElementById("downfallGraph").innerHTML = '<div class="dashboardItem" id="downfallGraphOut"></div>'; 
    document.getElementById("custom_selector").innerHTML = '<div id="custom_selector_out></div>"';
  }
  

  

  /*
  $("#button-selection-option-temp").click(function(){
    $("#tempGaugeOut").html('<div class="dashboardItem" id="tempGauge"></div>');
    $("#downfallGraphOut").html('');
    $("#custom_selector").hide(1000);
  });
  $("#button-selection-option-downfall").click(function(){
    $("#downfallGraph").show(1000);
    $("#tempGauge").hide(1000);
    $("#custom_selector").hide(1000);
  });
  $("#button-selection-option-custom").click(function(){
    $("#custom_selector").show(1000);
    $("#downfallGraph").hide(1000);
    $("#tempGauge").hide(1000);
  });
  $("#button-selection-option-snowfall").click(function(){
    //hier moet nog de showfall graph
    $("#custom_selector").hide(1000);
    $("#downfallGraph").hide(1000);
    $("#tempGauge").hide(1000);
  });
  */

