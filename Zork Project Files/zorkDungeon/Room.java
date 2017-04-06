/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorkDungeon;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import zorkItems.*;
import zorkGame.GameState;

/**
 *
 * @author ZorkTeamSilver
 * This class creates rooms that the player navigates through. Exits 
 * are also stored in the classes.
 * 
 * 
 */
public class Room{
    
    private String title;
    private String description = "";
    private boolean beenHere;
    
    private ArrayList<Exit> exits = new ArrayList();
    private ArrayList<Item> items = new ArrayList();
    
    /**
     * 
     * @param title
     * This creates a Room object with the desired title.
     */
    public Room(String title){
    	
        this.title = title;
    }
    
    /**
     * 
     * @param scan
     * @throws NoRoomException
     * Hydrates room details from the scanner.
     */
    public Room(Scanner scan)throws NoRoomException{
        
        this.title = scan.nextLine();
      
        if(this.title.equalsIgnoreCase("==="))
            throw new NoRoomException();
        
        String next = scan.nextLine();
        
        
        
        while(!next.equalsIgnoreCase("---")){
   
            if(next.equalsIgnoreCase("---"))
                break;
            this.description = this.description + "\n" + next;
            next = scan.nextLine();
            
            //System.out.println(scan.nextLine());
            //System.out.println(this.description);
        }
   
    }
    
    /**
     * 
     * @param scan
     * @param d
     * @param initState
     * @throws NoRoomException
     * This hydrates the room details from the scanner, stores itself in the dungeon and 
     * finally decides whether or not it loads itself from a save file.
     */
    public Room(Scanner scan, Dungeon d, boolean initState) throws NoRoomException{
        
        this.title = scan.nextLine();
        
        if(this.title.equalsIgnoreCase("==="))
            throw new NoRoomException();
      
        String next = scan.nextLine();
      
        
        if(next.equalsIgnoreCase("Contents:") && initState == true){
            
            String items = scan.nextLine();
            String[] separated = items.split(",");
            
            for(int i = 0; i < separated.length; i++){
                
             
                this.add(d.getItem(separated[i]));
                
            }
            next = scan.nextLine();
        }
        else if(next.equalsIgnoreCase("Contents:")){
            scan.nextLine();
            next = scan.nextLine();
        }
      
      
        
        
    
        while(!next.equalsIgnoreCase("---")){
   
            if(next.equalsIgnoreCase("---"))
                break;
            this.description = this.description + "\n" + next;
            next = scan.nextLine();
            
            
        }
       
        
        
        
    }
    
    /**
     * 
     * @return
     * Returns title
     */
    public String getTitle(){
        return this.title;
    }
    
    /**
     * 
     * @param description
     * Sets the description for the room
     */
    public void setDescription(String description){
        this.description = description;
    }
    
    /**
     * 
     * @return
     * Returns the Room description
     */
    public String describe(){
        if(beenHere)
            System.out.println(title);
        else
            System.out.println(title + "\n" + description);
        if(!items.isEmpty()){
            for(Item x : items){
            
                 System.out.println(x.getPrimaryName() + " is here.");
            
            }
        }
        
        
        for(Exit i : this.exits){
        	System.out.println(i.describe());
        }
        beenHere = true;
        return "";
        
    }
    
    /**
     * 
     * @param dir
     * @return
     * Returns the destination of the exit direction
     */
    public Room leaveBy(String dir){
        for(int i = 0; i < exits.size(); i++){
            if(exits.get(i).getDir().equalsIgnoreCase(dir))
                return exits.get(i).getDest();
            
        }
        return exits.get(0).getSrc();
    }
    
    /**
     * 
     * @param exit
     * Adds an exit to the room
     */
    public void addExit(Exit exit){
        exits.add(exit);
    }
    
    /**
     * 
     * @return
     * Returns the Exits array of the Room
     */
    public ArrayList<Exit> getExits(){
        return exits;
    }

    /**
     * 
     * @param wr
     * Stores the current state of the room
     */
    public void storeState(PrintWriter wr){
        
        wr.println(this.title+":");
        wr.println("beenHere="+this.beenHere);
        if(!this.items.isEmpty()){
            wr.println("Contents:");
            for(Item x : this.items){
                
                wr.print(x.getPrimaryName() +",");
            }
            wr.println();
        }
        wr.println("---");
        
    }
    
    /**
     * 
     * @param scan
     * Restores the rooms state from the scanner object given.
     */
    public void restoreState(Scanner scan){
        
        String next = scan.nextLine();
        if(next.contains("true"))
            this.beenHere = true;
        else 
            this.beenHere = false;
        
        next = scan.nextLine();
        //System.out.println(next);
        if(next.equalsIgnoreCase("Contents:")){
         
            String[] separated = scan.nextLine().split(",");
            
            for(int i = 0; i < separated.length; i++){
                //System.out.println(separated[i]);
                if(!separated[i].equalsIgnoreCase(""))
                    this.add(GameState.getDungeon().getItem(separated[i]));
            }
           scan.nextLine();
        }
        
        
    }
    
    /**
     * 
     * @param item
     * Adds an item to the room.
     */
    public void add(Item item){
        if(items == null)
            items = new ArrayList<>();
    items.add(item);
    }
    
    /**
     * 
     * @param item
     * removes an item from the room.
     */
    public void remove(Item item){
    items.remove(item);
    }
    
    /**
     * 
     * @param name
     * @return Item
     * Returns the item with the given name if its in the room. 
     * 
     */
    public Item getItemNamed(String name){
        
        for(Item x : items){
            
            if(x.goesBy(name))
                return x;
        }
        
        return null;
    }
    
    /**
     * 
     * @return ArrayList<Item>
     * returns the contents of a room.
     */
    public ArrayList<Item> getContents(){
        
        return items;
    }
    
    /**
     * 
     * @return String
     * Describes the room in full.
     */
    public String fullDescribe(){
        
        System.out.println(this.title);
        System.out.println(this.description);
        if(!this.items.isEmpty()){
            
            for(Item x : this.items){
                
                System.out.println(x.getPrimaryName() + " is here.");
                
            }
            
        }
       
        for(Exit i : this.exits){
            
            System.out.println(i.describe());
            
        }
        
      return "";  
    }
    
    
    /**
     * 
     * @author ZorkTeamSilver
     * <h>NoRoomExceptionClass</h>
     *
     */
    public class NoRoomException extends Exception{}
    
}
