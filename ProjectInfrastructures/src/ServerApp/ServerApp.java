package ServerApp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerApp {
    private WeatherDataReceiver weatherReceiver;
    private ExecutorService _threadPool;

    public ServerApp() {
        // Assign a thread pool of 10 to this server
        this._threadPool = Executors.newFixedThreadPool(20);

        this.weatherReceiver = new WeatherDataReceiver(this, 26555);
        this.weatherReceiver.start();
    }

    ExecutorService getThreadPool() {
        return this._threadPool;
    }
}
