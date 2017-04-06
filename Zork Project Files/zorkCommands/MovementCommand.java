package zorkCommands;

import zorkGame.GameState;
import zorkDungeon.Exit;

/**
 * 
 * @author Matt
 *  
 * This class will move the adventurer around the dungeon.
 *
 */
public class MovementCommand extends Command{

	private String dir;
	
	/**
	 * 
	 * @param dir
	 * creates a movement command with the given
	 * direction.
	 */
	public MovementCommand(String dir){
		
		this.dir = dir;
	}
	
	
	/**
	 * Moves the player in the given direction unless it does not exist; then it 
	 * displays the appropriate message.
	 */
	public String execute() {
		
		
		for(Exit i : GameState.getAdventurersCurrentRoom().getExits()){
	           if(i.getDir().equalsIgnoreCase(dir)){
	               GameState.setAdventurersCurrentRoom(i.getDest());
	               GameState.getAdventurersCurrentRoom().describe();
	           }
	       }
		
		return dir;
		
	}

	
}
