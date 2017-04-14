
<<<<<<< HEAD
=======
import java.util.logging.Level;
import java.util.logging.Logger;


>>>>>>> 0f81ab290d085faae77a21c75dbaa9f82e3efa4b
/**
 *
 * @author Nathan
 */
public class TransformEvent implements Events {
    private String itemName;
<<<<<<< HEAD
    
    public TransformEvent(String itemName) {
        this.itemName = itemName;
=======
    private Item orig;
    
    public TransformEvent(String itemName, Item orig) {
        this.itemName = itemName;
        this.orig = orig;
>>>>>>> 0f81ab290d085faae77a21c75dbaa9f82e3efa4b
    }
    
    @Override
    public void execute() {
<<<<<<< HEAD
        
    }

    @Override
    public boolean hasCalledTimer() {
        return false;
    }

    @Override
    public void setHasCalledTimer(boolean x) {
        
    }
=======
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
>>>>>>> 0f81ab290d085faae77a21c75dbaa9f82e3efa4b
}
