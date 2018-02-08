package PiServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Threaded class used for processing incoming data.
 */
public class DataReceiverClient implements Runnable {
    private ServerMain model;
    private Socket clientSocket;

    /**
     * @param model Main model reference
     * @param clientSocket Active client socket
     */
    DataReceiverClient(ServerMain model, Socket clientSocket) {
        this.model = model;
        this.clientSocket = clientSocket;
    }
    @Override
    public void run() {
        BufferedReader in = null;
        String buffer = "";
        String line;
        try{
            in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

            while(this.clientSocket.isConnected() && in != null){
                try{
                    line = in.readLine();
                    if(line != null) {
                        if(line.contains("<?xml")) {
                            // Print current buffer
                            if( !buffer.equals("") ){
                                this.model.pushParseQueue(buffer);
                                buffer = line; // Clean buffer
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
        }catch (IOException e) {
            System.out.println("Failed to open input stream.");
        }
    }
}
