package sandbox;

import java.util.HashMap;


public class GeneralBuffer {

	private int id;
	@SuppressWarnings("rawtypes")
	private HashMap map;
	
	public GeneralBuffer(){
		id = 1;
		this.map = new HashMap<>();
		
		if (this.map.get(id) == null) { //gets the value for an id)
		    this.map.put(id, new StationBuffer()); //no ArrayList assigned, create new ArrayList
		    System.out.print(this.map.get(id));
		    ((StationBuffer) this.map.get(id)).testone();    
		    ((StationBuffer) this.map.get(id)).printqueue();
		    ((StationBuffer) this.map.get(id)).addToQueue();
		    ((StationBuffer) this.map.get(id)).printqueue();
		
		} // end of if
		
	} // end of constructor
	
	public static void main(String[] args){
		
		GeneralBuffer generalBuffer = new GeneralBuffer();
	
	}
	
}//end of class
