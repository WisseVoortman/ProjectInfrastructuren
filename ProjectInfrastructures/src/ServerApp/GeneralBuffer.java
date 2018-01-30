package ServerApp;

import java.util.HashMap;


public class GeneralBuffer {

	private int id;
	@SuppressWarnings("rawtypes")
	private HashMap map;
	
	public GeneralBuffer(){
		id = 1;
		this.map = new HashMap<>();
		
		for(int i=id;i<=8000;i++){
			if (this.map.get(i) == null) { //gets the value for an id)
			    this.map.put(i, new StationBuffer()); //no StationBuffer assigned, creating a new StationBuffer
			    System.out.println("created a new StationBuffer for: station " + i + ".");
			    } // end of for
		
		
		    
		
		} // end of for
		
		//System.out.print(this.map.get(id));
		
		//((StationBuffer) this.map.get(id)).testone();    
	    //((StationBuffer) this.map.get(id)).printqueue();
	    //((StationBuffer) this.map.get(id)).addToQueue();
	    //((StationBuffer) this.map.get(id)).printqueue();
		
	} // end of constructor
	
}//end of class
