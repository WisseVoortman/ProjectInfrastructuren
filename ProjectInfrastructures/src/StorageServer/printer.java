package StorageServer;

public class printer implements Runnable {
    private ServerMain m;
    public printer(ServerMain model) {
        this.m = model;
    }

    public void run() {
        while(true) {
            System.out.println(this.m.tVar);
            try {
                Thread.sleep( 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
