/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorkDungeon;

import zorkDungeon.Exit.NoExitException;
import zorkItems.NoItemException;
import zorkItems.Item;
import zorkDungeon.Room.NoRoomException;
import zorkGame.GameState;
import java.io.File;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author ZorkTeamSilver
 * Class used to create the dungeons. 
 */
public class Dungeon {
    
    private Hashtable<String, Room> rooms = new Hashtable<String, Room>();
    private Hashtable<String, Item> allItems = new Hashtable<String, Item>();
    private String name, version;
    private Room entry;
    private int roomCount = 0;
    private String fileName;
    
    /**
     * 
     * @param entry
     * @param name
     * Constructor for Dungeon
     */
    public Dungeon(Room entry, String name){
        this.name = name;
        this.entry = entry;
    }

    /**
     * 
     * @param fileName
     * Hydrates dungeon from given filename.
     */
    public Dungeon(String fileName){
        
        Scanner scan = null;
        int roomCount = 0;
        this.fileName = fileName;
        
        try{
            scan = new Scanner(new File(fileName));
        }catch(Exception e){
            System.out.println("File Not Found.");
        }
        
        this.name = scan.nextLine();
        this.version = scan.nextLine();
        if(!this.version.equalsIgnoreCase("Bork v3.0")){
            System.out.println(this.name + this.version);
            System.out.println("Not an accepted version.");
            System.exit(0);
        }
       
        else{
            System.out.println(this.name + " " + this.version);
            boolean done = false;
            while(scan.hasNextLine() && !done){
                
              
                    
                if(scan.nextLine().equalsIgnoreCase("Rooms:")){
                    
                    while(scan.hasNextLine()){
                        
                        try{
                             rooms.put(Integer.toString(roomCount), new Room(scan));
                             roomCount++;
                        }catch(NoRoomException e){
                             System.out.println("End Of Rooms");
                              done = true;
                             break;
                            
                         
                        }       
                    }
                }
            }
            boolean doneWithExits = false;
            while(scan.hasNextLine() && !doneWithExits){
                
                if(scan.nextLine().equalsIgnoreCase("Exits:")){
                    while(scan.hasNextLine()){
                        try{
                            new Exit(scan, this);
                        }catch(NoExitException e){
                            System.out.println("End of Exits");
                            doneWithExits = true;
                            break;
                        }
                    }
                    
                    
                }
                
                
            }
            
            while(scan.hasNextLine()){
                
                
                if(scan.nextLine().equalsIgnoreCase("Time:")){
                 
                    String[] separated = scan.nextLine().split(":");
                    try{
                    GameState.getDayTimer().setTime(Integer.parseInt(separated[0]), Integer.parseInt(separated[1]));
                    }catch(Exception e){
                        System.out.println("Unable to set time. Defaulting.");
                        GameState.getDayTimer().setTime(12, 0);
                    }
                }
                    
            }
        }
                    
                
         
            
            
            
        
        
        
        this.entry = rooms.get("0");
        
    }
        
     
    /**
     * 
     * @param fileName
     * @param initState
     * Hydrates the dungeon from the file given and decides if 
     * it should load the default start or not with the initState 
     * variable.
     */
    public Dungeon(String fileName, boolean initState){
        
        
        Scanner scan = null;
        int roomCount = 0;
        this.fileName = fileName;
        
        try{
            scan = new Scanner(new File(fileName));
        }catch(Exception e){
            System.out.println("File Not Found.");
            System.exit(0);
        }
        
        this.name = scan.nextLine();
        this.version = scan.nextLine();
        if(!this.version.equalsIgnoreCase("Zork Project v1")){
            System.out.println(this.name + this.version);
            System.out.println("Not an accepted version.");
            System.exit(0);
        }
       
        else{
            System.out.println(this.name + " " + this.version);
            System.out.println();
            boolean done = false;
            while(scan.hasNextLine() && !done){
                 
                String next = scan.nextLine();
                    
                if(next.equalsIgnoreCase("Rooms:")){
                    
                    while(scan.hasNextLine()){
                        
                        try{
                             rooms.put(Integer.toString(roomCount), new Room(scan, this, initState));
                             roomCount++;
                    
                        }catch(NoRoomException e){
                             //System.out.println("End Of Rooms");
                              done = true;
                              break;
                            
                         
                        }       
                    }
                }
                else if(next.equalsIgnoreCase("Items:")){
                    int itemCount = 0;
                    while(scan.hasNextLine()){
                        
                        try{
                            
                            allItems.put(Integer.toString(itemCount), new Item(scan));
                            itemCount++;
                            
                        }catch(NoItemException e){
                            //System.out.println("End of Items.");
                            break;
                        }
                        
                        
                    }
                    
                    
                }
            }
            boolean doneWithExits = false;
             while(scan.hasNextLine() && !doneWithExits){
                
                if(scan.nextLine().equalsIgnoreCase("Exits:")){
                    while(scan.hasNextLine()){
                        try{
                            new Exit(scan, this);
                        }catch(NoExitException e){
                            //System.out.println("End of Exits");
                            doneWithExits = true;
                            break;
                        }
                    }
                    
                    
                }
                
                
            }
             
             
           while(scan.hasNextLine()){
               String next = scan.nextLine();
               if(next.equalsIgnoreCase("Time:")){
                   next = scan.nextLine();
                   String[] time = next.split(":");
                   //System.out.println(time[0] + ":" + time[1]);
                   try{
                   GameState.getDayTimer().setTime(Integer.parseInt(time[0]), Integer.parseInt(time[1]));
                   }catch(Exception e){
                       System.out.println("Unable to set time. Defaulting.");
                       GameState.getDayTimer().setTime(0, 0);
                   }
                   
               }
               else if(next.equalsIgnoreCase("==="))
                   break;
               
           }
            
            
            
        }
        
        
        this.entry = rooms.get("0");
        
        
        
         
        
        
    }
    
    
    
    
    /**
     * 
     * @return Room
     * Returns entry Room
     */
    public Room getEntry(){
        return this.entry;
    }
    
