package ServerApp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class StationBuffer {

	private String id; // the ID which is that same as the stationnumber of the station this buffer belongs to
	private LinkedList<Object> queue; // queue that will contain arrays with data
	
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
	
	/*
	 *  constructor of stationbuffer object
	 *  each weather station will have its own stationbuffer.
	 */
	public StationBuffer(String id){
		this.id = id;
		this.queue = new LinkedList<Object>(); // queue that will contain data 
	}
		
	// prints the id of the station to the console	
	public void printID(){
		System.out.println("---------------------------------------------------------");
		System.out.println("my id is: " + id);
		
	}
	
	// prints the queue size to the console
	public void printqueue(){
		System.out.println("queue size: " + this.queue.size());
		//System.out.println("Current Queue: " + this.queue);
	}
	
	/*
	 *  adds data to the queue in order to create a buffer.
	 *  this method has a built in check for empty values.
	 */
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
		
		dataArray.add(this.stationnumber); // filling the arraylist with data before it is added to the queeu
		dataArray.add(this.date);
		dataArray.add(this.time);
		dataArray.add(this.temperature);
		dataArray.add(this.dewpoint);
		dataArray.add(this.airpresurestationlevel);
		dataArray.add(this.airpresuresealevel);
		dataArray.add(this.visability);
		dataArray.add(this.windspeed);
		dataArray.add(this.perception);
		dataArray.add(this.snowfallen);
		dataArray.add(this.specialcircumstances);
		dataArray.add(this.cloudiness);
		dataArray.add(this.winddirection);
		
		this.queue.add(dataArray); // add the dataArray to the queue.
		
	}// end of addtodataQueue
	
	// checks if a temperature correction is required.
	public void correctTemperature(){
		if(correctionRequired()){
			setNewTemp();
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
				return false;
			}
			else if(this.queue.size() <1 ){
				// there are no values to make a correction with.
				return false;
			}
			else{
				//System.out.println("New temp is not within the limits, and therefore does need to be corrected.");
				return true;
			}
		}
		
		return false;
	}
	
	// calculates the new temperature based on the 30 last measured temperatures. 
	public void setNewTemp(){
		// 1 get all temp values
		float totalTemp = 0;
		int b = 0;
		for(int i=0;i < this.queue.size();i++){
			
			LinkedList<String> a = (LinkedList<String>) this.queue.get(i);
			totalTemp += Float.parseFloat(a.get(3));
			b = ++b;
			
		}
		
		// 2 calculate new temp value
		Float newTemp = totalTemp / b;
		
		// 3 set new temp value
		LinkedList<String> old = (LinkedList<String>) this.queue.removeLast();
		addDataArrayToQueue(old.get(0), old.get(1), old.get(2), String.valueOf(newTemp), old.get(4), old.get(5), old.get(6), old.get(7), old.get(8), old.get(9), old.get(10), old.get(11), old.get(12), old.get(13) );
	}
	/*
	 * this method checks for missing values
	 * if a measurement value is missing it will take the previous measurment.
	 * if there is no previous measurment it will set 0.
	 */
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
	/*
	 * adds the oldes value in the stationBuffer to a queue contained in the stationBufferMap for sending to the VM/storrage server.
	 */
	public void addToSendQueue(StationBufferMap stationBufferMap){
				
		if(this.queue.size() >=31){
			LinkedList<String> dataArray = new LinkedList<String>(); // initiating an arraylist to add to the queue
			
			//retrieving the array to put in the sendqueue
			dataArray = (LinkedList<String>) this.queue.peek();
			
			System.out.println("adding measurement to sendQueue...");
			stationBufferMap.add(dataArray); // <-- is synchronized
			System.out.println("sendQueue size:" + stationBufferMap.getSendQueueSize());
			this.queue.remove();
			System.out.println("removing dataArray from the queue." + "new queue size: " + this.queue.size() );
		}
		
			
		
		
	}
	
	
	// old way of sending the data that did not make use of the a threadpool.
	public void sendArray(){
		// 1 get the last array to send
		LinkedList<String> dataArray = new LinkedList<String>(); // initiating an arraylist to add to the queue
		
		dataArray = (LinkedList<String>) this.queue.peek();
		// 2 create object of array
		
		Measurement m = new Measurement(dataArray.get(0), dataArray.get(1), dataArray.get(2), dataArray.get(3), dataArray.get(4), dataArray.get(5), dataArray.get(6), dataArray.get(7), dataArray.get(8), dataArray.get(9), dataArray.get(10), dataArray.get(11), dataArray.get(12), dataArray.get(13)); //imediately call function in this bitch
		if(this.queue.size() >=31){
			this.queue.remove();
			System.out.println("removing dataArray from the queue." + "new queue size: " + this.queue.size() );
		
			Socket client = null;
			ObjectOutputStream out = null;
			
			try{
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
				e.printStackTrace();
				System.exit(1);
			}
			finally{
				
			}//end
		}
	}
	
}