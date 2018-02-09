package StorageServer;

public class ServerRunner {
    public static void main(String[] args) {
       ServerMain server = new ServerMain(30011, 30022, "c:/temp/proj/");
       server.run();
    }
}
