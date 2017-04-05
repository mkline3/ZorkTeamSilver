package Events;

/**
 * 
 * @author Xbox5
 *
 *This class will start a Rain Shower for a certain
 *amount of time. .
 */
public class Rain extends Events{

	private int timeToLive;
	
	/**
	 * 
	 * @param ttl
	 * 
	 * Will create a Rain object that will only
	 * cause Rain for as long as the time given
	 * in seconds.
	 */
	public Rain(int ttl){
		this.timeToLive = ttl;
	}
	
	
	
	/**
	 * This methods changes values in the GameState to 
	 * initiate a weather change.
	 */
	public void effect(){
		
		
		
	}
	
}
