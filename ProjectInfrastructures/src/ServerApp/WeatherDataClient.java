package ServerApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class WeatherDataClient implements Runnable {
    private Socket _socket;
    private int id;
    private String buffer;

    WeatherDataClient(Socket _client, int id) {
        this._socket = _client;
        System.out.println("Created new client!");
        this.id = id;
        this.buffer = "";
    }

    public void run() {
        String line;
        BufferedReader in = null;
        try{
            in = new BufferedReader(new InputStreamReader(this._socket.getInputStream()));

        } catch (IOException e) {
            System.out.println("in or out failed");
        }

        while(this._socket.isConnected() && in != null){
            try{
                line = in.readLine();
                if(line != null) {
                    if(line.contains("<?xml")) {
                        // Print current buffer
                        if( !buffer.equals("") )
                        	// prints thread id + weaterDataClientID + Buffer.
                            System.out.println(Thread.currentThread().getId() + "\t" + id + "\t" + buffer);
                        	//test
                        	new Dom4jParser(buffer).run();
                        	//Dom4jParser a = new Dom4jParser();
                        	//a.parse();
                        	//test
                        
                        // Clean buffer
                        this.buffer = line;
                    }else{
                        buffer += line;
                    }

                }
            }catch (IOException e) {
                System.out.println("Read failed");
                break;
            }
        }
        System.out.println(1);
    }
}
