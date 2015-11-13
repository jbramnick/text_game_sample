package edu.umw.cpsc240fall2015team7.zork;
import java.util.Hashtable;
import java.util.ArrayList;
/**
 *The Player of the game.
 *@author Nathanael Woodhead
 */
class Player{
	private ArrayList<String> verbs = new ArrayList<String>();
	private Room currentRoom;
	public class NoSnackException extends Exception{};
	private ArrayList<Item> inventory;
	private int ammo, snack, medkit,health,hunger,score;
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
		this.ammo = 0;
		this.snack = 0;
		this.medkit = 0;
		this.health = 100;
		this.hunger = 5;
		this.score = 0;
	}
	/**
	 *Removes one snack and refills hunger.
	 *@author Nathanael Woodhead
	 *@throws NoSnackException If snack is equal to 0;
	 */
	void eat() throws NoSnackException{
		if(snack == 0){
			throw new NoSnackException();
		}
		else{
			snack = snack - 1;
			hunger  = hunger+5;
		}
	}
	/**
	 *Increases hunger and once hunger gets too high starts to remove health.
	 *@return A message relating to how hungry the player is. If the player is not hungry then this will return an empty String.
	 *@author Nathanael Woodhead
	 */
	String hunger(){
		hunger = hunger - 1;
		if (hunger < 0){
			health = health - 5;
			return "You are starving to death";
		}
		else if (hunger < 2){
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
	void takeWound(int damage){
		health = health - damage;
		if(health == 0){
			new DieEvent();
		}
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
	int getScore(){
		return score;
	}
	int getHealth(){
		return health;
	}
	/**
	*Sets health to passed int.
	*@author Carson Meadows
	*/
	void setHealth(int StephenDavies) {
		this.health = StephenDavies;
	}
	void addMedkits (int add) {
		medkit += add;
	}
	void addAmmo (int add) {
		ammo += add;
	}
	void addSnacks (int add) {
		snack += add;
	}
	Room getCurrentRoom(){
		return currentRoom;
	}
	void setCurrentRoom(Room room){
		this.currentRoom = room;
	}
	int getLoad(){
		int load = 0;
		for(Item item : inventory){
			load += item.getWeight();
		}
		return load;
	}
	void removeFromInventory(Item item){
		inventory.remove(item);
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
	ArrayList<String> getVerbs(){
		return verbs;
	}
	Item getItemInVicinityNamed(String name) throws Item.NoItemException{
		return currentRoom.getItemNamed(name);
	}
	ArrayList<String> getInventoryNames(){
		ArrayList<String> names = new ArrayList<String>();
		for(Item x : inventory){
			names.add(x.getPrimaryName());
		}
		return names;
	}
}
