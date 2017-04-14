
/**
 *
 * @author Nathan
 */
public class DisappearEvent implements Events {
    private Item orig;
    
    public DisappearEvent(Item orig) {
        this.orig = orig;
    }
    
    @Override
    public void execute() {
        try {
            GameState gs = GameState.instance();
            
            if (gs.getItemFromInventoryNamed(orig.getPrimaryName()) != null) {
                gs.removeFromInventory(orig);
            } else if (gs.getAdventurersCurrentRoom().getItemNamed(orig.getPrimaryName()) != null) {
                gs.getAdventurersCurrentRoom().remove(orig);
            }
        } catch (Item.NoItemException ex) {
            
        }
    }

    @Override
    public boolean hasCalledTimer() { return false; }

    @Override
    public void setHasCalledTimer(boolean x) {}
}
