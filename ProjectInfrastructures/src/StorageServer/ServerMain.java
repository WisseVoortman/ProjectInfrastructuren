package StorageServer;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class that manages all servers and contains shared references
 */
class ServerMain {
    private DataReceiver dataReceiver;
    private QueryHandler queryHandler;
    private ExecutorService serverPool, queryPool, dataPool;
    public final String CUR_PATH;
    public int tVar = 0;

    /**
     *
     * @param _portData Port number for incoming data
     * @param path Absolute path to storage
     */
    ServerMain(int _portData, int _portQuery, String path) {
        this.CUR_PATH = path;

        // Create thread pools
        this.serverPool = Executors.newFixedThreadPool(5);
        this.queryPool = Executors.newFixedThreadPool(100);
        this.dataPool = Executors.newFixedThreadPool(800);

        // Assign threads to the servers
        this.serverPool.execute(new DataReceiver( this, _portData ));
        //this.serverPool.execute(new QueryHandler( this ));
    }

    public void run() {
        if( this.dataReceiver != null )
            this.dataReceiver.run();

        if( this.queryHandler != null )
            this.queryHandler.run();
    }

    public void stop() {
        if( this.dataReceiver != null )
            this.dataReceiver.stop();

        if( this.queryHandler != null )
            this.queryHandler.stop();
    }

    /**
     *
     * @return Thread pool used to handling queries
     */
    ExecutorService getQueryPool() {
        return this.queryPool;
    }

    /**
     *
     * @return Thread pool used for handling data processing
     */
    ExecutorService getDataPool() {
        return this.queryPool;
    }


}
