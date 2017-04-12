

/**
 * 
 * @author Xbox5
 * This class will create Fog that will effect your vision if
 * you are in "room" that is outside. 
 */

public class Fog implements Events{

	private int timeToLive;
	
	/**
	 * 
	 * @param ttl how long the fog will last
	 * This creates the Fog object that lasts as long
	 * as the seconds given.
	 */
	public Fog(int ttl){
		this.timeToLive = ttl;
	}
	
	/**
	 * This will make it so the interpreter prints out
	 * different messages for exits while the player is 
	 * outside, making it difficult to navigate.
	 */
	public void execute(){
		
	}
	
}
