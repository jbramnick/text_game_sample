package edu.umw.cpsc240fall2015team7.zork;
/**
  *Changes to the players health and checks to see if the player is dead.
  *@author Nathanael Woodhead
  */
class WoundEvent extends Event{
	private int damage;
	/**
	  *Constructs this WoundEvent object. 
	  *@param damage Number of hitpoints to remove from player.
	  *@author Nathanael Woodhead
	  */
	WoundEvent(Item i, String damage){
		this.damage= Integer.parseInt(damage);
	}
	WoundEvent(String damage){
		this.damage= Integer.parseInt(damage);
	}
	/**
	  *Changes the player's health. Subtracts the damage from the player's current health
	  *regardless of current healths value.
	  *@author Nathanael Woodhead
	  */
	String execute(){
		Player.instance().takeWound(damage);
		return "";
	}
	/**
	  *Checks to see if the player is dead. The player is dead when health falls below one.
	  *@return True when the player is dead; false when the player is still alive.
	  *@author Nathanael Woodhead
	  */
	boolean checkDead(){
		if (Player.instance().getHealth()<1) {
			return true;
		} else {
			return false;
		}
	}

}
