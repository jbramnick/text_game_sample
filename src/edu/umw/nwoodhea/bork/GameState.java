package edu.umw.nwoodhea.bork;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
class GameState{


	private static GameState theInstance;
	private Room adeventurersCurrentRoom;
	private Dungeon map;
	private ArrayList<Item> inventory;
	private ArrayList<String> verbs;
	public class IllegalSaveFormatException extends Exception {};

	public static GameState instance(){
		if(theInstance == null){
			theInstance = new GameState();
		}
		return theInstance;

	}
	private GameState(){
	}

	void initialize(Dungeon dungeon){
		this.map = dungeon;
		inventory = new ArrayList<Item>();
		verbs = new ArrayList<String>();
		for(Item item : inventory){
			verbs.removeAll(item.getVerbs());
			verbs.addAll(item.getVerbs());
		}
	}
	Room getAdventurersCurrentRoom(){
		return adeventurersCurrentRoom;
	}
	void setAdventurersCurrentRoom(Room room){
		adeventurersCurrentRoom = room;
	}
	Dungeon getDungeon(){
		return map;
	}
	void store(){
		File s = new File("myProgress.sav");
		PrintWriter save;
		try{
			save = new PrintWriter(s);
			save.println("Bork v3.0 save data");
			map.storeState(save);
			save.println("===");
			save.println("Current room: " + adeventurersCurrentRoom.getTitle());
			save.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not found.");
		}
	}	
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
						String room = restore.nextLine();
						int junk = 14;
						room = room.substring(14,room.length());
						this.adeventurersCurrentRoom = map.getRoom(room);
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
	ArrayList<String> getInventoryNames(){
		ArrayList<String> names = new ArrayList<String>();
		for(Item x : inventory){
			names.add(x.getPrimaryName());
			}
		return names;
		}
	void addToInventory(Item item){
		verbs.removeAll(item.getVerbs());
		verbs.addAll(item.getVerbs());
		inventory.add(item);
		}
	void removeFromInventory(Item item){
		inventory.remove(item);
		}
	Item getItemInVecinityNamed(String name) throws Item.NoItemException{
		return adeventurersCurrentRoom.getItemNamed(name);
		}
	Item getItemFromInventoryNamed(String name) throws Item.NoItemException{
		for(Item item : inventory){
			if(item.goesBy(name)){
				return item;
				}
			}
		throw new Item.NoItemException();
		}
	ArrayList<String> getVerbs(){
		return verbs;
	}
}
