
/**
 * This class will allow the user to access his inventory
 * @author Matt
 */

import java.util.ArrayList;

class InventoryCommand extends Command {

    InventoryCommand() {
    }
/**
 * This will return what is in the users inventory
 * @return 
 */
    public String execute() {
        ArrayList<String> names = GameState.instance().getInventoryNames();
        if (names.size() == 0) {
            return "You are empty-handed.\n";
        }
        String retval = "You are carrying:\n";
        for (String itemName : names) {
            retval += "   A " + itemName + "\n";
        }
        return retval;
    }
}
