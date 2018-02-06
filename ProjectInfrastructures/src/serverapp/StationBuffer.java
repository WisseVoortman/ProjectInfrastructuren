package ServerApp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class StationBuffer {

	private String id;
	private LinkedList<Object> queue; // queue that will contain arrays with data
	private long duration;
	
	private String stationnumber; 
	private String date;
	private String time;
	private String temperature;
	private String dewpoint;
	private String airpresurestationlevel;
	private String airpresuresealevel;
	private String visability;
	private String windspeed;
	private String perception;
	private String snowfallen;
	private String specialcircumstances;
	private String cloudiness;
	private String winddirection;
	

	public StationBuffer(String id){
		this.id = id;
		this.queue = new LinkedList<Object>(); // queue that will contain data 
	}
		
		
	public void printID(){
		System.out.println("---------------------------------------------------------");
		System.out.println("my id is: " + id);
		
	}
	
	public void printqueue(){
		System.out.println("queue size: " + this.queue.size());
		//System.out.println("Current Queue: " + this.queue);
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
		
		
		this.stationnumber = stationnumber;
		this.date = date;
		this.time = time;
		this.temperature = temperature;
		this.dewpoint = dewpoint;
		this.airpresurestationlevel = airpresurestationlevel;
		this.airpresuresealevel = airpresuresealevel;
		this.visability = visability;
		this.windspeed = windspeed;
		this.perception = perception;
		this.snowfallen = snowfallen;
		this.specialcircumstances = specialcircumstances;
		this.cloudiness = cloudiness;
		this.winddirection = winddirection;
		
		
		checkStrings(this.temperature, this.dewpoint, this.airpresurestationlevel, this.airpresuresealevel, this.visability, this.windspeed, this.perception, this.snowfallen, this.specialcircumstances, this.cloudiness, this.winddirection);
		
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
			setNewTemp();
			if(this.queue.size() >=31){
				sendArray();
			}
			
		}
		else if(!correctionRequired()){
			//send arraylist to vm for storage
			setNewTemp();
			if(this.queue.size() >=31){
				sendArray();
			}
		}			
			
			
		}
	
	public boolean correctionRequired(){
		float allowedDiffrence = 1.0f;
		float lowerLimitPercentage = 100 - allowedDiffrence;
		float uperLimitPercentage = 100 + allowedDiffrence;
		float absoluteZero = 273.15f;
		
		if(this.queue.size() >= 2){
			LinkedList<String> newestArray = (LinkedList<String>) this.queue.get((this.queue.size()-1)); //get the newest array of data
			LinkedList<String> previousArray = (LinkedList<String>) this.queue.get((this.queue.size()-2)); //get the previous array of data
			
			String newestTemp = newestArray.get(3); // get the temperature from the newest array of data
			String previousTemp = previousArray.get(3); // get the temperature from previous the array of data
			
			//System.out.println("newest: " + newestArray);
			//System.out.println("previous: " + previousArray);
			
			System.out.println("newest temp: " + newestTemp);
			System.out.println("previous temp: " + previousTemp);
			
			float lowerLimit = ((absoluteZero + Float.parseFloat(newestTemp))/100) * lowerLimitPercentage;
			//System.out.println("Lower Limit" + lowerLimit);
			
			float uperLimit = ((absoluteZero + Float.parseFloat(previousTemp))/100) * uperLimitPercentage;
			//System.out.println("Uper Limit" + uperLimit);
			
			//System.out.println("value to test: " + (absoluteZero + Float.parseFloat(newestTemp))  );
			
			if((absoluteZero + Float.parseFloat(newestTemp)) >= lowerLimit && (absoluteZero + Float.parseFloat(newestTemp)) <= uperLimit){
				//System.out.println("New temp is within the limits, and therefore does not need to be corrected.");
				return true;
			}
			else{
				//System.out.println("New temp is not within the limits, and therefore does need to be corrected.");
				return false;
			}
		}
		
		return false;
	}
	
	public void setNewTemp(){
		// 1 get all temp values
		// 2 calculate new temp value
		// 3 set new temp value
	}
	
	public void checkStrings(String temperature, String dewpoint, String airpresurestationlevel, String airpresuresealevel, String visability, String windspeed, String perception, String snowfallen, String specialcircumstances, String cloudiness, String winddirection){
		if (queue.size() > 0){
			LinkedList<String> dL = (LinkedList<String>) queue.peek();
			
			if (this.temperature.isEmpty()){
				this.temperature = dL.get(3);
			}
			if (this.dewpoint.isEmpty()){
				this.dewpoint = dL.get(4);
			}
			if (this.airpresurestationlevel.isEmpty()){
				this.airpresurestationlevel = dL.get(5);
			}
			if (this.airpresuresealevel.isEmpty()){
				this.airpresuresealevel = dL.get(6);
			}
			if (this.visability.isEmpty()){
				this.visability  = dL.get(7);
			}
			if (this.windspeed.isEmpty()){
				this.windspeed  = dL.get(8);
			}
			if (this.perception.isEmpty()){
				this.perception  = dL.get(9);
			}
			if (this.snowfallen.isEmpty()){
				this.snowfallen  = dL.get(10);
			}
			if (this.specialcircumstances.isEmpty()){
				this.specialcircumstances  = dL.get(11);
			}
			if (this.cloudiness.isEmpty()){
				this.cloudiness  = dL.get(12);
			}
			if (this.winddirection.isEmpty()){
				this.winddirection  = dL.get(13);
			}
		}
		else if (queue.size() < 1 ){
			LinkedList<String> dL = (LinkedList<String>) queue.peek();
			
			if (this.temperature.isEmpty()){
				this.temperature = "0";
			}
			if (this.dewpoint.isEmpty()){
				this.dewpoint = "0";
			}
			if (this.airpresurestationlevel.isEmpty()){
				this.airpresurestationlevel = "0";
			}
			if (this.airpresuresealevel.isEmpty()){
				this.airpresuresealevel = "0";
			}
			if (this.visability.isEmpty()){
				this.visability  = "0";
			}
			if (this.windspeed.isEmpty()){
				this.windspeed  = "0";
			}
			if (this.perception.isEmpty()){
				this.perception  = "0";
			}
			if (this.snowfallen.isEmpty()){
				this.snowfallen  = "0";
			}
			if (this.specialcircumstances.isEmpty()){
				this.specialcircumstances  = "0";
			}
			if (this.cloudiness.isEmpty()){
				this.cloudiness  = "0";
			}
			if (this.winddirection.isEmpty()){
				this.winddirection  = "0";
			}
		}
		
		
	}
	
	public void sendArray(){
		System.out.println("Sending Measurment...");
		// 1 get the last array to send
		LinkedList<String> dataArray = new LinkedList<String>(); // initiating an arraylist to add to the queue
		
		dataArray = (LinkedList<String>) this.queue.peek();
		// 2 create object of array
		
		Measurement m = new Measurement(dataArray.get(0), dataArray.get(1), dataArray.get(2), dataArray.get(3), dataArray.get(4), dataArray.get(5), dataArray.get(6), dataArray.get(7), dataArray.get(8), dataArray.get(9), dataArray.get(10), dataArray.get(11), dataArray.get(12), dataArray.get(13)); //imediately call function in this bitch
		if(this.queue.size() >=31){
			this.queue.remove();
			System.out.println("removing dataArray from the queue." + "new queue size: " + this.queue.size() );
		}
		Socket client = null;
		ObjectOutputStream out = null;
		
		try{
			this.duration = System.currentTimeMillis();
			client = new Socket("145.37.37.120", 30011);
			out = new ObjectOutputStream(client.getOutputStream());		
			out.writeObject(m);
			out.flush();
			
			// close resources
			
			out.close();
			client.close();
		}
		catch(UnknownHostException e){
			e.printStackTrace();
			System.exit(1);
		} 
		catch (IOException e) {
			this.duration = System.currentTimeMillis() - this.duration;
			System.out.println("connect attempt duration measured in ms:" + this.duration); // Prints how long the attempt at making a connection takes.
			e.printStackTrace();
			System.exit(1);
		}
		finally{
			
		}
		
	}
	
}
