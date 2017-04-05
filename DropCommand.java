
/**
 * This will allow the user to drop items
 * @author Matt
 */

class DropCommand extends Command {

    private String itemName;
/**
 * User gives item to drop
 * @param itemName 
 */
    DropCommand(String itemName) {
        this.itemName = itemName;
    }
/**
 * This will return what occurs when user attempts to drop an item
 * @return 
 */
    public String execute() {
        if (itemName == null || itemName.trim().length() == 0) {
            return "Drop what?\n";
        }
        try {
            Item theItem = GameState.instance().getItemFromInventoryNamed(
                itemName);
            GameState.instance().removeFromInventory(theItem);
            GameState.instance().getAdventurersCurrentRoom().add(theItem);
            return itemName + " dropped.\n";
        } catch (Item.NoItemException e) {
            return "You don't have a " + itemName + ".\n";
        }
    }
}
