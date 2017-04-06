import java.util.Random;
/**
 * A weapon will be an item that can be used in order to fight or potentially do other things with
 * 
 * @author Team Silver
 * @version 1
 */
public class Weapon extends Item
{
    private String name;
    private int weight;
    private int size;
    private int minDmg;
    private int maxDmg;
    private int critDmg;
    /**
     * The weapon constructor will be passed in it's fields and the critDmg will be maxDmg * 2.
     */
    public Weapon(String name, int weight, int size, int minDmg, int maxDmg)
    {
        this.name = name;
        this.weight = weight;
        this.size = size;
        this.minDmg = minDmg;
        this.maxDmg = maxDmg;
        this.critDmg = maxDmg * 2;
    }

    /**
     * This method will allow the system to determine how much damage the user will do
     * and if the hit is successful
     * 
     * 
     * @param  y   none
     * @return     damage dealt 
     */
    public int swing()
    {
        //rng here
        
    }
}
