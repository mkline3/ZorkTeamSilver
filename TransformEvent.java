
/**
 *
 * @author Nathan
 */
public class TransformEvent implements Events {
    private String itemName;
    
    public TransformEvent(String itemName) {
        this.itemName = itemName;
    }
    
    @Override
    public void execute() {
        
    }

    @Override
    public boolean hasCalledTimer() {
        return false;
    }

    @Override
    public void setHasCalledTimer(boolean x) {
        
    }
}
