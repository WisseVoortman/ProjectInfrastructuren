package ServerApp;

public class Measurement {
	
	public final int stationnumber;
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
	
}
