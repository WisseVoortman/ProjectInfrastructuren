package PiServer;

public class PiRunner {
    public static void main(String[] args) {
        PiMain server = new PiMain(26555, 30011 );
        server.run();
    }
}