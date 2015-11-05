package edu.umw.cpsc240fall2015team7.zork;
/**
  *Decreases the players health and checks to see if the player is dead.
  *@author Nathanael Woodhead
  */
class WoundEvent{
	private int damage;
	/**
	  *Creates a new WoundEvent object. 
	  *@param damage number of hitpoints to remove from player.
	  *@author Nathanael Woodhead
	  */
	WoundEvent(int damage){

	}
	/**
	  *Removes damage from the player. Subtracts the damage from the players current health
	  *regardless of current healths value.
	  *@author Nathanael Woodhead
	  */
	String execute(){
		return "";
	}
	/**
	  *Checks to see if the player is dead. The player is dead when health falls below 1.
	  *@author Nathanael Woodhead
	  */
	boolean checkDead(){
		return false;
	}

}
