function handleQuery(type, locations, timeToSend){
	var fields = 'timestamp,' + type;
	console.log(fields);
	var stations = locations;
	var time = timeToSend;
	var timeUnit = 'sec';
	console.log('queryStarted');
	var sendThis = 'fields=' + fields + '&stations=' + stations + '&time=' + time + '&timeUnit=' + timeUnit;
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
}