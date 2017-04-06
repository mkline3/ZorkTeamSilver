/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorkGame;

import zorkItems.NoItemException;
import zorkDungeon.Room.NoRoomException;
import zorkItems.*;
import zorkDungeon.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author ZorkTeamSilver
 * This keeps the players position and moves
 * them around the world. 
 */
public class GameState{
    
    
    private static Dungeon current;
    private static GameState state;
    private static Room currentRoom;
    private static DayTimer timer = new DayTimer(0,0);
    private static boolean isLightOutside = false;
    private Health health = new Health();
    private Score score = new Score();
  
    private static ArrayList<Item> inventory = new ArrayList<Item>();
    
    public GameState(Dungeon dungeon){
        
    	this.current = dungeon;
        this.state = this;
        initialize(current);
    	
    }
    
    private void initialize(Dungeon dungeon){
      
        instance();
        setAdventurersCurrentRoom(dungeon.getEntry());
        
    }
    
    private static synchronized GameState instance(){
    	
    	if(state == null)
    		state = new GameState(current);
    	return state;
    }
    
    public static DayTimer getDayTimer(){
        return timer;
    }
    
    public static void setLight(boolean x){
        if(x == true)
            isLightOutside = true;
        else 
            isLightOutside = false;
    }
    
    public static Room getAdventurersCurrentRoom(){
        
        return currentRoom;
    }
    
    public static void setAdventurersCurrentRoom(Room room){
        
        currentRoom = room;
    }
    
    public static Dungeon getDungeon(){
        
        return current;
    }
    
    
    public static void store(String saveName){
        
        PrintWriter wr = null;
        File file = new File(saveName);
        try{
            wr = new PrintWriter(file);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        System.out.println(file.getAbsolutePath());
        wr.println(current.getName());
        wr.println("Zork Project v1");
        wr.println("save data");
        wr.println("Dungeon file:");
        wr.println(file.getAbsolutePath());
        wr.println("states:");
        current.storeState(wr);
        wr.println("Current Room:");
        wr.println(currentRoom.getTitle());
        if(!inventory.isEmpty()){
            wr.println("Inventory:");
            for(Item x : inventory)
            {
                wr.print(x.getPrimaryName()+",");
                //System.out.println(x.getPrimaryName());
            }
            wr.println();
        }
        //wr.println();    
        wr.println("Time:");
        wr.println(GameState.getDayTimer().getMinutes() + ":" + GameState.getDayTimer().getSeconds());
        wr.close();
        
    }
    
    public static void restore(String fileName){
        
        if(!fileName.contains(".sav"))
            fileName = fileName + ".sav";
        Scanner in = new Scanner(System.in);
        Scanner scan = null;
        
        try{
            scan = new Scanner(new File(fileName));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        String name = scan.nextLine();
        while(!name.equalsIgnoreCase(current.getName())){
           
            if(new File(fileName).exists())
                System.out.println("Error, selected save file not compatible with Dungeon.");
            else
                System.out.println("Error, save file not found.");
           System.out.println("Enter another save file:");
           fileName = in.nextLine();
           if(!fileName.contains(".sav"))
               fileName = fileName + ".sav";
           if(new File(fileName).exists()){
               
               try{
                    scan = new Scanner(new File(fileName));
                }catch(Exception e){
                    e.printStackTrace();
                }
               name = scan.nextLine();
           }
           
               
           
        }
        
        if(!scan.nextLine().equalsIgnoreCase("Zork Project v1")){
            System.out.println("Error, not compatible with current version.");
            System.exit(0);
        }
        
        else if(scan.nextLine().equalsIgnoreCase("save data")){
            
            while(scan.hasNextLine()){
                
                String next = scan.nextLine();
                if(next.equalsIgnoreCase("states:")){
                    
                    current.restoreState(scan);
                    
                    
                }
                else if(next.equalsIgnoreCase("Inventory:")){
                    
                    String[] separated = scan.nextLine().split(",");
                    
                    for(int i = 0; i < separated.length; i++)
                    {
                        try{
                            state.addToInventory(current.getItem(separated[i]));
                            //System.out.println(separated[i]);
                        }catch(NoItemException e){
                            System.out.println("Could not find Item");
                        }
                    }
                }
                else if(next.equalsIgnoreCase("Time:")){
                	String[] separated = scan.nextLine().split(":");
                	
                	GameState.getDayTimer().setTime(Integer.parseInt(separated[0]), Integer.parseInt(separated[1]));
                }
                    
                else if(next.equalsIgnoreCase("==="))
                   break;
               
                
                
            }
            
            
        }
    
    }
    
    
    public static ArrayList<String> getInventoryNames(){
        
        ArrayList<String> inventoryNames = new ArrayList<String>();
        if(!inventory.isEmpty() || inventory != null){
            for(Item x : inventory){
                inventoryNames.add(x.getPrimaryName());
            
            }
        
        
            return inventoryNames;
        }
        else{
            return null;
        }
    }
    
    
    public static void addToInventory(Item item) throws NoItemException{
        if(inventory == null)
            inventory = new ArrayList<>();
        
    inventory.add(item);
    }
    
    public static void removeFromInventory(Item item){
       
    inventory.remove(item);
   
    
    }
    
    public static Item getItemInVicinityNamed(String name) {
        
        boolean found = false;
        
        for(Item x : currentRoom.getContents()){
            
            if(x.getPrimaryName().equalsIgnoreCase(name)){
                found = true;
                return x;
            }
        }
        
        for(Item y : inventory){
            
            if(y.getPrimaryName().equalsIgnoreCase(name)){
                found = true;
                return y;
            }
        }
        
        
            
       return null;
    }
    
    public static Item getItemFromInventoryNamed(String name) throws NoItemException{
        
        for(Item x : inventory){
            
            if(x.getPrimaryName().equalsIgnoreCase(name))
                return x;
        }
      
        return null;
       
    }
    
}