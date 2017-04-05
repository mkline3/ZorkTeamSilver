
/**
 * This is so the user can save progress
 * @author Matt
 */

class SaveCommand extends Command {

    private static String DEFAULT_SAVE_FILENAME = "bork";

    private String saveFilename;
/**
 * This takes the name that the user wants his progress saved to
 * @param saveFilename 
 */
    SaveCommand(String saveFilename) {
        if (saveFilename == null || saveFilename.length() == 0) {
            this.saveFilename = DEFAULT_SAVE_FILENAME;
        } else {
            this.saveFilename = saveFilename;
        }
    }
/**
 * This saves user progress to a file
 * returns a string to inform the user if the file could be saved or not
 * @return 
 */
    public String execute() {
        try {
            GameState.instance().store(saveFilename);
            return "Data saved to " + saveFilename +
                GameState.SAVE_FILE_EXTENSION + ".\n";
        } catch (Exception e) {
            System.err.println("Couldn't save!");
            e.printStackTrace();
            return "";
        }
    }
}
