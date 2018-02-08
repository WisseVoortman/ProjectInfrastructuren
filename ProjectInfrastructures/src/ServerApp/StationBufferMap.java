package ServerApp;

import java.util.HashMap;
import java.util.LinkedList;


public class StationBufferMap {

	private int id;
	@SuppressWarnings("rawtypes")
	private HashMap map; // map that contains the stationnumbers and their stationbuffers.
	private LinkedList<Object> sendQueue; // queue that will contain arrays with data for sending to vm
	
	// constructor for the stationBufferMap
	public StationBufferMap(){
		id = 1;
		this.map = new HashMap<>(); // contains the stationnumbers and their stationbuffers.
		this.sendQueue = new LinkedList<Object>(); // queue that will contain data for sending to vm
		
	} // end of constructor
	
	// getter for the map
	public HashMap getmap(){
		return this.map;
	}
	
	// getter for the sendQueue
	public LinkedList<Object> getSendQueue(){
		return this.sendQueue;
	}
	
	/*
	 * method for adding a dataArray to the sendqueue
	 * this method is synchronized because multiple threads have to acces the sendQueue variable
	 */
	public synchronized void add(LinkedList<String> dataArray){
		this.sendQueue.add(dataArray);	
	}
	
	/*
	 * method for removing a dataArray to the sendqueue 
	 * this method is synchronized because multiple threads have to remove data from the sendQueue variable
	 */
	public synchronized LinkedList<String> getAndRemove(){
		LinkedList<String> data = null;
		if(getSendQueueSize() > 0){
			data = (LinkedList<String>) this.sendQueue.removeFirst();
		}
		return data;
	}
	// method for getting the sendqueue size.
	public synchronized int getSendQueueSize(){
		return this.sendQueue.size();
	}
	
}//end of class
