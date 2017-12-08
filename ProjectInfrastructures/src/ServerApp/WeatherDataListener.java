package ServerApp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class WeatherDataListener implements Runnable {
    private WeatherDataReceiver weatherDataReceiver;
    private int port;
    private ServerSocket listener;
    private ServerApp _sApp;
    private int lastId;

    WeatherDataListener( WeatherDataReceiver _weatherDataReceiver, ServerApp _serverApp, int _port ) {
        this.weatherDataReceiver = _weatherDataReceiver;
        this.port = _port;
        this._sApp = _serverApp;
        this.lastId = 0;

        // Open a socket
        try {
            listener = new ServerSocket(this.port);
            System.out.println("Started server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        if( this.weatherDataReceiver.getIsStopped() )
            return; // We are not supposed to be running

        while( !this.weatherDataReceiver.getIsStopped() ) {
            try{
                Socket client = listener.accept();
                this._sApp.getThreadPool().execute(new WeatherDataClient(client, ++lastId));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
