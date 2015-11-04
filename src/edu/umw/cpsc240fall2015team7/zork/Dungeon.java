/**
*The dungeon class is the current layout of the rooms,items and NPCs.
*@author Nathanael Woodhead
*/
package edu.umw.cpsc240fall2015team7.zork;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.*;
import java.util.Set;
public class Dungeon{
	static class IllegalDungeonFormatException extends Exception {}
	private String name;
	private Hashtable <String,Room> map =  new Hashtable<String,Room>(5);
	private Room entry;
	private Hashtable <String, Item> items = new Hashtable<String, Item>(5);
	private String version;
	private String filename;
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
	*returns the entry room object of the dungeon.
	*@return entry room.
	*/
	public Room getEntry(){
		return entry;
	}
	/**
	*returns the name of the this dungeon object.
	*@return name of dungeon.
	*/
	public String getName(){
		return name;
	}
	/**
	*adds the room to this dungeon object.
	*If room already exisits in this dungeon does nothing.If room is null does nothing.
	*@param room to be added
	*/
	public void add(Room room){
		String title = room.getTitle();
		map.put(title, room);
	}
	/**
	*returns the room with title roomKey and null if no such room exsists.
	*@return room with title roomKey
	*/
	public Room getRoom(String roomKey){
		return map.get(roomKey);
	}
	/**
	*returns the a string of the full path to the file that was used to make the dungeon.
	*@return string of the full path to file
	*/
	public String getFilename(){
		return filename;
	}
	/**
	*Stores the current state of this dungeon to a file in accordance with the zork file format.
	*Takes the location of the printwriter and starts writing the dungeon from there.
	*@param the current printwriter used to save
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
	*Restores the state of a previously written dungeon from a .sav file. Starts reading from the restore's current state.
	*@param the current scanner used in reading the save file
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
	*adds the item to this dungeon. if the item already exisists, does nothing. if item is null, does nothing.
	*@param the item to be added
	*/
	public void add(Item item){
		String name = item.getPrimaryName();
		items.put(name, item);
		for(String x : item.getSecondaryNames()){
			items.put(x, item);
		}
	}
	/**
	*returns the item in this dungeon by the title name. if the item by title name is not in the dungeon returns null.
	*@param the name of the item to be returned
	*/
	public Item getItem(String name){
		return items.get(name);
	}
}
