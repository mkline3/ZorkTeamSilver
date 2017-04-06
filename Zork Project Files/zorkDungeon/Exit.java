/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorkDungeon;

import java.util.Scanner;
import java.util.*;

/**
 *
 * @author ZorkTeamSilver
 * This class is used to create Exit objects
 */
public class Exit{
    
    
    private String dir;
    private String description;
    private Room dest, src;
    /**
     * 
     * @param dir
     * @param src
     * @param dest
     * Constructor for Exit Class
     */
    public Exit(String dir, Room src, Room dest) {
    	this.dir = dir;
    	this.dest = dest;
    	this.src = src;
    	
    }
    
    /**
     * 
     * @param scan
     * @param dungeon
     * @throws NoExitException
     * Hydrates exits from the scanner object and stores them in the 
     * appropriate rooms.
     */
    public Exit(Scanner scan, Dungeon dungeon)throws NoExitException{
        
        
        String roomSrc = scan.nextLine();
        while(roomSrc.equalsIgnoreCase("---"))
            roomSrc = scan.nextLine();
        //System.out.println(roomSrc);
        if(!roomSrc.equalsIgnoreCase("---") && !roomSrc.equalsIgnoreCase("===")){
        this.dir = scan.nextLine();
        String roomDest = scan.nextLine();
        
        //System.out.println(this.dir);
        //System.out.println(roomDest);
        
        if(dungeon.getRoom(roomSrc) != null)
            this.src = dungeon.getRoom(roomSrc);
        else{
            throw new NoExitException();
        }
        if(dungeon.getRoom(roomDest) != null)
            this.dest = dungeon.getRoom(roomDest);
        else 
            throw new NoExitException();
        
        this.src.addExit(this);
        }
        else if(roomSrc.equalsIgnoreCase("==="))
          throw new NoExitException();
       
    }
    
    /**
     * 
     * @param src
     * @param dest
     * @return Room
     * Returns the Room of the Exit direction
     */
    public Room Exitdir(String src, Room dest){
        return dest;
    }
    /**
     * 
     * @return
     * Describes the exit
     */
    public String describe(){
        return "You can go "+dir+" to go to "+this.dest.getTitle();
    }
    
    /**
     * 
     * @return String
     * Returns Direction of exit
     */
    public String getDir(){
     
        return this.dir;
    }
    
    /**]
     * 
     * @return Room 
     * Gets the current room that the player left from
     */
    public Room getSrc(){
        return this.src;
    }
    
    /**
     * 
     * @return Room
     * Returns the destination of the exit
     */
    public Room getDest(){
        return this.dest;
    }
    
    
    
    public class NoExitException extends Exception{}
}
