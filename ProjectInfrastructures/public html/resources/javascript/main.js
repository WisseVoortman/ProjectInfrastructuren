$(document).ready(function() {
  $("#button-selection-option-all").click(function(){
    $("#tempGauge").show(1000);
    $("#downfallGraph").show(1000);
    $("#custom_selector").show(1000);
  });
  $("#button-selection-option-temp").click(function(){
    $("#tempGauge").show(1000);
    $("#downfallGraph").hide(1000);
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
});

