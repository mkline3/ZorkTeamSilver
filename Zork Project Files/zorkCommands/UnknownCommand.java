package zorkCommands;

/**
 * 
 * @author Matt
 * This will be used when a player inputs an unknown command. 
 */
public class UnknownCommand extends Command{

	private String bogusCommand;
	
	/**
	 * 
	 * @param command
	 * creates UnkownCommand object with the unknown string.
	 */
	public UnknownCommand(String command) {
		
		this.bogusCommand = command;
	}
	
	/**
	 * Displays the appropriate message for the unknown command.
	 */
	public String execute(){
		
            
                if(bogusCommand.equalsIgnoreCase("drop"))
                    System.out.println(bogusCommand+" what?");
                else if(bogusCommand.equalsIgnoreCase("take"))
                    System.out.println(bogusCommand+" what?");
                else if(bogusCommand.equalsIgnoreCase(""))
                    System.out.println("huh?");
                else
                    System.out.println(bogusCommand+"....what?");
		
		return bogusCommand;
	}

}
