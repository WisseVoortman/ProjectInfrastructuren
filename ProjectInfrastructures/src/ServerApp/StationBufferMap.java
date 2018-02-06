package ServerApp;

import java.util.HashMap;
import java.util.LinkedList;


public class StationBufferMap {

	private int id;
	@SuppressWarnings("rawtypes")
	private HashMap map; // contains the stationnumbers and their stationbuffers.
	private LinkedList<Object> sendQueue; // queue that will contain arrays with data for sending to vm
	public StationBufferMap(){
		id = 1;
		this.map = new HashMap<>(); // contains the stationnumbers and their stationbuffers.
		this.sendQueue = new LinkedList<Object>(); // queue that will contain data for sending to vm
		
	} // end of constructor
	
	
	public HashMap getmap(){
		return this.map;
	}
	
	//notes
	//((StationBuffer) this.map.get(id)).testone();    
    //((StationBuffer) this.map.get(id)).printqueue();
    //((StationBuffer) this.map.get(id)).addToQueue();
    //((StationBuffer) this.map.get(id)).printqueue();
		
	public void add(LinkedList<String> dataArray){
		this.sendQueue.add(dataArray);
	}
	
	public void print(){
		System.out.println(sendQueue);
		System.out.println("send queue size:" + this.sendQueue.size());
	}
	
}//end of class
