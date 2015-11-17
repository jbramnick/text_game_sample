package edu.umw.cpsc240fall2015team7.zork;
/**
  *Abstract superclass for all NPC's. An NPC is a non-player character.
  *@author Nathanael Woodhead
  */
abstract class Npc{
	protected String primaryName;
	protected int health, power, speed;
	private boolean aggression;
	protected Room currentRoom;
	/**
	  *Changes the aggression variable.
	  *@param aggression The desired value of aggression.
	  *@author Nathanael Woodhead
	  */
	void setAggressive(boolean aggression){
		this.aggression = aggression;
	}
	/**
	  *Kills this NPC and removes it from the game.
	  *@author Nathanael Woodhead
	  */
	void die(){
	}
	/**
	  *Removes health from this NPC. If health falls below 1, will call {@link die()}.
	  *@param damage The amount of damage to be removed from this NPC's health.
	  *@author Nathanael Woodhead
	  */
	void removeHealth(int damage){
		health -= damage;
	}
	/**
	  *Returns the aggression value for this NPC. Non-Aggressive Npcs can become aggressive if they are attacked or through an event.
	  *@author Nathanael Woodhead
	  */
	boolean getAggression(){
		return aggression;
	}
	/**
	*Returns response from this NPC when interacted with by the player.
	*@author Carson Meadows
	*/
	String getTalkedAt(){
		return "";
	}
}
