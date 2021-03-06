
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GameState {

    public static class IllegalSaveFormatException extends Exception {
        public IllegalSaveFormatException(String e) {
            super(e);
        }
    }

    static String DEFAULT_SAVE_FILE = "zork_save";
    static String SAVE_FILE_EXTENSION = ".sav";
    static String SAVE_FILE_VERSION = "Zork v1.0 save data";

    static String ADVENTURER_MARKER = "Adventurer:";
    static String CURRENT_ROOM_LEADER = "Current room: ";
    static String INVENTORY_LEADER = "Inventory: ";
    static String HEALTH_LEADER = "Health: ";
    static String SCORE_LEADER = "Score: ";

    private static GameState theInstance;
    private Dungeon dungeon;
    private ArrayList<Item> inventory;
    private Room adventurersCurrentRoom;
    private static boolean isLightOut = true;
    
    private static int adventurerHealth = 150;
    private static int adventurerScore = 0;

    static synchronized GameState instance() {
        if (theInstance == null) {
            theInstance = new GameState();
        }
        return theInstance;
    }

    private GameState() {
        inventory = new ArrayList<Item>();
    }

    void restore(String filename) throws FileNotFoundException,
        IllegalSaveFormatException, Dungeon.IllegalDungeonFormatException {

        Scanner s = new Scanner(new FileReader(filename));

        if (!s.nextLine().equals(SAVE_FILE_VERSION)) {
            throw new IllegalSaveFormatException("Save file not compatible.");
        }

        String dungeonFileLine = s.nextLine();

        if (!dungeonFileLine.startsWith(Dungeon.FILENAME_LEADER)) {
            throw new IllegalSaveFormatException("No '" +
                Dungeon.FILENAME_LEADER + 
                "' after version indicator.");
        }

        dungeon = new Dungeon(dungeonFileLine.substring(
            Dungeon.FILENAME_LEADER.length()), false);
        dungeon.restoreState(s);

        s.nextLine();  // Throw away "Adventurer:".
        String currentRoomLine = s.nextLine();
        adventurersCurrentRoom = dungeon.getRoom(
            currentRoomLine.substring(CURRENT_ROOM_LEADER.length()));
        while (s.hasNext()) {
            String next = s.nextLine();
            if(next.contains(INVENTORY_LEADER)){
                String inventoryList = next.substring(
                    INVENTORY_LEADER.length());
                String[] inventoryItems = inventoryList.split(",");
                for (String itemName : inventoryItems) {
                    try {
                        addToInventory(dungeon.getItem(itemName));
                    } catch (Item.NoItemException e) {
                        throw new IllegalSaveFormatException("No such item '" +
                            itemName + "'");
                    }
                }
            }
            else if(next.contains(HEALTH_LEADER)){
                    String[] sep = next.split(":");
                    this.setHealth(Integer.parseInt(sep[1].replace(" ", "")));

            }
            else if(next.contains(SCORE_LEADER)){
                    String[] sep = next.split(":");
                    this.setScore(Integer.parseInt(sep[1].replace(" ", "")));
            }
        }
    }

    void store() throws IOException {
        store(DEFAULT_SAVE_FILE);
    }

    void store(String saveName) throws IOException {
        String filename = saveName + SAVE_FILE_EXTENSION;
        PrintWriter w = new PrintWriter(new FileWriter(filename));
        w.println(SAVE_FILE_VERSION);
        dungeon.storeState(w);
        w.println(ADVENTURER_MARKER);
        w.println(CURRENT_ROOM_LEADER + adventurersCurrentRoom.getTitle());
        if (inventory.size() > 0) {
            w.print(INVENTORY_LEADER);
            for (int i=0; i<inventory.size()-1; i++) {
                w.print(inventory.get(i).getPrimaryName() + ",");
            }
            w.println(inventory.get(inventory.size()-1).getPrimaryName());
        }
        
        w.print(HEALTH_LEADER);
        w.print(GameState.instance().getHealth());
        w.println();
        w.print(SCORE_LEADER);
        w.print(GameState.instance().getScore());
        
        w.close();
    }

    void initialize(Dungeon dungeon) {
        this.dungeon = dungeon;
        adventurersCurrentRoom = dungeon.getEntry();
        System.out.println(adventurersCurrentRoom.getTitle());
    }

    ArrayList<String> getInventoryNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Item item : inventory) {
            names.add(item.getPrimaryName());
        }
        return names;
    }

    void addToInventory(Item item) /* throws TooHeavyException */ {
        inventory.add(item);
    }

    void removeFromInventory(Item item) {
        inventory.remove(item);
    }

    Item getItemInVicinityNamed(String name) throws Item.NoItemException {

        // First, check inventory.
        for (Item item : inventory) {
            if (item.goesBy(name)) {
                return item;
            }
        }

        // Next, check room contents.
        for (Item item : adventurersCurrentRoom.getContents()) {
            if (item.goesBy(name)) {
                return item;
            }
        }

        throw new Item.NoItemException();
    }

    Item getItemFromInventoryNamed(String name) throws Item.NoItemException {

        for (Item item : inventory) {
            if (item.toString().equalsIgnoreCase(name)) {
                return item;
            }
        }
        throw new Item.NoItemException();
    }

    Room getAdventurersCurrentRoom() {
        return adventurersCurrentRoom;
    }

    void setAdventurersCurrentRoom(Room room) {
        adventurersCurrentRoom = room;
    }

    Dungeon getDungeon() {
        return dungeon;
    }
    
    int getHealth(){
    	return this.adventurerHealth;
    }
    
    void setHealth(int health){
    	this.adventurerHealth = health;
    }
    
    void reduceHealth(int damageTaken){
    	if(damageTaken > 0){
    		System.out.println("You took " + damageTaken + " damage!");
    		this.adventurerHealth = adventurerHealth - damageTaken;
    	}
    	else if(damageTaken < 0){
    		System.out.println("You gained " + damageTaken + " health!");
    	}
    }
    
    void addHealth(int healthToAdd){
    	this.adventurerHealth += healthToAdd;
    }
    void setScore(int x){
    	this.adventurerScore = x;
    }
    int getScore(){
    	return this.adventurerScore;
    }
    
    void addScore(int s){
    	this.adventurerScore += s;
    }
    
    void reduceScore(int s){
    	this.adventurerScore -= s;
    }
    
    void setLight(boolean x){
    	this.isLightOut = x;
    }

}
