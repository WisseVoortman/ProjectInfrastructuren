<?php
	$HOST = 127.0.0.1;
	$PORT = 30022;
	$SOCKET = socket_create(AF_INET, SOCK_STREAM, 0);
	
	$CONNECTION = socket_create($SOCKET, $HOST, $PORT)
	or die("error: Unable to connect to Database\n";

	$AUTHENTICATION_ID = "";
	$FIELDS = $_POST[];
	$STATIONS = $_POST[];
	$WHEN = $_POST[];
	$TIME = $_POST[];
	$TIMEUNIT = $_POST[];
	
	$MESSAGE = $AUTHENTICATION_ID . "SELECT" . $FIELDS . FROM . $STATIONS . $WHEN . $TIME . "PER" . $TIMEUNIT;
	
	socket_write($sock, $MESSAGE . "\n", strlen($MESSAGE)+1);
	
	$DATA = socket_read($SOCKET, 10000, PHP_NORMAL_READ)
		or die("error failed to recieve data from the Database\n");
	
	socket_close($SOCKET);
	
	ECHO $DATA;
	
?>