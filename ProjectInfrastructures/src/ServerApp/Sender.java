package ServerApp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

/*
 * Class responsible for sending measurments to the VM/Storrageserver.
 */
public class Sender implements Runnable{

	private Socket client;
	private ObjectOutputStream out;
	private StationBufferMap stationBufferMap;
	
	public Sender(StationBufferMap stationBufferMap) throws UnknownHostException, IOException{
		this.stationBufferMap = stationBufferMap;
		this.client = new Socket("145.37.37.120", 30011);
		this.out = new ObjectOutputStream(client.getOutputStream());
	}
	
	// starts the thread (this class is asigned to the senderThreadPool)
	public void run(){
		try {
			sendToVm();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Sending to VM failed  :" + e);
			e.printStackTrace();
		}
	}
	
	public void sendToVm() throws IOException{
		while(true){		
			// initializes a dataArray that contains the information to create a Measurment
			LinkedList<String> dataArray = stationBufferMap.getAndRemove();
			if(dataArray != null){// checks that the dataArray contains data
			
			// creates a measurment object
			Measurement m = new Measurement(dataArray.get(0), dataArray.get(1), dataArray.get(2), dataArray.get(3), dataArray.get(4), dataArray.get(5), dataArray.get(6), dataArray.get(7), dataArray.get(8), dataArray.get(9), dataArray.get(10), dataArray.get(11), dataArray.get(12), dataArray.get(13));
			
			//sample of data:
			//Measurement m2 = new Measurement("123456", "2009-09-13", "15:59:46", "-60.1", "-58.1", "1034.5", "1007.6", "123.7", "10.8", "11.28", "11.1", "010101", "87.4", "342");
			
			// writes the object m to the outputstream
			this.out.writeObject(m);
			
			// flushes the output stream
			this.out.flush();
			}	
		}
	} 
}