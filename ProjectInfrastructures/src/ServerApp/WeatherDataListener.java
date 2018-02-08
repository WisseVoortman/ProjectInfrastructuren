package ServerApp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class WeatherDataListener implements Runnable {
    private WeatherDataReceiver weatherDataReceiver;
    private StationBufferMap generalBuffer;
    private int port;
    private ServerSocket listener;
    private ServerApp serverApp;
    private int lastId;

    WeatherDataListener( WeatherDataReceiver _weatherDataReceiver, ServerApp serverApp, int _port, StationBufferMap generalBuffer) {
        this.weatherDataReceiver = _weatherDataReceiver;
        this.port = _port;
        this.serverApp = serverApp;
        this.lastId = 0;
        this.generalBuffer = generalBuffer;

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
            return; 
        
        /*
         * accepts incomming connections for the socket
         * creates a new weatherDataClient for the socket
         */
        while( !this.weatherDataReceiver.getIsStopped() ) {
            try{
                Socket client = listener.accept();
                this.serverApp.getThreadPool().execute(new WeatherDataClient(this.serverApp, client, ++lastId, this.generalBuffer));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
