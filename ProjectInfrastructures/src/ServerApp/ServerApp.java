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
    	
    	// create a thread pool for incomming connections
        this.recieverThreadPool = Executors.newFixedThreadPool(10);
        
        // create a thread pool for data processing
        this.parserThreadPool = Executors.newFixedThreadPool(10);
        
        // create a threadpool for outgoing connections
        this.senderThreadPool = Executors.newFixedThreadPool(5);

        // create and start weatherReceiver
        this.weatherReceiver = new WeatherDataReceiver(this, 26555, stationBufferMap);
        this.weatherReceiver.start();
        
        // create senderobjects and assign them to the senderthreadpool
        for(int i=0;i<2;i++){
        	this.senderThreadPool.execute(new Sender(this.stationBufferMap));
        }
        
    }
    /*
     * main method that starts the application
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
    	new ServerApp();
    }
      /*
       * getter for recieverThreadPool
       * @return revieverThreadPool
       */
    public ExecutorService getThreadPool() {
        return this.recieverThreadPool;
    }
    /*
     * getter for parserThreadPool
     * @return parserThreadPool
     */
    public ExecutorService getParserPool() {
        return this.parserThreadPool;
    }
    /*
     * getter for senderThreadPool
     * @return senderThreadPool
     */
    public ExecutorService getSenderPool() {
        return this.senderThreadPool;
    }
    
}
