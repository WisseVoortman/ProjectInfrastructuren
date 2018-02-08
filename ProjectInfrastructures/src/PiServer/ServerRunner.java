package PiServer;

public class ServerRunner {
    public static void main(String[] args) { // /home/peasant/Storage/  c:/temp/proj/
        ServerMain server = new ServerMain(26555, 30011 );
        server.run();
    }
}