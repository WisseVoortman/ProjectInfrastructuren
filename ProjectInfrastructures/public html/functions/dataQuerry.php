<?php
	$HOST = '145.33.225.7';
	$PORT = 30022;
	$SOCKET = socket_create(AF_INET, SOCK_STREAM, 0);
	
	$CONNECTION = socket_connect($SOCKET, $HOST, $PORT)
	or die("error: Unable to connect to Database\n");

	$AUTHENTICATION_ID = "";
	$FIELDS = $_POST['fields'];
	$STATIONS = $_POST['stations'];
	$WHEN = $_POST['when'];
	$TIME = $_POST['time'];
	$TIMEUNIT = $_POST['timeUnit'];
	
	$MESSAGE = $AUTHENTICATION_ID . "SELECT" . $FIELDS . FROM . $STATIONS . $WHEN . $TIME . "PER" . $TIMEUNIT;
	
	socket_write($sock, $MESSAGE . "\n", strlen($MESSAGE)+1);
	
	$DATA = socket_read($SOCKET, 10000, PHP_NORMAL_READ)
		or die("error failed to recieve data from the Database\n");
	
	socket_close($SOCKET);
	
	ECHO $DATA;
	
?>