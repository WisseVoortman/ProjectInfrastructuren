function handleQuery(fields, stations, time, per){
	var fields = 'timestamp,' + fields;
	console.log(fields);
	console.log('queryStarted');
	var sendThis = 'fields=' + fields + '&stations=' + stations + '&time=' + time + '&timeUnit=' + per;
	console.log(sendThis);
	var dataBaseRequest = new XMLHttpRequest();
	dataBaseRequest.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200){
			document.getElementById("error-message").innerHTML = '';
			console.log(this.responseText);
			document.getElementById("queryTest").innerHTML = this.responseText;
		}
		if (this.status == (404 || 408)) {
  				document.getElementById("error-message").innerHTML = errorhtml;
				return "false";
  		}
	}
	dataBaseRequest.open("POST", "functions/dataQuerry.php", true);
	dataBaseRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	dataBaseRequest.send(sendThis);
var data = [{"station":5000,info:[{time:100, temp:20}, {time:200, temp:30}]},{"station":6000,info:[{time:100, temp:20}, {time:200, temp:30}]}]
	console.log(data);
}