package zorkItems;

import java.io.File;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author ZorkTeamSilver
 *This class is used to create Items in the dungeon.
 */
public class Item {

	
	private String primaryName;
	private int weight;
	private Hashtable<String, String> messages = new Hashtable<>();
	
	
        
    /**
     *     
     * @param scan
     * @throws NoItemException
     * hydrates items from the given scanner object.
     */
	public Item(Scanner scan) throws NoItemException{
		
            //String daItems = scan.nextLine();
            //String[] separatedItems = daItems.split(",");
            
            this.primaryName = scan.nextLine();
            
            if(this.primaryName.equalsIgnoreCase("==="))
                throw new NoItemException();
            
            //System.out.println(this.primaryName);
            this.weight = Integer.parseInt(scan.nextLine());
            
            //System.out.println(this.weight);
            String nextLine = scan.nextLine();
            
            while(!nextLine.equalsIgnoreCase("---")){
                if(nextLine.equalsIgnoreCase("---"))
                    break;
                //System.out.println(nextLine);
                this.messages.put(nextLine.replace(":", ""), scan.nextLine());
                
                nextLine = scan.nextLine();
            }
            
           
		
           
	}
	
	
	/**
	 * 
	 * @param name
	 * @return
	 * Returns true or false if the item goes by the given name.
	 */
	public boolean goesBy(String name){
		
		return false;
	}
	
	/**
	 * 
	 * @return
	 * Returns the items primary name.
	 */
	public String getPrimaryName(){
		
		return this.primaryName;
	}
	
	/**
	 * 
	 * @param verb
	 * @return
	 * Returns the corresponding message for the given verb.
	 */
	public String getMessageForVerb(String verb){
		
		return this.messages.get(verb);
	}
	
	/**
	 * Converts to string.
	 */
	public String toString(){
		
		return "";
	}
	
        
        
        
	
}
