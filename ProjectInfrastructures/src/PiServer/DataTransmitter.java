package PiServer;


import ServerApp.Measurement;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class DataTransmitter implements Runnable{
    private ServerMain model;
    private String serverIp;
    private int serverPort;

    public DataTransmitter(ServerMain model, String _serverIp, int _serverPort) {
        this.model = model;
        this.serverIp = _serverIp;
        this.serverPort = _serverPort;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Socket sock = new Socket(this.serverIp, this.serverPort);
                ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());

                while(true) {
                    Measurement m = this.model.popTransmitQueue();
                    if(m != null) {
                        //Socket sock = new Socket(this.serverIp, this.serverPort);
                        //ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());

                        oos.writeObject(m);
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (ConnectException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
