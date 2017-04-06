/**
 *
 * @author jordan.smith
 * This class gets the health, score and time. It also doubles
 * as a Take command for objects.
 */
public class GetCommand extends Command{

    private String object;
    
    /**
     * 
     * @param object
     * This takes the String object and stores it in the 
     * class.
     */
    public GetCommand(String object){
        this.object = object;
    }
    
    @Override
    /**
     * This method takes the String object and decides what to get 
     * with the name given. 
     */
    public String execute() {
      
      if(this.object.equalsIgnoreCase("time")){
          
        if(GameState.getDayTimer().getMinutes() < 7 && GameState.getDayTimer().getMinutes() > 5)
            System.out.println("It is the twilight hours of the morning.");
        else if(GameState.getDayTimer().getMinutes() < 5)
            System.out.println("It is the middle of the night.");
        else if(GameState.getDayTimer().getMinutes() > 7 && GameState.getDayTimer().getMinutes() < 12)
            System.out.println("It is morning.");
        else if(GameState.getDayTimer().getMinutes() >= 12 && GameState.getDayTimer().getMinutes() < 16)
            System.out.println("It is the early afternoon.");
        else if(GameState.getDayTimer().getMinutes() > 16 && GameState.getDayTimer().getMinutes() < 20)
            System.out.println("It is the late afternoon.");
        else
            System.out.println("It is night.");
          
        if(GameState.getDayTimer().getSeconds() < 10){
              System.out.println(GameState.getDayTimer().getMinutes() + ":0" + GameState.getDayTimer().getSeconds());
        }
        else
              System.out.println(GameState.getDayTimer().getMinutes() + ":" + GameState.getDayTimer().getSeconds());
        }
      else if(this.object.equalsIgnoreCase("score"))
    	  return null;
      else if(this.object.equalsIgnoreCase("health"))
    	  return null;
      return null;
    }
    
}
