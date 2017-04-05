
/**
 * The user's pool of health and the features to tamper with it
 * 
 * @author Team Silver
 * @version 1
 */
public class Health
{
    private int healthPool;
    public static int MAX_HEALTH = 40;
    /**
     * Constructor for objects of class Health
     */
    public Health()
    {
        healthPool = 40;
    }

    /**
     * for every turn the user plays, their health will decrease by 1. This is to help them get through
     * the dungeon without being bored. An element of finality is the purpose of this method. 
     * 
     * @param  y   none
     * @return     none
     */
    public void decrease()
    {
        if(healthPool < 1)
            //game over
        healthPool = healthPool - 1;
    }
    /**
     * this method is for increasing the users health. If a potion is consumed or some food, the player's health will increase
     * 
     */
    public void increase()
    {
        if(healthPool > MAX_HEALTH)
        {
            healthPool = MAX_HEALTH;
        }
        else
            //will have to check if adding puts the health over as well
            healthPool = healthPool + 20;
    }
}
