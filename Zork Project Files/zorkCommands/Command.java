/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorkCommands;


import java.util.Scanner;

/**
 *
 * @author Matt
 * This class is used to create commands for
 * the player. 
 */
public abstract class Command{
    
   
    
    /**
     * 
     * @return String
     * Matches the given string command to the 
     * Command object and executes the command.
     */
    public abstract String execute();
    
    
    
}
