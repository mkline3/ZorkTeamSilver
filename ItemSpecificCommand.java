
/**
 * This class is for if there is an action the user can use on an inventory
 * @author Matt
 */

class ItemSpecificCommand extends Command {

    private String verb;
    private String noun;
                        
/**
 * This takes in the action and item that the user wants
 * @param verb
 * @param noun 
 */
    ItemSpecificCommand(String verb, String noun) {
        this.verb = verb;
        this.noun = noun;
    }
/**
 * This returns what occurs to the item if the user can do said action
 * @return 
 */
    public String execute() {
        
        Item itemReferredTo = null;
        try {
            itemReferredTo = GameState.instance().getItemInVicinityNamed(noun);
        } catch (Item.NoItemException e) {
            return "There's no " + noun + " here.";
        }
        
        String msg = itemReferredTo.getMessageForVerb(verb);
        return (msg == null ? 
            "Sorry, you can't " + verb + " the " + noun + "." : msg) + "\n";
    }
}
