package edu.umw.cpsc240fall2015team7.zork;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
/**
 *The Player of the game.
 *@author Nathanael Woodhead
 */
class Player{
	class NoMeleeException extends Exception{}
	class NoGunException extends Exception{}
	private ArrayList<String> verbs = new ArrayList<String>();
	private Room currentRoom;
	private ArrayList<Item> inventory;
	private int attackLvl;
	private int attackXp;
	private int defenceLvl;
	private int defenceXp;
	private int health,food,score;
	private int maxHealth;
	private int carryWeight;
	public static Player theInstance;
	/**
	 *If a player does not already exist creates a new player and return it. Otherwise will return the Player object.
	 *@author Nathanael Woodhead
	 */
	public static Player instance(){
		if (theInstance == null){
			theInstance = new Player();
		}
		return theInstance;
	}
	/**
	 *A private constructor that creates a player object with the default Values.
	 *@author Nathanael Woodhead
	 */
	private Player(){
		this.inventory = new ArrayList<Item>();
		this.health = 100;
		this.food = 100;
		this.maxHealth=100;
		this.carryWeight=400;
		this.score = 0;
		this.attackLvl = 1;
		this.defenceLvl = 1;
	}
	/**
	  Returns this players maxHealth. 
	  @author Nathanael Woodhead
	  */
	public int getMaxHealth(){
		return maxHealth;
	}
	/**
	  Sets this players max health. This is the maximum amount that the health can go up to. 
	  @author Nathanael Woodhead
	  */
	public void setMaxHealth(int maxHealth){
		this.maxHealth=maxHealth;

	}
	/**
	  Returns the {@link carryWeight}
	  @author Nathanael Woodhead
	  */
	public int getCarryWeight(){
		return carryWeight;
	}
	/**
	  Method that sets this players {@link carryWeight}.
	  @param carryWeight The weight that the player can carry.
	  @author Nathanael Woodhead
	  */
	public void setCarryWeight(int carryWeight){
		this.carryWeight=carryWeight;
	}
	/**
	 *Adds food to the player.
	 *@author Nathanael Woodhead
	 */
	void eat(int food){
		this.food += food;
	}
	/**
	  Returns the first gun in this inventory.
	  @throws NoGunException When there are no guns in this inventory.
	  @author Nathanael Woodhead
	  */
	Gun getGun() throws NoGunException{
		for(Item i : inventory){
			Class clazz = Gun.class;
			if(clazz.isInstance(i)){
				return (Gun) i;
			}
		}
		throw new NoGunException();
	}
	/**
	  Returns an ArrayList of light items.
	  @author Nathanael Woodhead
	  */
	ArrayList<Light> getLights() throws Light.NoLightException{
		ArrayList<Light> lights=new ArrayList<Light>();
		for(Item i : inventory){
			if(i instanceof Light){
				lights.add((Light)i);
			}
		}
		if(lights.size()<1)
			throw new Light.NoLightException();
		else
			return lights;
	}
	/**
	 *Increases food and once food gets too high starts to remove health.
	 *@return A message relating to how hungry the player is. If the player is not hungry then this will return an empty String.
	 *@author Nathanael Woodhead
	 */
	String hunger(){
		food = food - 1;
		if (food <= 0){
			health = health - 1;
			food=0;
			checkDead();
			return "You are starving to death";
		}
		else if (food < 20){
			return "You are hungry.";
		}
		else{
			return "";
		}
	}
	/**
	 *Lowers the heath of the player. If this causes the player's health to fall below 1 then this will make a new die event 
	 *and execute it. This will cause the game to end and the player to loose the game.
	 *@author Nathanael Woodhead
	 */
	String takeWound(int damage,String message){
		if(damage<0)
			damage=damage*-1;
		health = health - damage;

		if(health <= 0){
			DieEvent d=new DieEvent();
			d.execute();
		}
		return "OWWWWWW! That hurt! "+message;
	}
	/**
	  Lowers the Players health. This takes into account the players defence level and should only be used in combat. This also calls the 
	  {@link addDefXp} to add xp to defence. This method can end the game by calling a {@link DieEvent}. This only happens when the player's
	  health falls below 0.
	  @param damage The amount of damage dealt by the wound.
	  @param message A message about who hit you.
	  @author Nathanael Woodhead
	  */
	String takeHit(int damage,String message){
		int def = defenceLvl;
		if(damage<0)
			damage=damage*-1;
		message += "\n" + addDefXp(damage);
		damage = damage/def;
		health = health - damage;

		if(health <= 0){
			DieEvent d=new DieEvent();
			d.execute();
		}
		return "OWWWWWW! That hurt! "+message;
	}
	/**
	 *Adds a item to this inventory. If the item is already in the inventory then it will quietly do nothing.
	 *@author Nathanael Woodhead
	 */
	void addToInventory(Item item){
		verbs.removeAll(item.getVerbs());
		verbs.addAll(item.getVerbs());
		inventory.add(item);
	}
	/**
	 *Changes the score. 
	 *@param score the amount this score should be changed by.
	 *@author Nathanael Woodhead	
	 */
	void changeScore(int score){
		this.score = this.score + score;
	}
	/**
	 *Returns the players score.
	 *@author Nathanael Woodhead
	 */
	int getScore(){
		return score;
	}
	/**
	 *Return the players health.
	 *@author Nathanael Woodhead
	 */
	int getHealth(){
		return health;
	}
	/**
	 *Sets health to passed int.
	 *@author Carson Meadows
	 */
	void setHealth(int health) {
		this.health = health;
	}

