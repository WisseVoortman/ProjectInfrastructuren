function handleQuery(){
	var fields = '';
	var stations = '';
	var when = '';
	var time = '';
	var timeUnit = '';
	
	var sendThis = 'fields=' + fields + 'stations=' + stations + 'when=' + when + 'time=' + time + 'timeUnit=' + timeUnit;
	var dataBaseRequest = new XMLHttpRequest();
	dataBaseRequest.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200){
			console.log(this.responseText);
			document.getElementById("queryTest").innerHTML = this.responseText;
		}
	};
	dataBaseRequest.open("POST", "functions/dataQuerry.php", true);
	dataBaseRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	dataBaseRequest.send(sendThis);
}