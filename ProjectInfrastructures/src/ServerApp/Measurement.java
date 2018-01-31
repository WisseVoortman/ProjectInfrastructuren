package ServerApp;

public class Measurement {
	
	private int stationnumber;
	private String date;
	private String time;
	private Float temperature;
	private Float dewpoint;
	private Float airpresurestationlevel;
	private Float airpresuresealevel;
	private Float visability;
	private Float windspeed;
	private Float perception;
	private Float snowfallen;
	private int specialcircumstances;
	private Float cloudiness;
	private int winddirection;
	
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
