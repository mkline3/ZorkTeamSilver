/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorkCommands;
import zorkGame.GameState;
import java.util.ArrayList;

/**
 *
 * @author Matt
 * This class displays the contents of the adventurers inventory.
 */
public class InventoryCommand extends Command{

    private String command;
    
    /**
     * Constructor for InventoryCommand class.
     */
    public InventoryCommand(){
        
    }
    
    /**
     * Displays all the contents of the adventurers inventory but if 
     * it is empty it displays the appropriate message.
     */
    public String execute() {
       
        ArrayList<String> inventory = GameState.getInventoryNames();
        System.out.println("Inventory: ");
        
        try{
            for(String x : inventory){
            
                System.out.println(x);
            
            }
            if(inventory.isEmpty())
                System.out.println("Empty");
        }catch(Exception e){
            
            System.out.println("Empty");
        }
        
        return this.command;
    }
    
}
