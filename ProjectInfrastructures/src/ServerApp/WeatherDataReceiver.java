package ServerApp;

class WeatherDataReceiver {
    private ServerApp _sApp;
    private Boolean isStopped;
    private int port;


    WeatherDataReceiver(ServerApp _serverApp, int _port) {
        this.isStopped = true; // Disable by default
        this._sApp = _serverApp;
        this. port = _port;
    }

    void start() {
        this.isStopped = false; // Enable
        // Create a new thread
        this._sApp.getThreadPool().execute(new WeatherDataListener(this, this._sApp, this.port));
    }

    void stop() {
        this.isStopped = true; // Disable
    }

    Boolean getIsStopped() {
        return this.isStopped;
    }
}
