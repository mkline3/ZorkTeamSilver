/**
 *
 * @author Nathan
 * @version 04.05.2017
 */
public class Enemy {
    // We may want to extend Item so that Enemies can be put into the Item
    //hashtable for rooms
    // Dialog options, arraylist or Hashtable?
    // Arraylist for inventory
    // Room for current room?
    // Time needed before attacking
    
    /**
     * Constructor of type Enemy
     */
    public Enemy() {
        
    }
    
    /**
     * Moves the enemy to a new room, updating both its and the room's information
     * @param room 
     */
    void moveTo(Room room) {
        
    }
    
    /**
     * Used to attack the enemy with the specified item
     * @param item the item the player attacks with
     * @return a success or failure message
     */
    String attack(Item item) {
        
    }
    
    /**
     * Checks if the specified item is a weapon that can hurt the enemy
     * @param item
     * @return 
     */
    boolean canBeHurtWith(Item item) {
        
    }
    
    
}
