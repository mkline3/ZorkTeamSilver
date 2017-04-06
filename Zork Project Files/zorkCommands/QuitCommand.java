/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorkCommands;

import java.util.Scanner;

/**
 *
 * @author jordan.smith
 * <h> Quit Command Class </h>
 * Creates a command to quit the game.
 */
public class QuitCommand extends Command{

    private saveCommand check = new saveCommand("save");
    
    /**
     * 
     * @param command
     * Creates the Quit command object.
     */
    public QuitCommand(String command){
        
        
    }
    
    /**
     * Will quit the game but it will ask the player if they want to save the game first.
     */
    public String execute() {
        Scanner in = new Scanner(System.in);
        
        System.out.println("Would you like to save before quitting?(y/n)");
        String ans = in.nextLine();
        
        if(ans.equalsIgnoreCase("n") || ans.equalsIgnoreCase("no"))
            System.exit(0);
        else
            check.execute(); System.exit(0);
        return null;
    }
    
    
}
