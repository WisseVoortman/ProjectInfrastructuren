package sandbox;

import java.util.*;

public class StationBuffer {

	private LinkedList<Object> queue; // queue that will contain arrays with data

	public StationBuffer(){
		this.queue = new LinkedList<Object>(); // queue that will contain data 
	}
		
		
	public void testone(){
		System.out.print("donders dikke lul man");
	}
	
	public void printqueue(){
		System.out.print(queue); // prints queue content
	}
	
	public void addToQueue(){
		this.queue.add("succesfully added a string to the queue of the stationbuffer"); // adds a string to the queue
	}
	
	/*
	 * in order to correct the temperature value we should store 30 values after which the corrected temperature value can be calculated
	 * after recieving the xml the data should be parsed and stored in instances of the stationbuffer class 1 station per instance. 
	 * we keep track of the stationbuffer instances in a parent bufferclass that has a map to containing key value pairs of station number and instance of stationbuffer.
	 * */
		

}
