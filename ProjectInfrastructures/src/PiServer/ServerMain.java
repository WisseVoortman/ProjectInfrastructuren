package PiServer;

import ServerApp.Measurement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class that manages all servers and contains shared references
 */
class ServerMain {
    private DataReceiver dataReceiver;
    private ExecutorService serverPool, transmitPool, dataPool, parserPool;
    private ArrayList<String> parseQueue;
    private ArrayList<Measurement> transmitQueue;
    private HashMap<Integer, ArrayList<Measurement>> compareBuffer;

    /**
     *
     * @param _portData Port number for incoming data
     * @param _transmitData Port to transmit data to
     */
    ServerMain(int _portData, int _transmitData) {
        // Create comparison buffer
        this.compareBuffer = new HashMap<>();
        // Create queue's
        this.parseQueue = new ArrayList<>();
        this.transmitQueue = new ArrayList<>();

        // Create thread pools
        this.serverPool = Executors.newFixedThreadPool(5);
        this.transmitPool = Executors.newFixedThreadPool(100);
        this.dataPool = Executors.newFixedThreadPool(800);
        this.parserPool = Executors.newFixedThreadPool(100);

        // Assign threads to the servers
        this.serverPool.execute(new DataReceiver( this, _portData ));

        // Create parsers
        ArrayList<XmlParser> parsers = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            XmlParser xmlP = new XmlParser(this);
            parsers.add(xmlP);
            this.parserPool.execute(xmlP);
        }

        // Create transmitters
        ArrayList<DataTransmitter> transmitters = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            DataTransmitter dt = new DataTransmitter(this, "localhost", _transmitData);
            transmitters.add(dt);
            this.transmitPool.execute(dt);
        }

    }

    public void run() {
        if( this.dataReceiver != null )
            this.dataReceiver.run();

    }

    public void stop() {
        if( this.dataReceiver != null )
            this.dataReceiver.stop();
    }

    ExecutorService getDataPool() {
        return this.dataPool;
    }

    ExecutorService getServerPool() {
        return this.serverPool;
    }

    ExecutorService getTransmitPool() {
        return this.transmitPool;
    }

    ExecutorService getParserPool() {
        return this.parserPool;
    }

    synchronized String popParseQueue() {
        String s = null;
        if(this.parseQueue.size() > 0)
            s = this.parseQueue.remove(0);
        return s;
    }

    synchronized void pushParseQueue(String s) {
        this.parseQueue.add(s);
    }

    synchronized Measurement popTransmitQueue() {
        Measurement m = null;
        if(this.transmitQueue.size() > 0)
            m = this.transmitQueue.remove(0);
        return m;
    }

    synchronized void pushTransmitQueue(Measurement m) {
        this.transmitQueue.add(m);
    }

    synchronized void pushCompareBuffer(int station, Measurement m) {
        try {
            if(!this.compareBuffer.containsKey(station))
                this.compareBuffer.put(station, new ArrayList<>());

            this.compareBuffer.get(station).add(m);

            if(this.compareBuffer.get(station).size() > 30)
                this.compareBuffer.get(station).remove(0);

        }catch(Exception e){e.printStackTrace();}
    }

    synchronized ArrayList<Measurement> popCompareBuffer(int station) {
        try {
            if(!this.compareBuffer.containsKey(station))
                this.compareBuffer.put(station, new ArrayList<>());

            // Return a copy, not the original
            return new ArrayList<>(this.compareBuffer.get(station));
        }catch(Exception e){e.printStackTrace();}

        return null;
    }
}
