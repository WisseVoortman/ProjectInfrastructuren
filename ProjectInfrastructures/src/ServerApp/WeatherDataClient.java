 package ServerApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class WeatherDataClient implements Runnable {
	private ServerApp serverApp;
	private Socket _socket;
    private int id;
    private String buffer; //contains the XML form from the weatherdata generator
    private StationBufferMap generalBuffer;

    WeatherDataClient(ServerApp serverApp,Socket _client, int id, StationBufferMap generalBuffer) {
        this.serverApp = serverApp;
    	this._socket = _client;
        System.out.println("Created new client!");
        this.id = id;
        this.buffer = "";
        this.generalBuffer = generalBuffer;
    }
    // starts the thread
    public void run() {
        String line;
        BufferedReader in = null;
        try{
            in = new BufferedReader(new InputStreamReader(this._socket.getInputStream()));

        } catch (IOException e) {
            System.out.println("in or out failed");
        }
        
        /*
         *  reads input from the socket
         *  when input is being received create a new parser and pass the input to it
         */
        
        while(this._socket.isConnected() && in != null){
            try{
                line = in.readLine();
                if(line != null) {
                    if(line.contains("<?xml")) {
                        // Print current buffer
                        if( !buffer.equals("") ){
                        	// prints thread id + weaterDataClientID + Buffer.
                            //System.out.println(Thread.currentThread().getId() + "\t" + id + "\t" + buffer);
                        	
                        	                        	
                        	// creates a parser assigned to parserThreadPool
                        	this.serverApp.getParserPool().execute(new Dom4jParser(this.serverApp, buffer, this.generalBuffer));
                        	
                        	this.buffer = line; // Clean buffer
                        }
                        	                    }else{
                        buffer += line;
                    }

                }
            }catch (IOException e) {
                System.out.println("Read failed");
                break;
            }
        }
    }
}
