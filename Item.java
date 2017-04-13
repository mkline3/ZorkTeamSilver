
 

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
            
            String[] firstSplit = verbParts[0].split(",");
            
            
            //if the verb has any events attached to it read them, else just get the verb and message
            if(verbParts[0].contains("[")){
                
                //Getting the verb and first event
                String edited = firstSplit[0];
                String[] actionAndFirstEvent = edited.split("\\[");

                String verb = actionAndFirstEvent[0];
                String firstEvent = actionAndFirstEvent[1];
                //This arraylist will catch all the events for this class and format them later
                ArrayList<String> followingEvents = new ArrayList<>();
                String message = verbParts[1];
                
                //add first event
                followingEvents.add(firstEvent);

                
                //If more than one event is present
                if(firstSplit.length > 1){
                    //For each event following the first, add it to the array list
                    for(int i = 1; i < firstSplit.length; i++){
                        followingEvents.add(firstSplit[i]);
                    }
                }
                 //This goes through and finds the last bracket to erase it.
                 for(int i = 0; i < followingEvents.size(); i++){
                    followingEvents.set(i, followingEvents.get(i).replace("]", ""));
                }
                
                
                //Puts the message for the verb in the hashtable
                this.messages.put(verb, message);

                

               

                /**
                 * Left over testing code
                for(String x : followingEvents){

                    System.out.println(x);
                }

                System.out.println(this.messages.get(verb));
                */

                for(String x : followingEvents){
                    
                    String[] openParenthSplit = x.split("\\(");

                    String eventToMake = openParenthSplit[0];
                    int value = 0;
                    try{
                    value = Integer.parseInt(openParenthSplit[1].replace(")", ""));
                    }catch(Exception e){
                            
                    }

                    if(eventToMake.equalsIgnoreCase("Wound"))
                        this.events.put(new Wound(value), verb);
                    else if(eventToMake.equalsIgnoreCase("Score"))
                        this.events.put(new AddScore(value), verb);
                    else if(eventToMake.equalsIgnoreCase("Die"))
                        this.events.put(new Die(), verb);
                    else if(eventToMake.equalsIgnoreCase("DeadTimer"))
                        this.events.put(new Die(value, this), verb);
                    

                }
            }
            else{
                String verb = verbParts[0];
                String message = verbParts[1];
                messages.put(verb, message);
                
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
    
    public String getVerbForEvent(Events event){
        if(events.containsKey(event))
            return events.get(event);
        else
            return null;
    }
    
    public Hashtable<Events, String> itemEvents(){
        return this.events;
    }
    
}
