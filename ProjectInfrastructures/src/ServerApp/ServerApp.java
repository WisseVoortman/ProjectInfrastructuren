package ServerApp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
 * MAIN Class for the PI application
 */
public class ServerApp {
	
	private GeneralBuffer generalBuffer;
	
    private WeatherDataReceiver weatherReceiver;
    private ExecutorService _threadPool;
    
    
    public ServerApp() {
        
    	this.generalBuffer = new GeneralBuffer();
    	
    	// Assign a thread pool of 20 to this server
        this._threadPool = Executors.newFixedThreadPool(900);

        this.weatherReceiver = new WeatherDataReceiver(this, 26555, generalBuffer);
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
