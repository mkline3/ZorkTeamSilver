
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Nathan
 */
public class TransformEvent implements Events {
    private String itemName;
    private Item orig;
    
    public TransformEvent(String itemName, Item orig) {
        this.itemName = itemName;
        this.orig = orig;
    }
    
    @Override
    public void execute() {
        Item newItem;
        
        try {
            //Places to change:
            //CurrentRoom
            //Inventory
            newItem = GameState.instance().getDungeon().getItem(itemName);
            GameState gs = GameState.instance();
            
            if (gs.getItemFromInventoryNamed(orig.getPrimaryName()) != null) {
                gs.removeFromInventory(orig);
            }
        } catch (Item.NoItemException ex) {
            
        }
    }

    @Override
    public boolean hasCalledTimer() { return false; }

    @Override
    public void setHasCalledTimer(boolean x) {}
}
