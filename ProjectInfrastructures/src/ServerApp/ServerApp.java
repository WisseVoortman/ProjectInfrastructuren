package ServerApp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
 * MAIN Class for the PI application
 */
public class ServerApp {
    private WeatherDataReceiver weatherReceiver;
    private ExecutorService _threadPool;

    public ServerApp() {
        // Assign a thread pool of 20 to this server
        this._threadPool = Executors.newFixedThreadPool(20);

        this.weatherReceiver = new WeatherDataReceiver(this, 26555);
        this.weatherReceiver.start();
    }
    /*
     * main method that starts the application
     */
    public static void main(String[] args) {
    	new ServerApp();
    }
      
    ExecutorService getThreadPool() {
        return this._threadPool;
    }
}
