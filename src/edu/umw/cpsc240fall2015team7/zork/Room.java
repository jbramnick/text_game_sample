package edu.umw.cpsc240fall2015team7.zork;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
/**
*Room objects make up the Dungeon, can be entered and exited, and can hold Items.
*@author Carson Meadows
*/
public class Room{
	static class NoRoomException extends Exception {}
	private String title;
	private String desc = "";
	private int ammo, snacks, medkits;
	private ArrayList<Exit> exits;
	private boolean beenHere = false;
	private ArrayList<Item> contents;

	/**
	*Constructs basic Room.
	*@author Carson Meadows
	*/
	public Room(String title){
		this.title = title;
		exits = new ArrayList<Exit>();
		contents = new ArrayList<Item>();

	}
	/**
	*Constructs this Room based on passed Scanner object's contents. Reads from
	*Scanner to get this Room's title, contents and description.
	*@param d The current Dungeon object.
	*@param initState If true, Items will be reset to initial state. If false, Items will be set to saved state.
	*@author Carson Meadows and Nathanael Woodhead
	*/
	
	public Room(Scanner scanner,Dungeon d, boolean initState) throws NoRoomException{
		this.title = scanner.nextLine();
		if(this.getTitle().equals("===")){
			throw new NoRoomException();
		}
		exits = new ArrayList<Exit>();
		contents = new ArrayList<Item>();
		String content = scanner.nextLine();
		if(content.contains("Contents: ")){
			if(initState == true){
				content = content.substring(10,content.length());
				String[] list = content.split(",");
				for(String x : list){
					this.add(d.getItem(x));
				}
			}
		}
		else if(content != null){
			this.desc = content;
		}
		String part = scanner.nextLine();
		while(!part.equals("---")){
			this.desc = this.desc + "\n" + part;
			part = scanner.nextLine();
		}
	}
	/**
	*Sets this Room's description as the parameter.
	*@author Carson Meadows
	*/
	public void setDesc(String desc){
		this.desc = desc;
	}
	/**
	*Returns information about this Room. If the player has never been to this room, 
	*returned text includes Room's title, description, exits, and contents.
	*Otherwise returns only exits and contents.
	*@param beenHere If false, will give a full description.
	*@author Nathanael Woodhead
	*/
	String describe(boolean beenHere){
		String text = title;
		this.beenHere = beenHere;
		if(beenHere == false){
			beenHere = true;
			text = text+": "+desc;
		}
		for(Exit exit: exits){
			text = text +"\n"+exit.describe();
		}
		if(contents.size()>0){
			text = text + "\n";
			for(Item item: contents){
				text = text + "\n" + "There is a " + item + " here."; 
			}
			text = text.substring(0,text.length()-2);
		}
		return text;
	}
	/**
        *Returns information about this Room. If the player has never been here,
        *returned text includes Room's title, description, exits, and contents.
        *If the player has been here before, returns only exits and contents.
        *@author Carson Meadows
	*/
	String describe(){
		String text = title;
		if(beenHere == false){
			beenHere = true;
			text = text+": "+desc;
		}
		for(Exit exit: exits){
			text = text +"\n"+exit.describe();
		}
		if(contents.size()>0){
			text = text + "\n";
			for(Item item: contents){
				text = text + "\n" + "There is a " + item + " here."; 
			}
		}
		return text;
	}
	

	/**
	*Returns this Room's title.
	*@author Carson Meadows
	*/
	public String getTitle(){
		return title;
	}
	/**
	*Adds passed Exit to this Room's Exits.
	*@author Carson Meadows
	*/
	public void addExit(Exit exit){
		exits.add(exit);
	}
	/**
	*Returns Room connected to the Exit associated with the passed direction. 
	*If this Room has no Exits associated with the passed direction, throws
	* IllegalArgumentException.
	*@author Carson Meadows
	*/
	Room leaveBy(String dir){
		Exit out = null;
		boolean found = false;
		for(Exit exit : exits){
			if(exit.getDir().equals(dir)){
				out = exit;
				found = true;
			}}
		if(found == true){
			return out.getDest();
		}
		else{
			throw new IllegalArgumentException("ERROR");
		}

	}
	/**
	*Writes save game info to the passed PrintWriter. Info includes this Room's
	* title, whether the player has been here yet, and it's contents.
	*@author Carson Meadows
	*/
	void storeState(PrintWriter save){
		save.println(getTitle() +":");
		save.println("beenHere="+beenHere);
		if(contents.size()>0){
			String stuff = ("Contents: ");
			for(Item item : contents){
				stuff = stuff + item+",";
			}
			stuff = stuff.substring(0,stuff.length()-1);	
			save.println(stuff);
		}
		save.println("---");
	}
	/**
	*Hydrates this Room's "been here" status and contents from passed Scanner.
	*@author Carson Meadows
	*/
	void restoreState(Scanner restore, Dungeon d){
		String state = restore.nextLine();
		int x = state.length();
		state = state.substring(9,x);
		if(state.equals("true")){
			beenHere = true;
		}
		else if(state.equals("false")){
			beenHere = false;
		}
		String inventory = restore.nextLine();
		if(inventory.contains("Contents: ")){
			inventory = inventory.substring(10, inventory.length());
			String [] inventroryList = inventory.split(",");
			for(String name : inventroryList){
				Item item = d.getItem(name);
				this.add(item);
			}
			restore.nextLine();
		}
	}
	/**
	*Adds passed Item to this Room's contents.
	*@author Carson Meadows
	*/
	void add(Item item){
		contents.add(item);
	}
	/**
	*Returns ArrayList of this Room's contents.
	*@author Carson Meadows
	*/
	ArrayList<Item> getContents(){
		return contents;
	}
	/**
	*Removes passed Item from this Room's contents.
	*@author Carson Meadows
	*/
	void remove(Item item){
		contents.remove(item);
	}
	/**
	*Returns Item in this Room whose name is the parameter. 
	*@throws Item.NoItemException If no Item has name passed as a primary or secondary name.
	*@author Carson Meadows and Nathanael Woodhead
	*/
	Item getItemNamed(String name) throws Item.NoItemException{
		Item named = null;
		for(Item item : contents){
			if(item.goesBy(name)){
				named = item;
			}
		}
		if(named == null){
			throw new Item.NoItemException();
		}
		return named;
	}


}
