package edu.umw.cpsc240fall2015team7.zork;
import java.util.Scanner;
/**
  *Superclass for all NPC's. An NPC is a non-player character.
  *@author Nathanael Woodhead
  */
class Npc implements Prototype{
	protected String primaryName;
	protected String secondaryName;
	protected int health, power, speed;
	protected String talkText;
	private boolean aggression;
	protected Room currentRoom;
	static class NoNpcException extends Exception{}
	public Npc(String primaryName, int health, int power, int speed, String talkText, boolean aggression){
		this.primaryName = primaryName;
		this.secondaryName = "";
		this.health = health;
		this.power = power;
		this.speed = speed;
		this.talkText = talkText;
		this.aggression = aggression;
	}
	public Prototype doClone() {
			return new Npc(this.primaryName, this.health, this.power, this.speed, this.talkText, this.aggression);
	}
	public Npc(Scanner scan, boolean initState) throws NoNpcException{
		String current=scan.nextLine();
		if(current.equals("==="))
			throw new NoNpcException();
		this.primaryName=current;
		current=scan.nextLine();
		if(initState)
			this.health=Integer.parseInt(current);
		current=scan.nextLine();
		this.power=Integer.parseInt(current);
		current=scan.nextLine();
		this.speed=Integer.parseInt(current);
		current=scan.nextLine();
		if(initState)
			this.aggression=Boolean.valueOf(current);
		current=scan.nextLine();
		this.talkText="";
		while(!(current.equals("---")))
		{
			this.talkText+=current+"\n";
			current=scan.nextLine();

		}
	}
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
	String die(){
		return "The "+primaryName+ " is dead.";
	}
	/**
	  *Removes health from this NPC. If health falls below 1, will call {@link die()}.
	  *@param damage The amount of damage to be removed from this NPC's health.
	  *@author Nathanael Woodhead
	  */
	String takeWound(int damage){
		String text = "\n";
		health -= damage;
		if(health <= 0){
			text += die() + "\n";
		}
		else{
			text += primaryName + " is hit for " + damage + " health.\n";
		}
		return text;
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
	/**
	  *Returns the speed.
	  *@author Nathanael Woodhead
	  */
	int getSpeed(){
		return speed;
	}
	/**
	  *Returns true when the health is above 0 otherwise returns false.
	  *@author Nathanael Woodhead
	  */
	boolean isAlive(){
		if(health > 0){
			return true;
		}
		return false;
	}
	int getPower(){
		return power;
	}
}
