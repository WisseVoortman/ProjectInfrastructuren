package ServerApp;

import java.util.*;

import java.io.File;
import java.io.StringReader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
//import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Dom4jParser implements Runnable{
	
	private String buffer;
	private GeneralBuffer generalBuffer;
	
	
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
	
		
	public Dom4jParser(String buffer, GeneralBuffer generalBuffer){
		this.buffer = buffer;
		this.generalBuffer = generalBuffer;
		
	}
	
	public void run(){
		parse(this.buffer);
	}
	
	public void parse(String buffer) {

	      try {
	         //File inputFile = new File("input.txt");
	         SAXReader reader = new SAXReader();
	         Document document = reader.read(new StringReader(this.buffer));

	         //System.out.println("Root element :" + document.getRootElement().getName());

	         //Element classElement = document.getRootElement();

	         List<Node> nodes = document.selectNodes("/WEATHERDATA/MEASUREMENT" );
	         //System.out.println("----------------------------");
	         
	         for (Node node : nodes) {
	            //System.out.println("\nCurrent Element :"
	            //   + node.getName());
	            //System.out.println("Student roll no : " 
	            //   + node.valueOf("@rollno") );
	                  
	            this.stationnumber 				= node.selectSingleNode("STN").getText();
	            this.date 						= node.selectSingleNode("DATE").getText();
	            this.time 						= node.selectSingleNode("TIME").getText();
	            this.temperature 				= node.selectSingleNode("TEMP").getText();
	            this.dewpoint 					= node.selectSingleNode("DEWP").getText();
	            this.airpresurestationlevel 	= node.selectSingleNode("STP").getText();
	            this.airpresuresealevel 		= node.selectSingleNode("SLP").getText();
	            this.visability 				= node.selectSingleNode("VISIB").getText();
	            this.windspeed 					= node.selectSingleNode("WDSP").getText();
	            this.perception 				= node.selectSingleNode("PRCP").getText();
	            this.snowfallen 				= node.selectSingleNode("SNDP").getText();
	            this.specialcircumstances 		= node.selectSingleNode("FRSHTT").getText();
	            this.cloudiness 				= node.selectSingleNode("CLDC").getText();
	            this.winddirection 				= node.selectSingleNode("WNDDIR").getText();
	            
	            if (generalBuffer.getmap().get(this.stationnumber) == null) { //gets the value for an id)
	            		generalBuffer.getmap().put(this.stationnumber, new StationBuffer(this.stationnumber)); //no StationBuffer assigned, creating a new StationBuffer
	            		System.out.println("created a new StationBuffer for: station " + this.stationnumber + ".");
				    } // end of if
	            
	            //printParsedData();
	            
	            ((StationBuffer) generalBuffer.getmap().get(this.stationnumber)).printID();
	            
	            ((StationBuffer) generalBuffer.getmap().get(this.stationnumber)).addDataArrayToQueue(this.stationnumber, this.date, this.time, this.temperature, this.dewpoint, this.airpresurestationlevel, this.airpresuresealevel, this.visability, this.windspeed, this.perception, this.snowfallen, this.specialcircumstances, this.cloudiness, this.winddirection);
	            
	            ((StationBuffer) generalBuffer.getmap().get(this.stationnumber)).printqueue();
	            
	            ((StationBuffer) generalBuffer.getmap().get(this.stationnumber)).correctTemperature();
	            
	         }
	      } catch (DocumentException e) {
	         e.printStackTrace();
	      }
	   }
	
	public void printParsedData(){
        System.out.println("Station number : " + this.stationnumber);
        System.out.println("Date of measurement : " + this.date);
        System.out.println("Time of measurement : " + this.time);
        System.out.println("Temperature : " + this.temperature);
        System.out.println("Dewpoint : " + this.dewpoint);
        System.out.println("Air presure at station level : " + this.airpresurestationlevel);
        System.out.println("Air presure at sea level : " + this.airpresuresealevel);
        System.out.println("Visibility : " + this.visability);
        System.out.println("Wind speed : " + this.windspeed);
        System.out.println("Perception : " + this.perception);
        System.out.println("Snow fallen : " + this.snowfallen);
        System.out.println("Special Circumstances : " + this.specialcircumstances);
        System.out.println("Cloudiness : " + this.cloudiness);
        System.out.println("Wind direction : " + this.winddirection);
	}
}