    /**
     * 
     * @return String
     * Returns Dungeon name
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * 
     * @param room 
     * Adds a room to the dungeon.
     */
    public void add(Room room){
       rooms.put(room.getTitle(), room);
    }
    
    /**
     * 
     * @param roomTitle 
     * Returns room title
     */
    public Room getRoom(String roomTitle){
        Set<String> keys = this.rooms.keySet();
        
        for(String key : keys){
            
            if(roomTitle.equalsIgnoreCase(this.rooms.get(key).getTitle())){
                
                return this.rooms.get(key);
            }
        }
        
        return null;
    }
    
    /**
     * 
     * @param wr
     * Saves the current state of the rooms.
     */
    public void storeState(PrintWriter wr){
        
        Set<String> keys = rooms.keySet();
        
        for(String key : keys){
            
            this.rooms.get(key).storeState(wr);
            
        }
        
    
    }
    
    /**
     * 
     * @param scan
     * restores the states of the Rooms from the scanner.
     */
    public void restoreState(Scanner scan){
        
        Set<String> keys = rooms.keySet();
        String currentRoom = scan.nextLine().replace(":", "");
        for(String key : keys){
            if(this.rooms.get(key).getTitle().equalsIgnoreCase(currentRoom)){
                this.rooms.get(key).restoreState(scan); 
                
                
                    currentRoom = scan.nextLine();
                    //System.out.println(currentRoom);
                    currentRoom = currentRoom.replace(":", "");
                
                
                
            }
            
        }
        
        while(scan.hasNextLine()){
        	
        	try{
        		String next = scan.nextLine();
                        //System.out.println(next);
        		for(String key : keys){
        			
        			if(this.rooms.get(key).getTitle().equalsIgnoreCase(next)){
        				GameState.setAdventurersCurrentRoom(this.rooms.get(key));
        				this.entry = this.rooms.get(key);
        			}
        			
        		}
                        break;
        	}catch(Exception e){
        		System.out.println("Unable to find Current Room.");
        	}
        }
        
         
    }
    
    /**
     * 
     * @param name
     * @return Item
     * Returns the item with the name given.
     */
    public Item getItem(String name){
        
        Set<String> keys = allItems.keySet();
        
        for(String key : keys){
            
            
            
            if(allItems.get(key).getPrimaryName().equalsIgnoreCase(name)){
                
                return allItems.get(key);
            }
            
        }
        
        return null;
    }
    
    /**
     * 
     * @param item
     * adds the Item given in the parameter.
     */
    public void add(Item item){
        allItems.put(item.getPrimaryName(), item);
    }
    
    
    
}
