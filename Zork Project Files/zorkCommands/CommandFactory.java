/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorkCommands;

//import zorkGame.Exit.NoExitException;
//import zorkGame.Item.NoItemException;

/**
 *
 * @author Matt
 * This is the classed used to create and 
 * process commands.
 */
public class CommandFactory {
    
    private static CommandFactory current;
    
    private MovementCommand n = new MovementCommand("n");
    private MovementCommand d = new MovementCommand("d");
    private MovementCommand e = new MovementCommand("e");
    private MovementCommand w = new MovementCommand("w");
    private MovementCommand s = new MovementCommand("s");
    private MovementCommand u = new MovementCommand("u");
    private saveCommand save = new saveCommand("save");
    private QuitCommand quit = new QuitCommand("quit");
    
    
    
    /**
     * Constructor for CommandFactory Class
     */
    public CommandFactory(){
        
        this.current = this;
        instance();
        
    }
     
    /**
     * 
     * @return CommandFactory
     * Keeps only one instance of CommandFactory 
     */
    private static synchronized CommandFactory instance(){
        
        if(current == null)
            current = new CommandFactory();
        return current;
    }
    
    /**
     * 
     * @param commandString
     * @return Command
     * This takes in a string command 
     * and executes the matching Command object. 
     */
    public Command parse(String commandString) {
        
        String[] separated = commandString.split(" ");
        
        if(commandString.equalsIgnoreCase("n"))
                return this.n;
            else if(commandString.equalsIgnoreCase("s"))
                return this.s;
            else if(commandString.equalsIgnoreCase("e"))
                return this.e;
            else if(commandString.equalsIgnoreCase("w"))
                return this.w;
            else if(commandString.equalsIgnoreCase("d"))
                return this.d;
            else if(commandString.equalsIgnoreCase("u"))
                return this.u;
            else if(commandString.equalsIgnoreCase("save"))
                return this.save;
            else if(commandString.equalsIgnoreCase("quit") || commandString.equalsIgnoreCase("q"))
                return this.quit;
            else if(separated[0].equalsIgnoreCase("take") && separated.length > 1)
                return new TakeCommand(separated[1]);
            else if(separated[0].equalsIgnoreCase("drop") && separated.length > 1)
                return new DropCommand(separated[1]);
            else if(separated[0].equalsIgnoreCase("get") && separated.length > 1)
            	return new GetCommand(separated[1]);
            else if(separated.length > 1)
                return new ItemSpecificCommand(separated[0], separated[1]);
            else if(commandString.equalsIgnoreCase("i"))
                return new InventoryCommand();
            else if(commandString.equalsIgnoreCase("describe") || commandString.equalsIgnoreCase("desc"))
                return new DescribeCommand();
            else
                return new UnknownCommand(commandString);
    }
    
}
