package ServerApp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
 * MAIN Class for the PI application
 */
public class ServerApp {
	
	private StationBufferMap stationBufferMap;
	
    private WeatherDataReceiver weatherReceiver;
    private ExecutorService recieverThreadPool;
    private ExecutorService parserThreadPool;
    private ExecutorService senderThreadPool;
    
    
    public ServerApp() {
        
    	this.stationBufferMap = new StationBufferMap();
    	
    	// Assign a thread pool of 20 to this server
        this.recieverThreadPool = Executors.newFixedThreadPool(800);
        this.parserThreadPool = Executors.newFixedThreadPool(600);
        this.senderThreadPool = Executors.newFixedThreadPool(1);

        this.weatherReceiver = new WeatherDataReceiver(this, 26555, stationBufferMap);
        this.weatherReceiver.start();
        
        
    }
    /*
     * main method that starts the application
     */
    public static void main(String[] args) {
    	new ServerApp();
    }
      
    public ExecutorService getThreadPool() {
        return this.recieverThreadPool;
    }
    public ExecutorService getParserPool() {
        return this.parserThreadPool;
    }
    public ExecutorService getSenderPool() {
        return this.senderThreadPool;
    }
    
}
