package ServerApp;

import java.io.Serializable;
/*
 * Class that is used to create objects that will be sent to the VM/Storrage server
 */
public class Measurement implements Serializable{
	
	public final int stationnumber; // defines variables
	public final String date;
	public final String time;
	public final Float temperature;
	public final Float dewpoint;
	public final Float airpresurestationlevel;
	public final Float airpresuresealevel;
	public final Float visability;
	public final Float windspeed;
	public final Float perception;
	public final Float snowfallen;
	public final int specialcircumstances;
	public final Float cloudiness;
	public final int winddirection;
	
	/**
	 * constructor for Measurment class 
	 * initializes the variables from the paramenters
	 */
	public Measurement(String stationnumber, String date, String time, String temperature, String dewpoint, String airpresurestationlevel, String airpresuresealevel, String visability, String windspeed, String perception, String snowfallen, String specialcircumstances, String cloudiness, String winddirection){
		this.stationnumber 				= Integer.parseInt(stationnumber);
		this.date						= date; 
		this.time						= time;
		this.temperature				= Float.parseFloat(temperature);
		this.dewpoint					= Float.parseFloat(dewpoint);
		this.airpresurestationlevel		= Float.parseFloat(airpresurestationlevel);
		this.airpresuresealevel			= Float.parseFloat(airpresuresealevel);
		this.visability					= Float.parseFloat(visability);
		this.windspeed					= Float.parseFloat(windspeed);
		this.perception					= Float.parseFloat(perception);
		this.snowfallen					= Float.parseFloat(snowfallen);
		this.specialcircumstances		= Integer.parseInt(specialcircumstances);
		this.cloudiness					= Float.parseFloat(cloudiness);
		this.winddirection				= Integer.parseInt(winddirection);
	}
	/**
	 * constructor for Measurment class
	 * initializes the variables from the paramenters
	 */
	public Measurement(int stationnumber, String date, String time, Float temperature, Float dewpoint, Float airpresurestationlevel, Float airpresuresealevel, Float visability, Float windspeed, Float perception, Float snowfallen, int specialcircumstances, Float cloudiness, int winddirection) {
		this.stationnumber 				= stationnumber;
		this.date						= date;
		this.time						= time;
		this.temperature				= temperature;
		this.dewpoint					= dewpoint;
		this.airpresurestationlevel		= airpresurestationlevel;
		this.airpresuresealevel			= airpresuresealevel;
		this.visability					= visability;
		this.windspeed					= windspeed;
		this.perception					= perception;
		this.snowfallen					= snowfallen;
		this.specialcircumstances		= specialcircumstances;
		this.cloudiness					= cloudiness;
		this.winddirection				= winddirection;
	}
}
