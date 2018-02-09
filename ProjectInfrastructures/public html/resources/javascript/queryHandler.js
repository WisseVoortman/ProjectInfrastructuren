function handleQuery(fields, stations, time, per, sender, currentPage){
	var fields = 'timestamp,' + fields;
	console.log(fields);
	console.log('queryStarted');
	var sendThis = 'fields=' + fields + '&stations=' + stations + '&time=' + time + '&timeUnit=' + per + '&currentPage=' + currentPage;
	console.log(sendThis);
	var dataBaseRequest = new XMLHttpRequest();
	dataBaseRequest.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200){
			document.getElementById("error-message").innerHTML = '';
			handleReturn(this.responseText);
			
		}
		if (this.status == (404 || 408)) {
  				document.getElementById("error-message").innerHTML = errorhtml;
				return "false";
  		}
	}
	dataBaseRequest.open("POST", "functions/dataQuerry.php", true);
	dataBaseRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	dataBaseRequest.send(sendThis);

}

function handleReturn(data){
	if (data.slice(1,6) == 'ERROR'){
		console.log("we hebben een error");
	}
	if (data.slice(0,1) =='{'){
		console.log(data);
		var testData = '{"station":5000,"info":[{"time":1518129490487, "temp":20}, {"time":1518129326112, "temp":30}]};{"station":6000,"info":[{"time":1518129490487, "temp":40}, {"time":1518129326112, "temp":-30}]}';
		var array =	data.split(";");
		for (var i = DownfallGraphChart.series.length - 1; i>-1; i--){
			DownfallGraphChart.series[i].remove();
		}
		for (object in array){
			var graphData = JSON.parse(array[object]);
			var graphSerie = []
				for (g in graphData){
					for (t in graphData[g]){
						graphSerie.push(
							{x: graphData[g][t]['time'], y: graphData[g][t]['temp']}
						)
					}
				}
			DownfallGraphChart.addSeries(
				{type: 'spline', name:graphData['station'].toString(), data:graphSerie})
		}
	}
}