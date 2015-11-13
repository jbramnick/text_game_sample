package edu.umw.cpsc240fall2015team7.zork;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
  *Keeps track of the current state of the zork game.
  *@author Nathanael Woodhead
  */
class GameState{

	private boolean verbose;
	private static GameState theInstance;
	private Room adeventurersCurrentRoom;
	private Dungeon map;
	private int load;
	private ArrayList<String> verbs;
	public class IllegalSaveFormatException extends Exception {};
	/**
	  *Returns this GameState object. If no GameState object exists, creates a new one and returns it. 
	  *@author Nathanael Woodhead
	  */
	public static GameState instance(){
		if(theInstance == null){
			theInstance = new GameState();
		}
		return theInstance;
	}
	/**
	  *Creates a new GameState object. GameState.instance() should be called in order to instantiate a GameState object..
	  *@author Nathanael Woodhead
	  */
	private GameState(){
	}
	/**
	  *Initializes a Dungeon in this GameState instance. Sets the map, creates an empty inventory, and list of verbs. 
	  *@param The Dungeon object.
	  *@author Nathanael Woodhead
	  */
	void initialize(Dungeon dungeon){
		this.map = dungeon;
		verbs = new ArrayList<String>();
		}
	/**
	  *Returns the Dungeon object for this. If there is no Dungeon, will return null.
	  *@author Nathanael Woodhead
	  */
	Dungeon getDungeon(){
		return map;
	}
	/**
	*Returns whether or not verbose is on.
	*@author Carson Meadows
	*/
	public boolean getVerbose () {
		return verbose;
	}
	/**
	*Sets verbose to the passed boolean.
	*@author Carson Meadows
	*/
	public void setVerbose (boolean mew) {
		verbose = mew;
	}
	/**
	  *Creates a savefile of the current GameState. Stores the file as myProgress.sav. Catches the FileNotFoundException and prints
	  *"File not found".
	  *@author Nathanael Woodhead
	  */
	void store(){
		File s = new File("myProgress.sav");
		PrintWriter save;
		try{
			save = new PrintWriter(s);
			save.println("Bork v3.0 save data");
			map.storeState(save);
			save.println("===");
			save.println("Adventurer:");
			save.println("Current room: " + 
				adeventurersCurrentRoom.getTitle());
	/*		if(inventory.size()>0){
				save.print("Inventory: ");
				String text = "";
				for(Item item : inventory){
					text = text + (item + ",");
				}
				text = text.substring(0,text.length()-1);
				save.print(text);
			} */
			
			save.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not found.");
		}
	}
	/**
  	*Hydrates the game from a savefile.
	*@throws IllegalSaveFormatException If the .sav does not conform to the proper format.
	*@throws FileNotFoundException If the file is not found.
	*@throws Dungeon.IllegalDungeonFormatException If the Dungeon file contained in the savefile is invalid.
	* @author Nathanael Woodhead
	*/	
	void restore(String filename) throws IllegalSaveFormatException, FileNotFoundException, Dungeon.IllegalDungeonFormatException{
		try{
			File f = new File(filename);
			Scanner restore = new Scanner(f);
			if(restore.nextLine().equals("Zork v1.0 save data")){
				try{
					String name = restore.nextLine();
					name = name.substring(14,name.length());
					this.map = new Dungeon(name,false); 
					map.restoreState(restore);
					restore.nextLine();
					String room = restore.nextLine();
					int junk = 14;
					room = room.substring(14,room.length());
					this.adeventurersCurrentRoom = map.getRoom(room);
				/*	inventory = new ArrayList<Item>();
					if(restore.hasNextLine()){
						String stuff = restore.nextLine();
						stuff = stuff.substring(11,stuff.length());
						String[] things = stuff.split(",");
						for (String item : things){
							Item crap = map.getItem(item);
							inventory.add(crap);
						}
					} */
				}
				catch(Dungeon.IllegalDungeonFormatException e){
					throw new Dungeon.IllegalDungeonFormatException();
				} 
			}
			else{
				throw new IllegalSaveFormatException();
			}
		}
		catch(IllegalSaveFormatException e){
			throw new IllegalSaveFormatException();
		}
		catch(FileNotFoundException e){
			throw new FileNotFoundException();	
		}
	}

	/**
	  *Returns an ArrayList of all the Item names in the inventory of this.
	  *@author Nathanael Woodhead
	  */
/*	ArrayList<String> getInventoryNames(){
		ArrayList<String> names = new ArrayList<String>();
		for(Item x : inventory){
			names.add(x.getPrimaryName());
		}
		return names;
	} */ 
}
