/**
 * 
 * @author Xbox5
 * This class will cause the player to receive damage when executed.
 */
public class Wound implements Events{

	
	private int damageAmount;
	
	/**
	 * @param damageAmount the amount of damage dealt to the player
	 * Creats a wound event with a damage amount.
	 */
	public Wound(int damageAmount){
		this.damageAmount = damageAmount;
	}
	
	
	/**
	 * This will decrease the players health by the given amount.
	 */
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
