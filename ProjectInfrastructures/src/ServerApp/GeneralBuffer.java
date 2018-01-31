package ServerApp;

import java.util.HashMap;


public class GeneralBuffer {

	private int id;
	@SuppressWarnings("rawtypes")
	private HashMap map;
	
	public GeneralBuffer(){
		id = 1;
		this.map = new HashMap<>();
		
	} // end of constructor
	
	
	public HashMap getmap(){
		return this.map;
	}
	
	//notes
	//((StationBuffer) this.map.get(id)).testone();    
    //((StationBuffer) this.map.get(id)).printqueue();
    //((StationBuffer) this.map.get(id)).addToQueue();
    //((StationBuffer) this.map.get(id)).printqueue();
		
}//end of class