	/**
	 *Returns the players current room.
	 *@author Nathanael Woodhead
	 */
	Room getCurrentRoom(){
		return currentRoom;
	}

	/**
	 *Sets the players current room.
	 *@author Nathanael Woodhead
	 */
	void setCurrentRoom(Room room){
		this.currentRoom = room;
	}
	/**
	 *Returns the total weight of Items in the inventory of this.
	 *@author Carson Meadows
	 */
	int getLoad(){
		int load = 0;
		if(inventory.size()>0)
		{
			for(Item item : inventory){
				load += item.getWeight();
			}
		}
		return load;
	}
	/**
	 *Removes the passed Item from the inventory of this.
	 *@author Carson Meadows
	 */
	void removeFromInventory(Item item){
		inventory.remove(item);
	}
	/**
	  Takes a String an removes the first item in the inventory that goes by that name.
	  @author Nathanael Woodhead
	 */
	void removeItem(String name){
		Item itemNamed=null;
		for(Item item : inventory){
			if(item.goesBy(name)){
				itemNamed = item;
			}
		}
		inventory.remove(itemNamed);
	}
	/**
	 *Removes all items from the inventory and returns an ArrayList
	 * of the old inventory.
	 *@author Carson Meadows
	 */
	ArrayList<Item> removeAllFromInventory(){
		ArrayList<Item> oldInventory = new ArrayList<Item>();
		for(Item item : inventory){
			oldInventory.add(item);
		}
		inventory.clear();
		return oldInventory;
	}
	/**
	 *Returns the Item object in this inventory that goes by the name inputed.
	 *@param name An Item name to look for in the inventory
	 *@author Nathanael Woodhead
	 *@throws Item.NoItemException If there is no Item by that name in this' inventory.
	 */

