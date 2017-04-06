/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorkCommands;
import zorkGame.GameState;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matt
 * This class will allow an item to execute its own
 * specific command.
 */
public class ItemSpecificCommand extends Command{

    private String verb, noun;
    
    /**
     * 
     * @param verb
     * @param noun
     * Constructor for ItemSpecificCommand that stores the given verb and 
     * noun combo in the class.
     */
    public ItemSpecificCommand(String verb, String noun){
        
        this.verb = verb;
        this.noun = noun;
        
    }
    
    /**
     * Executes the item specific command unless the item does not exist and it 
     * displays the appropriate message.
     */
    public String execute() {
        
        if(GameState.getItemInVicinityNamed(noun) != null){
            if(GameState.getItemInVicinityNamed(noun).getMessageForVerb(verb) != null)
                System.out.println(GameState.getItemInVicinityNamed(noun).getMessageForVerb(verb));
            else
                System.out.println("I dont know how to "+ verb +" the "+ noun);
        }
        else
            System.out.println("You do not have a "+noun);
        
        return null;
    }
    
}
