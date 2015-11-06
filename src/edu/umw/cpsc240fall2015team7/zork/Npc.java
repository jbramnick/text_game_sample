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
	  *Constructor for all NPC's
	  *@param currentRoom The room that the NPC object should be added to.
	  *@param health The health for the NPC.
	  *@param primaryName The name of the character.
	  *@param power The amount of damage done with a single attack.
	  *@author Nathanael Woodhead
	  */
	public Npc(Room currentRoom, int health, int power, String primaryName, Room currentRoom){
	}
	/**
	  *Changes the aggression variable.
	  *@param aggression The desired value of aggression.
	  *@author Nathanael Woodhead
	  */
	public void setAggressive(boolean aggression){
	}
	/**
	  *Kills this NPC and removes it from the game.
	  *@author Nathanael Woodhead
	  */
	public void die(){
	}
	/**
	  *Removes health from the NPC. If health falls below 1 this will call {@link die()}.
	  *@param damage The amount of damage to be removed from this NPC's health.
	  *@author Nathanael Woodhead
	  */
	public void removeHealth(int damage){
	}
	/**
	  *Returns the aggression value for this NPC.
	  *@author Nathanael Woodhead
	  */
	public boolean getAggression(){
		return false;
	}
	/**
	*Returns response from this NPC when interacted with by the player.
	*@author Carson Meadows
	*/
	public String getTalkedAt () {
	}
}
