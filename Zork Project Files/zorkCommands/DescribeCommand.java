/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorkCommands;
import zorkGame.GameState;
/**
 *
 * @author ZorkTeamSilver
 * This class describes the adventurers current room 
 * in full detail.
 */
public class DescribeCommand extends Command{
    
    /**
     * DescribeCommand Constructor.
     */
    public DescribeCommand(){}
    
    /**
     * This describes the current room in full.
     */
    public String execute(){
        
        
        System.out.println(GameState.getAdventurersCurrentRoom().fullDescribe());
        
        
        return null;
    }
    
}
