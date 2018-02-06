package ServerApp;

class WeatherDataReceiver {
    private ServerApp serverApp;
    private Boolean isStopped;
    private int port;
    private StationBufferMap generalBuffer;


    WeatherDataReceiver(ServerApp serverApp, int _port, StationBufferMap generalBuffer) {
        this.isStopped = true; // Disable by default
        this.serverApp = serverApp;
        this. port = _port;
        this.generalBuffer = generalBuffer;
    }

    void start() {
        this.isStopped = false; // Enable
        // Create a new thread
        this.serverApp.getThreadPool().execute(new WeatherDataListener(this, this.serverApp, this.port, this.generalBuffer));
    }

    void stop() {
        this.isStopped = true; // Disable
    }

    Boolean getIsStopped() {
        return this.isStopped;
    }
}
