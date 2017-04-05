
/**
 * This allows the user to move in certain directions
 * @author Matt
 */

class MovementCommand extends Command {

    private String dir;
                       
/**
 * Takes in the direction the user wants to go
 * @param dir 
 */
    MovementCommand(String dir) {
        this.dir = dir;
    }
/**
 * This returns where the user goes to if he used an accurate direction 
 * @return 
 */
    public String execute() {
        Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
        Room nextRoom = currentRoom.leaveBy(dir);
        if (nextRoom != null) {  // could try/catch here.
            GameState.instance().setAdventurersCurrentRoom(nextRoom);
            return "\n" + nextRoom.describe() + "\n";
        } else {
            return "You can't go " + dir + ".\n";
        }
    }
}
