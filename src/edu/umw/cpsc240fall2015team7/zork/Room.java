package edu.umw.cpsc240fall2015team7.zork;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.Collections;
/**
 *Room objects make up the Dungeon, can be entered and exited, and can hold Items.
 *@author Carson Meadows
 */
public class Room{
	static class NoRoomException extends Exception {}
	private String title;
	private String desc = "";
	private ArrayList<Exit> exits;
	private boolean lightdefault = true;
	private boolean light=true;
	private boolean beenHere = false;
	private ArrayList<Item> contents;
	private ArrayList<Npc> npcs  = new ArrayList<Npc>();
	private ArrayList<String> npcNames=new ArrayList<String>();
	private ArrayList<String> uniqueNpcNames=new ArrayList<String>();
	private ArrayList<String> itemNames=new ArrayList<String>();
	private ArrayList<String> uniqueItemNames=new ArrayList<String>();
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
		if(content.contains("Light: ")){
			content=content.split(" ")[1];
			this.light = Boolean.valueOf(content);
			this.lightdefault= this.light;
			content = scanner.nextLine();
		}
		if(content.contains("Occupants: ")){
			if(initState){
				content = content.substring(11,content.length());
				String[] list = content.split(",");
				for(String x : list){
					Npc npc=d.getNpc(x);
					npc.setRoom(this);
					addNpc(npc);
				}
			}
			content= scanner.nextLine();
		}
		if(content.contains("Contents: ")){
			if(initState == true){
				content = content.substring(10,content.length());
				String[] list = content.split(",");
				for(String x : list){
					this.add(d.getItem(x));
				}
			}
			content=scanner.nextLine();

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
	public void light() {
		light=true;
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
		String text ="";
		this.beenHere = beenHere;
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
		if (light==false) {
			if(Player.instance().hasLight()){
				light = true;
			}else{
				return "It's too dark to see in here.";
			}
		}
		if(beenHere == false){
			beenHere = true;
			text = text+": "+desc;
		}
		for(Exit exit: exits){
			text = text +"\n"+exit.describe();
		}
		if(contents.size()>0){
			text=text+"\n";
			for(String name:uniqueItemNames)
			{
				int time=Collections.frequency(itemNames,name);
				if (time==1)
					text=text+"\n"+"There is a " +name+ " in this room.";
				else
					text=text+"\n"+"There are "+ time +" " +name+"s in this room.";
			}
		}
		if(npcs.size()>0)
		{
			text=text+"\n";
			for(String name:uniqueNpcNames)
			{
				int time=Collections.frequency(npcNames,name);
				if (time==1)
					text=text+"\n"+"There is a " +name+ " in this room.";
				else
					text=text+"\n"+"There are "+ time +" " +name+"s in this room.";
			}

		}
		return text;
	}

	/**
	 *Returns an ArrayList of this Room's Exits.
	 *@author Carson Meadows
	 */
	public ArrayList<Exit> getExits () {
		return exits;
	}
	/**
	 *Returns this Room's title.
	 *@author Carson Meadows
	 */
	public String getTitle(){
		return title;
	}
	/**
	 *Adds passed exit to this Room's possible exits. If exit already exists then it will quietly do nothing. 
	 *@author Carson Meadows
	 */
	public void addExit(Exit exit){
		exits.add(exit);
	}
	/**
	 *Returns Room connected to the Exit associated with the passed direction. 
	 *If this Room does not have an exit in the passed direction then it will throw a Exit.NoExitException.
	 *@throws Exit.NoExitException When the direction passed does not match any exit in the room. 
	 *@author Carson Meadows and Nathanael Woodhead
	 */
	Room leaveBy(String dir) throws Exit.NoExitException{
		Exit out = null;
		boolean found = false;
		for(Exit exit : exits){
			if((exit.getDir().equals(dir))&&(!exit.isLocked())){
				out = exit;
				found = true;
			}}
		if(found == true){
			return out.getDest();
		}
		else{
			throw new Exit.NoExitException();
		}

	}
	/**
	 *Writes save game info to the passed PrintWriter. Info includes this Room's
	 * title, beenHere status, and it's contents.
	 *@author Carson Meadows
	 */
	void storeState(PrintWriter save){
		save.println(getTitle() +":");
		save.println("beenHere="+beenHere);
		save.println("light="+light);
		if(contents.size()>0){
			String stuff = ("Contents: ");
			for(Item item : contents){
				stuff = stuff + item.storeState()+",";
			}
			stuff = stuff.substring(0,stuff.length()-1);	
			save.println(stuff);
		}
		if(npcs.size()>0)
		{
			String stuff=("Occupants: ");
			for(Npc npc:npcs)
			{
				stuff=stuff+npc.storeState()+",";

			}
			stuff=stuff.substring(0,stuff.length()-1);
			save.println(stuff);
		}
		if(exits.size()>0)
		{
			String stuff="Exits: ";
			for(Exit e:exits)
			{
				stuff+=e.isLocked()+",";
			}
			stuff=stuff.substring(0,stuff.length()-1);
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
		if(inventory.contains("light="))
		{
			this.light=Boolean.valueOf(inventory.split("=")[1]);
			inventory=restore.nextLine();
		}
		if(inventory.contains("Contents: ")){
			inventory = inventory.substring(10, inventory.length());
			String [] inventroryList = inventory.split(",");
			for(String name : inventroryList){
				Item item=null;
				if(name.contains(":"))
					item=Gun.restore(name);
				else
					item = Item.restore(name);
				this.add(item);
			}
			inventory=restore.nextLine();
		}
		if(inventory.contains("Occupants: ")){
			inventory = inventory.substring(11, inventory.length());
			String [] inventroryList = inventory.split(",");
			for(String name : inventroryList){
				Npc item = Npc.restoreState(name,this);
				this.addNpc(item);
			}
			inventory=restore.nextLine();
		}
		if(inventory.contains("Exits: "))
		{
			inventory = inventory.split(" ")[1];
			String [] inventroryList = inventory.split(",");
			for(int i=0;i<exits.size();i++){
				exits.get(i).setLocked(Boolean.valueOf(inventroryList[i]));
			}
			inventory=restore.nextLine();
		}

	}
	/**
	 *Adds passed Item to this Room's contents.
	 *@author Carson Meadows
	 */
	void add(Item item){
		contents.add(item);
		itemNames.add(item.getPrimaryName());
		if(!uniqueItemNames.contains(item.getPrimaryName()))
			uniqueItemNames.add(item.getPrimaryName());

	}
	/**
	 *Returns ArrayList of this Room's contents.
	 *@author Carson Meadows
	 */
	ArrayList<Item> getContents(){
		return contents;
	}
	/**
	 *Removes passed Item from this Room's contents. If the item is not in the room then it will quietly do nothing. 
	 *@author Carson Meadows
	 */
	void remove(Item item){
		contents.remove(item);
		itemNames.remove(item.getPrimaryName());
		if(!itemNames.contains(item.getPrimaryName()))
			uniqueItemNames.remove(item.getPrimaryName());
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
	ArrayList<String> getVerbs(){
		ArrayList<String> verbs = new ArrayList<String>();
		for(Item i : contents){
			for(String verb : i.getVerbs()){
				verbs.add(verb);
			}
		}
		return verbs;
	}
	void addNpc(Npc npc){
		npcs.add(npc);
		npcNames.add(npc.getPrimaryName());
		if(!uniqueNpcNames.contains(npc.getPrimaryName()))
			uniqueNpcNames.add(npc.getPrimaryName());
	}
	void removeNpc(Npc npc){
		npcs.remove(npc);
		npcNames.remove(npc.getPrimaryName());
		if(!npcNames.contains(npc.getPrimaryName()))
			uniqueNpcNames.remove(npc.getPrimaryName());
	}
	void changeLight(boolean light){
		this.light = light;
	}
	void reset(){
		if(!this.title.equals(Player.instance().getCurrentRoom().getTitle()))
			this.light = this.lightdefault;
	}
	ArrayList<Npc> getInhabitants()
	{
		return npcs;

	}

}
