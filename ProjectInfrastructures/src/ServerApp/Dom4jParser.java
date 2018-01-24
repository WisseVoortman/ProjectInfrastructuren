package sandbox;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Dom4jParser {
	
	public static void main(String[] args) {

	      try {
	         File inputFile = new File("input.txt");
	         SAXReader reader = new SAXReader();
	         Document document = reader.read( inputFile );

	         System.out.println("Root element :" + document.getRootElement().getName());

	         Element classElement = document.getRootElement();

	         List<Node> nodes = document.selectNodes("/WEATHERDATA/MEASUREMENT" );
	         System.out.println("----------------------------");
	         
	         for (Node node : nodes) {
	            //System.out.println("\nCurrent Element :"
	            //   + node.getName());
	            //System.out.println("Student roll no : " 
	            //   + node.valueOf("@rollno") );
	            
	            System.out.println("Station number : " + node.selectSingleNode("STN").getText());
	            System.out.println("Date of measurement : " + node.selectSingleNode("DATE").getText());
	            System.out.println("Time of measurement : " + node.selectSingleNode("TIME").getText());
	            System.out.println("Temperature : " + node.selectSingleNode("TEMP").getText());
	            System.out.println("Dewpoint : " + node.selectSingleNode("DEWP").getText());
	            System.out.println("Air presure at station level : " + node.selectSingleNode("STP").getText());
	            System.out.println("Air presure at sea level : " + node.selectSingleNode("SLP").getText());
	            System.out.println("Visibility : " + node.selectSingleNode("VISIB").getText());
	            System.out.println("Wind speed : " + node.selectSingleNode("WDSP").getText());
	            System.out.println("Perception : " + node.selectSingleNode("PRCP").getText());
	            System.out.println("Snow fallen : " + node.selectSingleNode("SNDP").getText());
	            System.out.println("Special Circumstances : " + node.selectSingleNode("FRSHTT").getText());
	            System.out.println("Cloudiness : " + node.selectSingleNode("CLDC").getText());
	            System.out.println("Wind direction : " + node.selectSingleNode("WNDDIR").getText());
		         
	         }
	      } catch (DocumentException e) {
	         e.printStackTrace();
	      }
	   }
}
