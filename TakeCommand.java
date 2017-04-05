
/**
 * This class is for the user to pick up items
 * @author Matt
 */

class TakeCommand extends Command {

    private String itemName;
/**
 * This takes what item the user wants to take
 * @param itemName 
 */
    TakeCommand(String itemName) {
        this.itemName = itemName;
    }
/**
 * This returns that the user has taken the item,if he can
 * adds item to inventory
 * @return 
 */
    public String execute() {
        if (itemName == null || itemName.trim().length() == 0) {
            return "Take what?\n";
        }
        try {
            Room currentRoom = 
                GameState.instance().getAdventurersCurrentRoom();
            Item theItem = currentRoom.getItemNamed(itemName);
            GameState.instance().addToInventory(theItem);
            currentRoom.remove(theItem);
            return itemName + " taken.\n";
        } catch (Item.NoItemException e) {
            // Check and see if we have this already. If no exception is
            // thrown from the line below, then we do.
            try {
                GameState.instance().getItemFromInventoryNamed(itemName);
                return "You already have the " + itemName + ".\n";
            } catch (Item.NoItemException e2) {
                return "There's no " + itemName + " here.\n";
            }
        }
    }
}
