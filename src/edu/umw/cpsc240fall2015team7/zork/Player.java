package edu.umw.cpsc240fall2015team7.zork;
import java.util.Hashtable;
/**
 *The Player of the game.
 *@author Nathanael Woodhead
 */
class Player{
	public class NoSnackException extends Exception{};
	private Hashtable<String, Item> inventory;
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
	 *@throws NoSnackException If snacks is equal to 0;
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
	  *Lowers the heath of the player. If this causes the player's heath to fall below 1 then this will make a new die event and execute it.
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
	void addItemtoInventory(Item item){
		String name = item.getPrimaryName();
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

}
