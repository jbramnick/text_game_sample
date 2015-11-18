package edu.umw.cpsc240fall2015team7.zork;
import java.util.Iterator;
import java.lang.reflect.Constructor;
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
	private Hashtable <String, Weapon> weapons = new Hashtable <String, Weapon>(5);
	private Hashtable <String, Item> items = new Hashtable<String, Item>(5);
	private String version;
	private String filename;

	/**
	*Constructs this Dungeon from a file.
	*@param filename The .zork filename
	*@param initState If true, this Dungeon is reset to its original state. If false, the Items will not be placed in the Rooms.
	*@throws IllegalDungeonFormatException If the .zork file does not follow the correct format.
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
			if (!version.equals("Zork v1.0")){
				throw new IllegalDungeonFormatException();
			}

			if(!scanner.nextLine().equals("===")){

				throw new IllegalDungeonFormatException();
			}
			String custom = scanner.nextLine();
			if(custom.equals("Weapons:")){
				try{
					while(true){
						String type = scanner.nextLine();
						type = "edu.umw.cpsc240fall2015team7.zork."+type;
						Class clazz = Class.forName(type);
						Constructor con = clazz.getDeclaredConstructor(Scanner.class);
						Weapon weapon = (Weapon)con.newInstance(scanner);
						this.addWeapon(weapon);
					}
				}
				catch(Exception e){
				custom = scanner.nextLine();
				}
			}
			if(custom.equals("Items:")){
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
	*Returns the entry room object of this Dungeon.
	*@author Jim Bramnick
	*/
	public Room getEntry(){
		return entry;
	}
	/**
	*Returns the name of this Dungeon object.
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
	*Returns the Room object with title roomKey and null if no such Room exists.
	*@author Jim Bramnick
	*/
	public Room getRoom(String roomKey){
		return map.get(roomKey);
	}
	/**
	*Returns an ArrayList of Room titles.
	*@author Carson Meadows
	*/
	public ArrayList <String> getKeys () {
		ArrayList<String> roomNames = new ArrayList<String>();
		Iterator keys = map.keySet().iterator();
		while(keys.hasNext()){
			roomNames.add(keys.next().toString());
		}
		return roomNames;
	}
	/**
	*Returns a String of the full path to the file that was used to make this Dungeon.
	*@author Jim Bramnick
	*/
	public String getFilename(){
		return filename;
	}
	/**
	*Stores the current state of this Dungeon to a file in accordance with the .sav file format.
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
	*Removes passed Item from Hashtable items.
	*@author Carson Meadows
	*/
	public void removeItem (Item item) {
		items.remove(item.getPrimaryName());
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
	/**
	  *Adds a weapon to the this Dungeon. Does nothing if the weapon already exists, or is null.
	  *@author Nathanael Woodhead
	  */
	void addWeapon(Weapon weapon){
		String name = weapon.getName();
		weapons.put(name, weapon);
		for(String x : weapon.getSecondaryNames()){
			weapons.put(x,weapon);
		}
	}
	/**
	  Returns the weapon with the given name. If no weapon by that name exists in this Dungeon returns null.
	  @param name The name of a weapon to look for.
	  @author Nathanael Woodhead
	  */
	Weapon getWeapon(String name){
		return weapons.get(name);
	}
}
