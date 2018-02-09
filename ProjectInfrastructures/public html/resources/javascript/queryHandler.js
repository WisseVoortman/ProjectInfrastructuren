function handleQuery(fields, stations, time, per, sender, currentPage){
	var fields = 'timestamp,' + fields;
	console.log(fields);
	console.log('queryStarted');
	var sendThis = 'fields=' + fields + '&stations=' + stations + '&time=' + time + '&timeUnit=' + per;
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
	else{
		console.log(data);
		var testData = '{"station":5000,"info":[{"time":1518129490487, "temp":20}, {"time":1518129326112, "temp":30}]};{"station":6000,"info":[{"time":100, "temp":20}, {"time":200, "temp":30}]}';
		var array =	testData.split(";");
		for (var i = DownfallGraphChart.series.length - 1; i>-1; i--){
			DownfallGraphChart.series[i].remove();
		}
		for (object in array){
			var graphData = JSON.parse(array[object]);
			console.log(graphData, "dit is graphData")
			DownfallGraphChart.addSeries(
				{type: 'spline', name:graphData['station'].toString(), data:[(function(){
					var data = [];
					console.log("hello");
					for (g in graphData[object]){
						console.log(data);
						data.push({
							x: graphData[object][g]['timestamp']*1000,
							y: graphData[object][g]['precipitation']
						})
					}return data;
				})]}
			);
		}
	}
}