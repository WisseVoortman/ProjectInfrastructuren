package StorageServer;

public class ServerRunner {
    public static void main(String[] args) { // /home/peasant/Storage/  c:/temp/proj/
       ServerMain server = new ServerMain(30011, 30022, "/home/peasant/Storage");
       server.run();
    }
}
