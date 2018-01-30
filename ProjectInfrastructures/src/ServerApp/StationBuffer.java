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
		System.out.println(this.queue.size());
		System.out.println("this is the fucking queue: " + this.queue);
	}
	
	public void addDataArrayToQueue(
			int stationnumber, 
			String date,
			String time,
			String temperature,
			String dewpoint,
			String airpresurestationlevel,
			String airpresuresealevel,
			String visability,
			String windspeed,
			String perception,
			String snowfallen,
			String specialcircumstances,
			String cloudiness,
			String winddirection){
		
		List<Object> dataArray = new ArrayList<Object>(); // initiating an arraylist to add to the queue
		
		dataArray.add(stationnumber); // filling the arraylist with data before it is added to the queeu
		dataArray.add(date);
		dataArray.add(time);
		dataArray.add(temperature);
		dataArray.add(dewpoint);
		dataArray.add(airpresurestationlevel);
		dataArray.add(airpresuresealevel);
		dataArray.add(visability);
		dataArray.add(windspeed);
		dataArray.add(perception);
		dataArray.add(snowfallen);
		dataArray.add(specialcircumstances);
		dataArray.add(cloudiness);
		dataArray.add(winddirection);
		
		this.queue.add(dataArray); // adds the dataArray to the queue.
		printqueue();
	}
	
	/*
	 * in order to correct the temperature value we should store 30 values after which the corrected temperature value can be calculated
	 * after recieving the xml the data should be parsed and stored in instances of the stationbuffer class 1 station per instance. 
	 * we keep track of the stationbuffer instances in a parent bufferclass that has a map to containing key value pairs of station number and instance of stationbuffer.
	 * */
		

}
