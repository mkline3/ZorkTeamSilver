
import java.util.Scanner;
import java.util.Hashtable;


public class Item {
    static class NoItemException extends Exception {}
    
    private String primaryName;
    private int weight;
    private Hashtable<String,String> messages;
    private Hashtable<String,Event[]> events;
    
    
    Item(Scanner s) throws NoItemException,
        Dungeon.IllegalDungeonFormatException {
        
        messages = new Hashtable<String,String>();
        events = new Hashtable<String,Event[]>();
        
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
            if (verbParts[0].contains("[")) {
                String[] vsub = verbParts[0].substring(verbParts[0].indexOf("[")+1, verbParts[0].indexOf("]")).split(",");
                String[] forVerb = new String[vsub.length];
                // Create event by name and pass the parameter
                
                messages.put(verbParts[0].substring(0,verbParts[0].indexOf("[")), verbParts[1]);
                for (String v : vsub) {
                    forVerb[0] = v.substring(0, v.indexOf("("));
                }
                //events.put(verbParts[0].substring(0, verbParts[0].indexOf("[")), forVerb);
            } else {
                messages.put(verbParts[0], verbParts[1]);
            }
            
            verbLine = s.nextLine();
        }
    }
    
    boolean goesBy(String name) {
        // could have other aliases
        return this.primaryName.equals(name);
    }
    
    String getPrimaryName() { return primaryName; }
    
    public String getMessageForVerb(String verb) {
        return messages.get(verb);
    }
    
    public String toString() {
        return primaryName;
    }
}
