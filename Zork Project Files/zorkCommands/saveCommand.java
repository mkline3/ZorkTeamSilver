package zorkCommands;
import zorkGame.GameState;
import java.util.Scanner;

/**
 * 
 * @author Matt
 *  
 * This class allows you to save the game.
 *
 */
public class saveCommand extends Command{

	private String command;
	
	/**
	 * 
	 * @param command
	 * This creates the saveCommand object.
	 */
	public saveCommand(String command){
		
		this.command = command;
		
	}
	
	/**
	 * 
	 * @return String
	 * Returns saveFileName.
	 */
	public String saveFilename(){
		
		return "";
	}
	
	
	public void SaveCommand(String f){
		
		
		
	}
	
	/**
	 * This will save the current game.
	 */
	public String execute(){
		
		
	    	Scanner in = new Scanner(System.in);
	    	System.out.println("What do you want to name the file?:");
	    	String ans = in.nextLine();
	    	if(!ans.contains(".sav") && ans != null)
	    		ans = ans + ".sav";
	    	else if(ans == null)
	    		ans = "save.sav";
	    	
	        GameState.store(ans);
	        
	        
	        
	        
	        return this.command;
	
	
	}
}
