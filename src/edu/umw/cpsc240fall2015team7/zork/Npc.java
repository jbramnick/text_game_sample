package edu.umw.cpsc240fall2015team7.zork;
/**
  *Abstract superclass for all NPC's. An NPC is a non-player character.
  *@author Nathanael Woodhead
  */
abstract class Npc{
	private String primaryName;
	private int health, power;
	private boolean aggression;
	private Room currentRoom;
	/**
	  *Changes the aggression variable.
	  *@param aggression The desired value of aggression.
	  *@author Nathanael Woodhead
	  */
	void setAggressive(boolean aggression){
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
	}
	/**
	  *Returns the aggression value for this NPC. Non-Aggressive Npcs can become aggressive if they are attacked or through an event.
	  *@author Nathanael Woodhead
	  */
	boolean getAggression(){
		return false;
	}
	/**
	*Returns response from this NPC when interacted with by the player.
	*@author Carson Meadows
	*/
	String getTalkedAt(){
		return "";
	}
}
