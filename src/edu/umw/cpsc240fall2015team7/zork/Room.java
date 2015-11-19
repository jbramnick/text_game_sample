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
	private ArrayList<Weapon> weapons;

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
		weapons=new ArrayList<Weapon>();
		String content = scanner.nextLine();
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
		if(content.contains("Weapons: "))
		{
			if(initState==true)
			{

				content = content.substring(9,content.length());
				String[] list=content.split(",");
				for(String x:list)
				{
					this.add(d.getWeapon(x));
				}


			}
			content=scanner.nextLine();

		}
		if(content.contains("Snacks,"))
		{
			if(initState==true)
			{
				content=content.split(" ")[1];
				this.snacks=Integer.parseInt(content.split(",")[0]);
				this.medkits=Integer.parseInt(content.split(",")[1]);
				this.ammo=Integer.parseInt(content.split(",")[2]);

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
		if (GameState.instance().getVerbose()==true) {
			for(Exit exit: exits){
				text = text +"\n"+exit.describe();
			}
		}
		if(contents.size()>0){
			text = text + "\n";
			for(Item item: contents){
				text = text + "\n" + "There is a " + item + " here."; 
			}
			text = text.substring(0,text.length()-2);
		}
		if((snacks>0)||(medkits>0)||(ammo>0))
			text=text+"\nThere are "+snacks+" snacks, " + medkits+ " medkits, " + ammo + " Ammo in here.";
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
		if(weapons.size()>0){
			text = text + "\n";
			for(Weapon wep: weapons){
				text = text + "\n" + "There is a " + wep + " here."; 
			}
		}
		if((snacks>0)||(medkits>0)||(ammo>0))
			text=text+"\nThere are "+snacks+" snacks, " + medkits+ " medkits, " + ammo + " Ammo in here.";
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
	  Adds this snacks to {@link Player} snacks and sets this snacks to zero.
	  @author Jim Bramnick
	 */
	public void giveSnacks()
	{
		Player.instance().addSnacks(snacks);
		snacks=0;
	}
	/**
	  Adds this medkits to {@link Player} medkits and sets this medkits to zero.
	  @author Jim Bramnick
	 */
	public void giveMedkits()
	{
		Player.instance().addMedkits(medkits);
		medkits=0;
	}
	/**
	  Adds this ammo to {@link Player} ammo and sets this ammo to zero.
	  @author Jim Bramnick
	 */
	public void giveAmmo()
	{
		Player.instance().addAmmo(ammo);
		ammo=0;
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
			if(exit.getDir().equals(dir)){
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
		if(contents.size()>0){
			String stuff = ("Contents: ");
			for(Item item : contents){
				stuff = stuff + item+",";
			}
			stuff = stuff.substring(0,stuff.length()-1);	
			save.println(stuff);
		}
		if(weapons.size()>0)
		{
			String stuff = ("Weapons: ");
			for(Weapon wep : weapons){
				stuff = stuff + wep+",";
			}
			stuff = stuff.substring(0,stuff.length()-1);	
			save.println(stuff);

		}
		if((snacks>0)||(medkits>0)||(ammo>0))
			save.println("Snacks,Medkits,Ammo: "+snacks+","+medkits+","+ammo);
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
			inventory=restore.nextLine();
		}
		if(inventory.contains("Weapons: "))
		{
			inventory = inventory.substring(9, inventory.length());
			String [] inventroryList = inventory.split(",");
			for(String name : inventroryList){
				Weapon wep= d.getWeapon(name);
				this.add(wep);
			}
			inventory=restore.nextLine();
		}
		if(inventory.contains("Snacks,Medkits,Ammo:"))
		{
			inventory=inventory.split(" ")[1];
			this.snacks=Integer.parseInt(inventory.split(",")[0]);
			this.medkits=Integer.parseInt(inventory.split(",")[1]);
			this.ammo=Integer.parseInt(inventory.split(",")[2]);
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
	  Adds passed {@link Weapon} to weapons
	  @author Jim Bramnick
	 */
	void add(Weapon wep)
	{
		weapons.add(wep);
	}
	/**
	  Returns weapons
	  @author Jim Bramnick
	 */
	ArrayList<Weapon> getWeapons()
	{
		return weapons;
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
	}
/**
	 *Removes passed {@link Weapon} from this Room's contents. If the weapon is not in the room then it will quietly do nothing. 
	 *@author Jim Bramnick
	 */
	void remove(Weapon weap){
		weapons.remove(weap);
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
	Weapon getWeaponNamed(String name) throws Weapon.NoWeaponException
	{
		Weapon named=null;
		for(Weapon weapon: weapons)
		{
			if(weapon.goesBy(name))
			{
				named=weapon;
			}
		}
		if(named==null)
			throw new Weapon.NoWeaponException();
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
}
