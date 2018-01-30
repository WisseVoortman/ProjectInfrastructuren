package ServerApp;

import java.util.*;

public class StationBuffer {

	private int id;
	private LinkedList<Object> queue; // queue that will contain arrays with data

	public StationBuffer(int id){
		this.id = id;
		this.queue = new LinkedList<Object>(); // queue that will contain data 
	}
		
		
	public void testone(){
		System.out.println("donders dikke lul man");
		System.out.println("my id is: " + id);
		
	}
	
	public void printqueue(){
		System.out.print(queue); // prints queue content
	}
	
	public void addArrayToQueue(){
		List<Object> myList = new ArrayList<Object>();
		myList.add("succesfully added a string to the myList"); // adds a string to the queue
		myList.add("succesfully added a second string to the myList"); // adds a string to the queue
		System.out.println(myList);
		this.queue.add(myList); // adds a string to the queue
		System.out.println("this is the fucking queue: " + this.queue);
	}
	
	/*
	 * in order to correct the temperature value we should store 30 values after which the corrected temperature value can be calculated
	 * after recieving the xml the data should be parsed and stored in instances of the stationbuffer class 1 station per instance. 
	 * we keep track of the stationbuffer instances in a parent bufferclass that has a map to containing key value pairs of station number and instance of stationbuffer.
	 * */
		

}
