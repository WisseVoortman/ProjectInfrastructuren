function handleQuery(){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200){
			(console.log(this.response))
		}
	};
	xmlhttp.open("POST", "/functions/dataQuerry.php", true);
	xmlhttp.send();
}