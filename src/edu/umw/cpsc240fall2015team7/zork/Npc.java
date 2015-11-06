package edu.umw.cpsc240fall2015team7.zork;
/**
  *Abstract superclass for all Npcs. A Npc is a non-player character.
  *@author Nathanael Woodhead
  */
abstract class Npc{
	private String primaryName;
	private int health, power;
	private boolean aggression;
	private Room currentRoom;
	/**
	  *Abstract constructor for all Npcs
	  *@param currentRoom The room that the Npc object should be added to.
	  *@param health The health for the Npc
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
	  *Kills the Npc and removes it from the game.
	  *@author Nathanael Woodhead
	  */
	public void die(){
	}
	/**
	  *Removes health from the Npc. If health falls below 1 this will call {@link die()}.
	  *@param damage the amount of damage to be removed from health.
	  *@author Nathanael Woodhead
	  */
	public void removeHealth(int damage){
	}
	/**
	  *Returns the aggression value for this.
	  *@author Nathanael Woodhead
	  */
	public boolean getAgression(){
		return false;
	}
