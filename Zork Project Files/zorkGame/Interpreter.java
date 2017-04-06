/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorkGame;

import zorkDungeon.Dungeon;
import zorkCommands.*;
import java.io.File;
import java.util.Scanner;

/** 
 *
 * @author ZorkTeamSilver
 * This class contains basically the command line that 
 * the player uses; taken in by the method promptUser(). 
 *  
 */
public class Interpreter{
    
    
    
    
    /**
     * 
     * @param args
     * 
     * <h> Main Class for Interpreter</h>
     * This main is what the user uses to navigate
     * the world.
     */
    public static void main(String[] args){
    	
        
        boolean initState = true;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a file name: ");
        String fileName = in.nextLine();
        File temp = new File(fileName);
        
        while(!temp.exists() || !fileName.contains(".zork")){
            while(!fileName.contains(".zork")){
                System.out.println("Error, not an accepted file format. \nEnter a file name: ");
                fileName = in.nextLine();
                
                if(fileName.equalsIgnoreCase("q") || fileName.equalsIgnoreCase("quit"))
                	System.exit(0);
            }
        
            
            
            temp = new File(fileName);
        
            if(temp.exists())
                break;
            else{
                System.out.println("Unable to find Dungeon file.\nEnter a file name:");
                fileName = in.nextLine();
            }
        }
        
        
        
        System.out.println("Would you like to load a save file?:");
        String saveFile = in.nextLine();
        boolean finished = false;
        if(saveFile.equalsIgnoreCase("n") || saveFile.equalsIgnoreCase("no"))
            finished = true;
        else{
           System.out.println("Enter a file name:");
           saveFile = in.nextLine();
        }
            while(finished != true){
                 
                if(!saveFile.contains(".sav"))
                    saveFile = saveFile + ".sav";
                temp = new File(saveFile);
                if(temp.exists()){
                finished = true;
                initState = false;
                }
                else{
                    
                    System.out.println("Error, unable to find file. Try again or \"n\" to exit:");
                    saveFile = in.nextLine();
                }
            
            }
        
        
        Dungeon game = null;
        
        
        try{
            game = new Dungeon(fileName, initState);
        }catch(Exception e){
            e.printStackTrace();
        }   
        
        GameState state = new GameState(game);
       
        
        if(initState == false)
            state.restore(saveFile);
        
       
        
        
          
        
        
        
        
        
        CommandFactory commands = new CommandFactory();
        
        
        
        System.out.println(game.getEntry().describe());
        
        String ans2 = "";
        Scanner in2 = new Scanner(System.in);
        while(!ans2.equalsIgnoreCase("q")){
            System.out.print(">");
            ans2 = promptUser(in2);
            
            commands.parse(ans2).execute();
        }
    }
    
    
    public static String promptUser(Scanner in){
        return in.nextLine();
    }
    
    
}
