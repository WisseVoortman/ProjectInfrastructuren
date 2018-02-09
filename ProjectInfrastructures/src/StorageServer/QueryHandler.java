package StorageServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class that listens to incoming connections and handles them
 */
public class QueryHandler implements Runnable {
    private ServerMain model;
    private boolean running;
    private ServerSocket receiver;
    private final int QUERY_PORT;

    /**
     *
     * @param model Main model reference
     * @param _pQ Port number that the servers listens to for incoming connections.
     */
    QueryHandler(ServerMain model, int _pQ) {
        this.running = false;
        this.model = model;
        this.QUERY_PORT = _pQ;
    }

    @Override
    public void run() {
        if( this.running )
            return;

        this.running = true;

        // Open a new listening socket
        if( this.receiver == null || this.receiver.isClosed() ) {
            try {
                this.receiver = new ServerSocket(this.QUERY_PORT);
                System.out.println( "Query Handler started." );
            } catch( IOException e ) {
                System.out.println( "Failed to create socket.\t" + e.toString() );
            }
        }
        System.out.println( "Waiting for query clients..." );
        // Listen to new incoming connections
        while( this.running ) {
            try {
                // Accept a socket
                Socket clientSocket = this.receiver.accept();
                // Create new thread and assign it to the new client
                System.out.println("Accepted query client.");
                this.model.getQueryPool().execute(new QueryClient(this.model, clientSocket));
            } catch( IOException e ) {
                System.out.println( "Failed to accept client.\t" + e.toString() );
            }
        }
    }

    public void stop() {
        if( !this.running )
            this.running = false;

        try {
            this.receiver.close();
        } catch( IOException e ) {
            System.out.println( "Failed to close socket.\t" + e.toString() );
        }
    }
}
