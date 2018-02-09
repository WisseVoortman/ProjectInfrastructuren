<?php
	$HOST = '145.37.37.120';
	//145.37.37.120
	$PORT = 30022;
	$SOCKET = socket_create(AF_INET, SOCK_STREAM, 0);
	
	$CONNECTION = socket_connect($SOCKET, $HOST, $PORT)
	or die("error: Unable to connect to Database\n");

	$AUTHENTICATION_ID = "6SQ8JGZTBWQWIM9U";
	$FIELDS = $_POST['fields'];
	$STATIONS = $_POST['stations'];
	$TIME = $_POST['time'];
	$TIMEUNIT = $_POST['timeUnit'];
	
	$MESSAGE = $AUTHENTICATION_ID . " SELECT " . $FIELDS . " FROM " . $STATIONS . " BETWEEN " . $TIME . " PER " . $TIMEUNIT;
	
	socket_write($SOCKET, $MESSAGE . "\n\r\n", strlen($MESSAGE)+1);
	
	$DATA = socket_read($SOCKET, 100000, PHP_NORMAL_READ)
	or die("error failed to recieve data from the Database\n");
	
	socket_close($SOCKET);
	
	ECHO $DATA;
	
?>