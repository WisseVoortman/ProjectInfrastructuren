function handleQuery(){
	var fields = '1';
	var stations = '1';
	var when = ' BETWEEN ';
	var time = '1';
	var timeUnit = '1';
	console.log('queryStarted');
	var sendThis = 'fields=' + fields + '&stations=' + stations + '&when=' + when + '&time=' + time + '&timeUnit=' + timeUnit;
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