	Item getItemInInventoryNamed(String name) throws Item.NoItemException{
		for(Item item : inventory){
			if(item.goesBy(name)){
				return item;
			}
		}
		throw new Item.NoItemException();
	}
	/**
	 *Returns an ArrayList of verbs this holds.
	 *@author Carson Meadows
	 */
	ArrayList<String> getVerbs(){
		return verbs;
	}
	/**
	 *Returns item in the current Room named the parameter.
	 *@author Carson Meadows
	 *@throws Item.NoItemException
	 */
	Item getItemInVicinityNamed(String name) throws Item.NoItemException{
		return currentRoom.getItemNamed(name);
	}
	/**
	 *Returns an ArrayList of item names in the inventory of this.
	 *@author Carson Meadows
	 */
	ArrayList<String> getInventoryNames(){
		ArrayList<String> names = new ArrayList<String>();
		for(Item x : inventory){
			names.add(x.getPrimaryName());
		}
		return names;
	}
	/**
	  Returns the ArrayList inventory of this
	  @author Jim Bramnick
	 */
	ArrayList<Item> getInventory(){
		return inventory;
	}
	/**
	 *Saves progress to a Printwriter.
	 *@author Carson Meadows
	 */
	public void store(PrintWriter save){
		save.println("Adventurer:");
		save.println("Current room: " + currentRoom.getTitle());
		if(inventory.size()>0){
			save.print("Inventory: ");
			String text = "";
			for(Item item : inventory){
				text = text + (item.storeState() + ",");
			}
			text = text.substring(0,text.length()-1);
			save.println(text);
		}
		save.println("Score: " + score);
		save.println("Health: " +health);
		save.println("Food: "+food);
		save.println("AttackXp: " +  attackXp);
		save.println("AttackLvl: " + attackLvl);
		save.println("DefenceXp: " + defenceXp);
		save.println("DefenceLvl: " + defenceLvl);
	}
	/**
	  Restores this Player to a saves point.
	  @author Nathanael Woodhead
	 */
	void restore(Scanner scan){
		String current="";
		try
		{
			current=scan.nextLine();
			current=current.substring(14,current.length());
			currentRoom=GameState.instance().getDungeon().getRoom(current);
			if(scan.hasNextLine()){
				current=scan.nextLine();
				if(!(current.contains("Inventory: ")))
					throw new Exception();
				String itemlist=current.split(":")[1].trim();
				String[] itemList=itemlist.split(",");
				for(int i=0;i<itemList.length;i++)
				{	
					String name=itemList[i];
					Item item=null;
					if(name.contains(";")&&name.contains("/"))
						item=Light.restore(name);
					else if(name.contains(";"))
						item=Gun.restore(name);
					else
						item = Item.restore(name);
					this.addToInventory(item);
				}

			}

		}
		catch(Exception e)
		{
			this.score=Integer.parseInt(current.split(" ")[1]);
			current=scan.nextLine();
			this.health=Integer.parseInt(current.split(" ")[1]);
			current=scan.nextLine();
			this.food=Integer.parseInt(current.split(" ")[1]);
			current=scan.nextLine();
			this.attackXp=Integer.parseInt(current.split(" ")[1]);
			current=scan.nextLine();
			this.attackLvl=Integer.parseInt(current.split(" ")[1]);
			current=scan.nextLine();
			this.defenceXp=Integer.parseInt(current.split(" ")[1]);
			current=scan.nextLine();
			this.defenceLvl=Integer.parseInt(current.split(" ")[1]);
		}
	}
	/**
	  Returns the amount of an item in this inventory. Takes the item named passed and counts the number of times it appears in this inventory. 
	  @param type The name of the item to be counted. 
	  @return An int of the number of times that item is in this inventory
	  @author Nathanael Woodhead
	 */
	int countAmmo(String type){
		int count = 0;
		for(Item item : inventory){
			if(item.goesBy(type)){
				count++;
			}
		}
		return count;
	}
	/**
	  Checks if the players health is below 0. If it creates a new {@link DieEvent} and executes it.
	  @author Nathanael Woodhead
	 */
	void checkDead(){
		if(health<0){
			Event die = new DieEvent();
			die.execute();
		}
	}
	/**
	  Returns the first melee weapon in this inventory. 
	  @throws NoMeleeException When there are no melee weapons in this inventory.
	  @author Nathanael Woodhead
	 */
	Melee getMelee() throws NoMeleeException{
		Class clazz = Melee.class;
		for(Item i : inventory){
			if(clazz.isInstance(i)){
				return (Melee) i;
			}
		}
		throw new NoMeleeException();
	}
	/**
	  Returns true when the player has a light item that is activated in inventory.
	  @author Nathanael Woodhead
	 */
	boolean hasLight(){
		for(Item i : inventory){
			if(i instanceof Light){
				Light light = (Light) i;
				if(light.getPower() == true){
					return true;
				}
			}
		}
		return false;
	}
	/**
	  Returns the level that the player has achieved. The levels are hard coded and are biased on the players score.
	  @author Nathanael Woodhead
	 */
	String getScoreMessage(){
		String text = "You are a ";
		if(score <100){
			return text +"Noob";
		}
		else if(score < 200){
			return text +"Freshman";
		}
		else if(score < 400){
			return text +"Sophmore";
		}
		else if (score < 800){
			return text +"Junior";
		}
		else if (score <1600){
			return 	text +"Senior";
		}
		else if(score <3200){
			return text +"Jedi";
		}
		else{
			return text +"Jedi Master";
		}
	}
	String addXp(int ammount){
		this.attackXp += ammount;
		return setLvl();
	}
	String setLvl(){
		int currentLevel = attackLvl;
		int newLevel = 0;
		ArrayList<Integer> levels = new ArrayList<>(Arrays.asList(0,100,200,400,800,1600,3200,6400,12800,25600,51200,102400,204800,409600,819200,1638400));
		for(int level : levels){
			if(attackXp>= level){
				newLevel++;
			}
		}
		if (newLevel > currentLevel){
			attackLvl = newLevel;
			ScoreEvent score = new ScoreEvent(attackLvl * 100);
			return "Congratulations you have reached attack level " +newLevel + "!\n" + score.execute();
		}
		else{ return "";}
	}
	int getLevel(){
		return attackLvl;
	}
	String addDefXp(int ammount){
		this.defenceXp += ammount;
		return setDefLvl();
	}
	String setDefLvl(){
		int currentLevel = defenceLvl;
		int newLevel = 0;
		ArrayList<Integer> levels = new ArrayList<Integer>();
		int x = 0;
		for(int i = 10; i>0; i--){
			levels.add(x);
			x += 50;
		}
		for(int level : levels){
			if(defenceXp>= level){
				newLevel ++;
			}
		}
		if(newLevel > currentLevel){
			defenceLvl = newLevel;
			ScoreEvent score = new ScoreEvent(defenceLvl * 50);
			return "Congratulations you have reached defence level " +  newLevel + "!\n"+ score.execute();
		}
		else {
			return"";
		}
	}
	int getDefLvl(){
		return defenceLvl;
	}
}
