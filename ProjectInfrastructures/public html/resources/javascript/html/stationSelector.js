var selectorCheckbox = '<input class="stationSelectorCheckbox" type="checkbox" value="test"></br>';
var stationList = ["SAIDPUR","LALMONIRHAT","DACCA/TEZGAON","CHANDPUR","LUCKNOW/AMAUSI","GORAKHPUR","ALLAHABAD/BAMHRAULI","GAYA","M.O. RANCHI","CALCUTTA/DUM DUM","BALASORE","BHUBANESWAR","WUDAOLIANG","BAINGOIN","NAGQU","XAINZA","XIGAZE","LHASA","TINGRI","LHUNZE","PAGRI CHINA","TUOTUOHE","ZADOI CHINA","QUMARLEB","YUSHU","SOG XIAN","DENGQEN","NANGQEN","QAMDO","DEGE","BATANG","NYINGCHI","DEQEN","TENGCHONG","BAOSHAN","RUILI","ONDAL"];
var stationListSelectorBoxes = "";

var i;
stationList.sort();
for (i=0; i < stationList.length; i++){
	stationListSelectorBoxes += '<input class="stationSelectorCheckbox" type="checkbox" value=' + stationList[i] + '>' + stationList[i] + '<br>';
}




var stationSelector = ['<div class="stationSelector" id="downfallSelector">',
	stationListSelectorBoxes,
	'</div>'].join("\n");