package ServerApp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import threadpooltest.WorkerThread;

public class ThreadPoolManager {
	
	private ExecutorService executor;
	
	public ThreadPoolManager(int numbererOfThreads){
		
		ExecutorService executor = Executors.newFixedThreadPool(numbererOfThreads);
                
        // code below creates the workerthread
		//Runnable worker = new WorkerThread("workerthread");
        //executor.execute(worker);
        
        //executor.shutdown(); // shuts down the Threadpool
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	public void setExecutor(ExecutorService executor) {
		this.executor = executor;
	}
}
