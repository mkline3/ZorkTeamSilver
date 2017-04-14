
 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class Item {

    static class NoItemException extends Exception {}

    private String primaryName;
    private int weight;
    private Hashtable<String,String> messages;
    private Hashtable<Events, String> events = new Hashtable<>();
   

    Item(Scanner s) throws NoItemException,
        Dungeon.IllegalDungeonFormatException {

        messages = new Hashtable<String,String>();
        

        // Read item name.
        primaryName = s.nextLine();
        if (primaryName.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoItemException();
        }

        // Read item weight.
        weight = Integer.valueOf(s.nextLine());

        // Read and parse verbs lines, as long as there are more.
        String verbLine = s.nextLine();
        while (!verbLine.equals(Dungeon.SECOND_LEVEL_DELIM)) {
            if (verbLine.equals(Dungeon.TOP_LEVEL_DELIM)) {
                throw new Dungeon.IllegalDungeonFormatException("No '" +
                    Dungeon.SECOND_LEVEL_DELIM + "' after item.");
            }
            
            String[] verbParts = verbLine.split(":");
            
            //if the verb has any events attached to it read them, else just get the verb and message
            if(verbParts[0].contains("[")){
                // Get only the stuff between the brackets, and split it by comma
                String[] firstSplit = verbParts[0].substring(verbParts[0].indexOf("[")+1,verbParts[0].indexOf("]")).split(",");
                ArrayList<Events> forVerb = new ArrayList<Events>();
                String verb = verbParts[0].substring(0, verbParts[0].indexOf("["));
                
                for (String evLine : firstSplit) {
                    String evName = "";
                    String evParam = "";
                    
                    if (evLine.contains("(")) {
                        evName = evLine.substring(0, evLine.indexOf("("));
                        evParam = evLine.substring(evLine.indexOf("(")+1, evLine.indexOf(")"));
                    } else {
                        evName = evLine;
                    }
                    if (evName.equalsIgnoreCase("DeadTimer"))
                        this.events.put(new Die(Integer.parseInt(evParam), this), verb);
                    else if (evName.equalsIgnoreCase("Die"))
                    	this.events.put(new Die(), verb);
                    else if (evName.equalsIgnoreCase("Disappear"))
                    	this.events.put(new DisappearEvent(this), verb);
                    else if (evName.equalsIgnoreCase("Score"))
                    	this.events.put(new AddScore(Integer.parseInt(evParam)), verb);
                    else if (evName.equalsIgnoreCase("Transform"))
                    	this.events.put(new TransformEvent(evParam, this), verb);
                    else if (evName.equalsIgnoreCase("Wound"))
                    	this.events.put(new Wound(Integer.parseInt(evParam)), verb);
                }
                // Check to make sure verb's message gets saved
                
            } else {
                String verb = verbParts[0];
                String message = verbParts[1];
                messages.put(verb, message);
            }
            
            verbLine = s.nextLine();
        }
    }

    boolean goesBy(String name) {
        // could have other aliases
        return this.primaryName.equalsIgnoreCase(name);
    }

    String getPrimaryName() { return primaryName; }

    public String getMessageForVerb(String verb) {
        return messages.get(verb);
    }

    public String toString() {
        return primaryName;
    }
    
    public Hashtable<Events, String> itemEvents(){
        return this.events;
    }
}
