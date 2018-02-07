package ServerApp;

import java.io.IOException;
import java.net.UnknownHostException;
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
    
    
    public ServerApp() throws UnknownHostException, IOException {
        
    	this.stationBufferMap = new StationBufferMap();
    	
    	// Assign a thread pool of 20 to this server
        this.recieverThreadPool = Executors.newFixedThreadPool(800);
        this.parserThreadPool = Executors.newFixedThreadPool(800);
        this.senderThreadPool = Executors.newFixedThreadPool(20);

        this.weatherReceiver = new WeatherDataReceiver(this, 26555, stationBufferMap);
        this.weatherReceiver.start();
        
        for(int i=0;i<20;i++){
        	this.senderThreadPool.execute(new Sender(this.stationBufferMap));
        }
        
    }
    /*
     * main method that starts the application
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
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
