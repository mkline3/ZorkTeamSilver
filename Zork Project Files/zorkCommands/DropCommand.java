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
 * This class will drop an item from the adventurers inventory.
 */
public class DropCommand extends Command{
    
    private String itemName;
    
    /**
     * 
     * @param itemName
     * This creates a DropCommand object, storing the 
     * given item name in the class.
     */
    public DropCommand(String itemName){
        this.itemName = itemName;
    }
    
    /**
     * Drops the given item from the adventurers inventory. 
     */
    public String execute(){
        
        ArrayList<String> temp = GameState.getInventoryNames();
        
        
        try{
            if(temp.isEmpty()){
            
                System.out.println("You have nothing to drop");
                return null;
            }
            else if(!temp.isEmpty()){
            
                
           
                for(String x : temp){
                    
                if(x.equalsIgnoreCase(itemName)){
                    GameState.getAdventurersCurrentRoom().add(GameState.getItemFromInventoryNamed(itemName));
                    GameState.removeFromInventory(GameState.getItemFromInventoryNamed(itemName));
                    System.out.println(itemName + " dropped");
                    return itemName;
                    }
                }   
            }
            else{
                System.out.println("You do not have a " + itemName);
                return itemName;
            }
            }catch(Exception e){
            
                System.out.println("You do not have a "+itemName);
                return itemName;
            }
            
        System.out.println("You do not have a "+itemName);
        return null;
        
       
    }
    
}
