/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorkCommands;

import zorkItems.*;
import zorkItems.NoItemException;
import zorkGame.GameState;
import java.util.ArrayList;


/**
 *
 * @author Matt
 * This class takes an object from the current room.
 */
public class TakeCommand extends Command {

    private String itemName;
    
    /**
     * 
     * @param itemName
     * This creates the TakeCommand object.
     */
    public TakeCommand(String itemName){
        
        this.itemName = itemName;
    }
    
    /**
     * Takes the given item from the current room or if it does not exist
     * then it displays the appropriate message.
     */
    public String execute(){
        
        
        
            ArrayList<String> itemNames = GameState.getInventoryNames();
        
            if(itemNames != null && itemNames.size() > 0){
                System.out.println(itemNames.get(0));
                for(int i = 0; i < itemNames.size(); i++){
                    if(itemNames.get(i).equalsIgnoreCase(itemName)){
                        System.out.println("You already have "+itemName);
                        return itemName;
                    }
                }
                try{
                    Item itemToTake = GameState.getItemInVicinityNamed(itemName);
                    if(itemToTake != null){
                        GameState.addToInventory(itemToTake);
                        GameState.getAdventurersCurrentRoom().remove(itemToTake);
                        System.out.println(itemName + " taken");
                    }
                    else{
                        System.out.println(itemName + " is not here");
                        return itemName;
                    }
                    }catch(NoItemException e){
                        System.out.println(itemName + " is not here");
                        return itemName;
                    }
                
                
                
            }
                else{
                    try{
                        Item itemToTake = GameState.getItemInVicinityNamed(itemName);
                        if(itemToTake != null){
                            GameState.addToInventory(itemToTake);
                            GameState.getAdventurersCurrentRoom().remove(itemToTake);
                            System.out.println(itemName + " taken");
                        }
                        else{
                            System.out.println(itemName + " is not here");
                            return itemName;
                        }
                    }catch(NoItemException e){
                
                        System.out.println(itemName + " is not here");
                        return itemName;
                    }
                    
                    
                }
            
          return null;  
    }

}
