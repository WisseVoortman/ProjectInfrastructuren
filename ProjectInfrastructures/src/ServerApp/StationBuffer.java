package ServerApp;

import java.util.*;

public class StationBuffer {

	private String id;
	private LinkedList<Object> queue; // queue that will contain arrays with data

	public StationBuffer(String id){
		this.id = id;
		this.queue = new LinkedList<Object>(); // queue that will contain data 
	}
		
		
	public void printID(){
		System.out.println("donders dikke lul man");
		System.out.println("my id is: " + id);
		
	}
	
	public void printqueue(){
		System.out.println("queue size: " + this.queue.size());
		System.out.println("Current Queue: " + this.queue);
	}
	
	public void addDataArrayToQueue(
			String stationnumber, 
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
		
		LinkedList<String> dataArray = new LinkedList<String>(); // initiating an arraylist to add to the queue
		
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
		
		this.queue.add(dataArray); // add the dataArray to the queue.
		
	}// end of addtodataQueue
	
	public void correctTemperature(){
		if(correctionRequired()){
			// 1 get all temp values
			// 2 calculate new temp value
			// 3 set new temp value
			// 4 send arraylist to vm for storage
		}
		else if(!correctionRequired()){
			//send arraylist to vm for storage
		}			
			
			setNewTemp();
		}
	
	public boolean correctionRequired(){
		float allowedDiffrence = 0.0f;
		float lowerLimitPercentage = 100 - allowedDiffrence;
		float uperLimitPercentage = 100 + allowedDiffrence;
		float absoluteZero = 273.15f;
		
		if(this.queue.size() >= 2){
			LinkedList<String> newestArray = (LinkedList<String>) this.queue.get((this.queue.size()-1)); //newest
			LinkedList<String> previousArray = (LinkedList<String>) this.queue.get((this.queue.size()-2)); //previous
			
			String newestTemp = newestArray.get(3);
			String previousTemp = previousArray.get(3);
			
			System.out.println("newest: " + newestArray);
			System.out.println("previous: " + previousArray);
			
			System.out.println("newest temp: " + newestTemp);
			System.out.println("previous temp: " + previousTemp);
			
			float lowerLimit = ((absoluteZero + Float.parseFloat(newestTemp))/100) * lowerLimitPercentage;
			System.out.println("Lower Limit" + lowerLimit);
			
			float uperLimit = ((absoluteZero + Float.parseFloat(previousTemp))/100) * uperLimitPercentage;
			System.out.println("Uper Limit" + uperLimit);
			
			System.out.println("dis is it: " + (absoluteZero + Float.parseFloat(newestTemp))  );
			
			if((absoluteZero + Float.parseFloat(newestTemp)) >= lowerLimit && (absoluteZero + Float.parseFloat(newestTemp)) <= uperLimit){
				System.out.println("New temp is within the limits, and therefore does not need to be corrected.");
				return true;
			}
			else{
				System.out.println("New temp is not within the limits, and therefore does need to be corrected.");
				return false;
			}
		}
		
		return false;
	}
	
	public void setNewTemp(){
		
	}
	
	public void sendArray(){
	// 1 send array to the vm
	// 2 remove from queue
	}
	
	/*
	 * in order to correct the temperature value we should store 30 values after which the corrected temperature value can be calculated
	 * after recieving the xml the data should be parsed and stored in instances of the stationbuffer class 1 station per instance. 
	 * we keep track of the stationbuffer instances in a parent bufferclass that has a map to containing key value pairs of station number and instance of stationbuffer.
	 * */
		

}
