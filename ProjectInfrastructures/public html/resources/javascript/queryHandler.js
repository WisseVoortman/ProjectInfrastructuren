function handleQuery(){
	var hello = 'hello php';
	var sendThis = 'fields=' + hello + 'stations=' + 'when=' + 'time=' + 'timeUnit=';
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