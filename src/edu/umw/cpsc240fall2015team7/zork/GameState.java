/**
  *Keeps track of the current state of the zork game.
  *@author Nathanael Woodhead
  */
package edu.umw.cpsc240fall2015team7.zork;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
class GameState{


	private static GameState theInstance;
	private Room adeventurersCurrentRoom;
	private Dungeon map;
	private int load;
	private ArrayList<Item> inventory;
	private ArrayList<String> verbs;
	public class IllegalSaveFormatException extends Exception {};
	/**
	  *Returns a GameState object. If no gamestate object exists creates a new one and returns it. 
	  *@author Nathanael Woodhead
	  */
	public static GameState instance(){
		if(theInstance == null){
			theInstance = new GameState();
		}
		return theInstance;

	}
	/**
	  *An empty constructor. Instead GameState.instance should be called.
	  *@author Nathanael Woodhead
	  */
	private GameState(){
	}
	/**
	  *Initializes the dungeon in this. 
	  *@peram The dungeon object.
	  *@author Nathanael Woodhead
	  */
	void initialize(Dungeon dungeon){
		this.map = dungeon;
		inventory = new ArrayList<Item>();
		verbs = new ArrayList<String>();
		for(Item item : inventory){
			verbs.removeAll(item.getVerbs());
			verbs.addAll(item.getVerbs());
		}
	}
	/**
	  *Returns the total weight of all items in the players inventory.
	  *@author Nathanael Woodhead
	  */
	int getLoad(){
		load = 0;
		for(Item item : inventory){
			load += item.getWeight();
		}
		return load;
	}
	/**
	  *Returns object that is the current room for this. 
	  *@author Nathan Woodhead
	  */
	Room getAdventurersCurrentRoom(){
		return adeventurersCurrentRoom;
	}
	/**
	  *Sets the current room. 
	  *@author Nathan Woodhead
	  */
	void setAdventurersCurrentRoom(Room room){
		adeventurersCurrentRoom = room;
	}
	/**
	  *Returns the dungeon for this. If there is no dungeon then it will return null
	  *@author Nathan Woodhead
	  */
	Dungeon getDungeon(){
		return map;
	}
	/**
	  *Creates a savefile of the current gamestate. Stores the file as myProgress.sav
	  *@author Nathan Woodhead
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
			save.println("Current room: " + adeventurersCurrentRoom.getTitle());
			if(inventory.size()>0){
				save.print("Inventory: ");
				String text = "";
				for(Item item : inventory){
					text = text + (item + ",");
				}
				text = text.substring(0,text.length()-1);
				save.print(text);
			}
			save.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not found.");
		}
	}
	/**
  	*Hydrates the game from a savefile.
	*@throws IllegalSaveFormatException when the .sav does not conform to the proper format.
	*@throws FileNotFoundException When the fils is not found.
	*@throws Dungeon.IllegalDungeonFormatException When the dungeon file contained in the savefile is the invalid
	* @author Nathan Woodhead
	*/	
	void restore(String filename) throws IllegalSaveFormatException, FileNotFoundException, Dungeon.IllegalDungeonFormatException{
		try{
			File f = new File(filename);
			Scanner restore = new Scanner(f);
			if(restore.nextLine().equals("Bork v3.0 save data")){
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
					inventory = new ArrayList<Item>();
					if(restore.hasNextLine()){
						String stuff = restore.nextLine();
						stuff = stuff.substring(11,stuff.length());
						String[] things = stuff.split(",");
						for (String item : things){
							Item crap = map.getItem(item);
							inventory.add(crap);
						}
					}
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
	  *Returns an ArrayList of all the item names in this inventory.
	  *@author Nathan Woodhead
	  */
	ArrayList<String> getInventoryNames(){
		ArrayList<String> names = new ArrayList<String>();
		for(Item x : inventory){
			names.add(x.getPrimaryName());
		}
		return names;
	}
	/**
	  *Adds an Item object to this inventory.
	  *@author Nathan Woodhead
	  */
	void addToInventory(Item item){
		verbs.removeAll(item.getVerbs());
		verbs.addAll(item.getVerbs());
		inventory.add(item);
	}
	/**
	  *Removes the given item object from this inventory.
	  *If item is not in the inventory then it will quietly do nothing
	  */ 
	void removeFromInventory(Item item){
		inventory.remove(item);
	}
	/**
	  *Returns a ArrayList with the contents inventory and then clears the inventory of items.
	  *@author Nathan Woodhead
	  */
	ArrayList<Item> removeAllFromInventory(){
		ArrayList<Item> oldInventoy = new ArrayList<Item>(); 
		for(Item item :inventory){
			oldInventoy.add(item);
		}	
		inventory.clear();
		return oldInventoy;
	}
	/**
	  *Returns the item that goes by the name given in the current room.
	  *@throws Item.NoItemException When there is no item by that name in the current room.
	  *@author Nathanael Woodhead
	  */
	Item getItemInVecinityNamed(String name) throws Item.NoItemException{
		return adeventurersCurrentRoom.getItemNamed(name);
	}
	/**
	  *Returns the Item object in this inventory that goes by the name inputed.
	  *@peram name A item name to look for in the inventory
	  *@author Nathanael Woodhead
	  *@throws Item.NoItemException When there is no item by that name in this inventory.
	  */
	Item getItemFromInventoryNamed(String name) throws Item.NoItemException{
		for(Item item : inventory){
			if(item.goesBy(name)){
				return item;
			}
		}
		throw new Item.NoItemException();
	}
	/**
	  *Returns an ArrayList of verbs. Each verb is a String.
	  *@author Nathanael Woodhead
	  */
	ArrayList<String> getVerbs(){
		return verbs;
	}
}
