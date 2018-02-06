function stationSelectorGenerator (){
	var selectorCheckbox = '<input class="stationSelectorCheckbox" type="checkbox" value="test"></br>';
	var stationList = [{stationNumber: 418580, Name: "Saidpur"},{stationNumber: 418620, Name: "Lalmonirhat"},{stationNumber: 419170, Name: "Dacca"},{stationNumber: 419410, Name: "Chandpur"},{stationNumber: 423690, Name: "Lucknow/Amausi"},{stationNumber: 423790, Name: "Gorakhpur"},{stationNumber: 424750, Name: "Allahabad"},{stationNumber: 425910, Name: "Gaya"},{stationNumber: 427010, Name: "M.O. Ranchi"},{stationNumber: 428090, Name: "Calcutta"},{stationNumber: 428950, Name: "Balasore"},{stationNumber: 429710, Name: "Bhubaneswar"},{stationNumber: 529080, Name: "Wudaoliang"},{stationNumber: 552790, Name: "Baingoin"},{stationNumber: 552990, Name: "Nagqu"},{stationNumber: 554720, Name: "Xainza"},{stationNumber: 555780, Name: "Xigaze"},{stationNumber: 555910, Name: "Lhasa"},{stationNumber: 556640, Name: "Tingri"},{stationNumber: 556960, Name: "Lhunze"},{stationNumber: 557730, Name: "Pagri"},{stationNumber: 560040, Name: "Tuotuohe"},{stationNumber: 560180, Name: "Zadoi"},{stationNumber: 560210, Name: "Qumarleb"},{stationNumber: 560290, Name: "Yushu"},{stationNumber: 561060, Name: "Sog Xian"},{stationNumber: 561160, Name: "Dengqen"},{stationNumber: 561250, Name: "Nangqen"},{stationNumber: 561370, Name: "Qamdo"},{stationNumber: 561440, Name: "Dege"},{stationNumber: 562470, Name: "Batang"},{stationNumber: 563120, Name: "Nyingchi"},{stationNumber: 564440, Name: "Deqen"},{stationNumber: 567390, Name: "Tengchong"},{stationNumber: 567480, Name: "Baoshan"},{stationNumber: 568380, Name: "Ruili"},{stationNumber: 749238, Name: "Ondal"}];
	var stationListSelectorBoxes = "";

	stationList = sortByKey(stationList, 'Name')

	for (object in stationList){
		console.log(stationList[object].stationNumber);
		stationListSelectorBoxes += '<label><input class="stationSelectorCheckbox" type="checkbox" value=' + stationList[object].stationNumber + '>' + stationList[object].Name + '</label>' +'<br>';
	}




	var stationSelector = [
		'<div class="downfallSelectorWrapper" id="downfallSelectorWrapper">',
			'<div class="stationSelector" id="downfallSelector">',
				'<label><input class="stationSelectorCheckbox" type="checkbox" onClick="toggleAll(this);" value="selectAll">Select All</label><br>',
				stationListSelectorBoxes,
			'</div>',
			'<button type="button" id="selectorSubmitButton" onclick="readForm();">Submit</button>',
		'</div>',
		'<div id="test">',
		'</div>'].join("\n");
	return stationSelector;
		}