package edu.umw.cpsc240fall2015team7.zork;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.*;
import java.util.Set;
/**
*The map that the player can explore.
*@author Nathanael Woodhead
*/
public class Dungeon{
	static class IllegalDungeonFormatException extends Exception {}
	private String name;
	private Hashtable <String,Room> map =  new Hashtable<String,Room>(5);
	private Room entry;
	private Hashtable <String, Item> items = new Hashtable<String, Item>(5);
	private String version;
	private String filename;

	/**
	*Constructs this Dungeon from a file.
	*@param filename the .bork filename
	*@param initState when true the dungeon is reset to its original state. If false the items will not be placed in the rooms.
	*@throws IllegalDungeonFormatException If the .bork file does not follow the correct format.
	*@author Carson Meadows and Nathanael Woodhead
	*/
	public Dungeon (String filename, boolean initState) throws IllegalDungeonFormatException{
		this.filename = filename;
		try{
			Scanner scanner = new Scanner(new File(filename));
			this.name = scanner.nextLine();
			System.out.println(this.name);
			version = scanner.nextLine();
			System.out.println(this.version);
			if (!version.equals("Bork v3.0")){
				throw new IllegalDungeonFormatException();
			}

			if(!scanner.nextLine().equals("===")){

				throw new IllegalDungeonFormatException();
			}
			if(scanner.nextLine().equals("Items:")){
				try{
					while(true){
						this.add(new Item(scanner));
					}
				}
				catch(Item.NoItemException e){}
			}

			if(scanner.nextLine().equals("Rooms:")){
				boolean x = true;
				try{

					Room enter = new Room(scanner, this , initState);
					this.entry = enter;
					this.add(entry);
					while(x){
						try{
							this.add(new Room(scanner,this,initState));
						}
						catch(Room.NoRoomException e){
							x = false;
						}
					}
				}
				catch(Room.NoRoomException e){
					x = false;
				}
			}
			else{
				throw new IllegalDungeonFormatException();
			}

			if(scanner.nextLine().equals("Exits:")){
				boolean y = true;
				while(y){
					try{
						new Exit(scanner,this);

					}
					catch(Exit.NoExitException e){
						y = false;
					}

				}
			}
			else{
				throw new IllegalDungeonFormatException();
			}
		}
		catch(FileNotFoundException e){

			System.out.println("File not found");
		}
	}
	/**
	*Returns the entry room object of the dungeon.
	*@author Jim Bramnick
	*/
	public Room getEntry(){
		return entry;
	}
	/**
	*Returns the name of the this Dungeon object.
	*@author Jim Bramnick
	*/
	public String getName(){
		return name;
	}
	/**
	*Adds the passed Room to this Dungeon object.
	*Does nothing if the passed Room is null, or already exists in this Dungeon.
	*@author Jim Bramnick
	*/
	public void add(Room room){
		String title = room.getTitle();
		map.put(title, room);
	}
	/**
	*Returns the Room object with title roomKey and null if no such room exists.
	*@author Jim Bramnick
	*/
	public Room getRoom(String roomKey){
		return map.get(roomKey);
	}
	/**
	*Returns a string of the full path to the file that was used to make this dungeon.
	*@author Jim Bramnick
	*/
	public String getFilename(){
		return filename;
	}
	/**
	*Stores the current state of this dungeon to a file in accordance with the Zork file format.
	*@param save The PrintWriter to be used to save the current state.
	*@author Jim Bramnick
	*/
	void storeState(PrintWriter save){
		save.println("Dungeon file: " + filename);
		save.println("Room states:");
		Set<String> keys = map.keySet();
		for(String key: keys){
			map.get(key).storeState(save);
		}

	}
	/**
	*Restores the state of a previously written Dungeon from a .sav file. Starts reading from the passed Scanner's current state.
	*@param restore The Scanner object.
	*@author Jim Bramnick and Nathanael Woodhead
	*/
	void restoreState(Scanner restore){
		restore.nextLine();
		String title = restore.nextLine();
		while(!title.equals("===")){
			title = title.substring(0,title.length()-1);
			getRoom(title).restoreState(restore,this);
			title = restore.nextLine();
		}

	}
	/**
	*Adds the passed Item to this Dungeon. Does nothing if the Item already exists, or is null.
	*@author Jim Bramnick
	*/
	public void add(Item item){
		String name = item.getPrimaryName();
		items.put(name, item);
		for(String x : item.getSecondaryNames()){
			items.put(x, item);
		}
	}
	/**
	*Returns the Item in this Dungeon named the passed String. Returns null if the Item named is not in this Dungeon.
	*@author Jim Bramnick
	*/
	public Item getItem(String name){
		return items.get(name);
	}
}
