package ServerApp;

class WeatherDataReceiver {
    private ServerApp _sApp;
    private Boolean isStopped;
    private int port;
    private GeneralBuffer generalBuffer;


    WeatherDataReceiver(ServerApp _serverApp, int _port, GeneralBuffer generalBuffer) {
        this.isStopped = true; // Disable by default
        this._sApp = _serverApp;
        this. port = _port;
        this.generalBuffer = generalBuffer;
    }

    void start() {
        this.isStopped = false; // Enable
        // Create a new thread
        this._sApp.getThreadPool().execute(new WeatherDataListener(this, this._sApp, this.port, this.generalBuffer));
    }

    void stop() {
        this.isStopped = true; // Disable
    }

    Boolean getIsStopped() {
        return this.isStopped;
    }
}
