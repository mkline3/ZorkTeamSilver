
/**
 * The score class will tally up the points a user will get through various tasks. (i.e. entering a room, etc)
 * 
 * @author Team Silver 
 * @version 1
 */
public class Score
{
    // instance variables - replace the example below with your own
    private int score;
    /**
     * Score begins at zero, and will hopefully increase as the user explores the dungeon
     */
    public Score()
    {
        this.score = 0;
    }

    /**
     * this method adds 1 anytime the user discovers something 
     * 
     * @param  y   none
     * @return     none 
     */
    public void addPoints()
    {
        score += 1;
    }
}
