package ServerApp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

public class Sender implements Runnable{

	private Socket client;
	private ObjectOutputStream out;
	private StationBufferMap stationBufferMap;
	
	public Sender(StationBufferMap stationBufferMap) throws UnknownHostException, IOException{
		this.stationBufferMap = stationBufferMap;
		this.client = new Socket("145.37.37.120", 30011);
		this.out = new ObjectOutputStream(client.getOutputStream());
	}
	
	public void run(){
		try {
			sendToVm();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendToVm() throws IOException{
		while(true){
			synchronized(stationBufferMap.getSendQueue()){
				LinkedList<String> dataArray = (LinkedList<String>) stationBufferMap.getSendQueue().removeFirst();
				Measurement m = new Measurement(dataArray.get(0), dataArray.get(1), dataArray.get(2), dataArray.get(3), dataArray.get(4), dataArray.get(5), dataArray.get(6), dataArray.get(7), dataArray.get(8), dataArray.get(9), dataArray.get(10), dataArray.get(11), dataArray.get(12), dataArray.get(13));
				this.out.writeObject(m);
				this.out.flush();	
			}
			
		}
	} 
}


//outside loop			
////1 create a socket named client	
//
////2 create objectoutputstream		
//			
//
//
//inside loop			
//	
//	synchronized(){
//	
//	// 3 construct measurment from first array element
//
//	// 4 write objectoutputstream
//
//	// 5 flush outputstream
//	
//	}
//	
//	
//outside loop
//	// 6 close outputstream
//
//	// 7 close client socket