package edu.umw.cpsc240fall2015team7.zork;
import java.util.Scanner;
import java.io.*;
/**
  *Superclass for all NPC's. An NPC is a non-player character.
  *@author Nathanael Woodhead
  */
class Npc{
	protected String primaryName;
	protected String secondaryName;
	protected int health, power, speed, score;
	protected String talkText;
	private boolean aggression;
	protected Room currentRoom;
	static class NoNpcException extends Exception{}
	public Npc(String primaryName, int health, int power, int speed,int score, String talkText, boolean aggression,Room currentRoom){
		this.primaryName = primaryName;
		this.secondaryName = "";
		this.currentRoom=currentRoom;
		this.health = health;
		this.power = power;
		this.speed = speed;
		this.score=score;
		this.talkText = talkText;
		this.aggression = aggression;
	}
	public Npc clone() {
			return new Npc(this.primaryName, this.health, this.power, this.speed,this.score, this.talkText, this.aggression,this.currentRoom);
	}
	public Npc(Scanner scan) throws NoNpcException{
		this.secondaryName="";
		this.currentRoom=null;
		String current=scan.nextLine();
		if(current.equals("==="))
			throw new NoNpcException();
		this.primaryName=current;
		current=scan.nextLine();
		this.health=Integer.parseInt(current);
		current=scan.nextLine();
		this.power=Integer.parseInt(current);
		current=scan.nextLine();
		this.speed=Integer.parseInt(current);
		current=scan.nextLine();
		this.score=Integer.parseInt(current);
		current=scan.nextLine();
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
		this.currentRoom.removeNpc(this);
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
	public void attackPlayer()
	{
		if(Player.instance().getCurrentRoom().getTitle().equals(this.currentRoom.getTitle())&&(aggression))
		{
			System.out.println(Player.instance().takeWound(this.power,this.primaryName + " hit you!!!"));
		}
			

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
	/**
	Returns power of this
	@author Jim Bramnick
	*/
	int getPower(){
		return power;
	}
	/**
	Returns currentRoom of this
	@author Jim Bramnick
	*/
	Room getCurrentRoom()
	{
		return currentRoom;
	}
	/**
	Sets health to health
	*/
	public void setHealth(int health)
	{
		this.health=health;
	}
	public void setSecondaryName(String name)
	{
		this.secondaryName=name;
	}
	/**
	Returns primaryName of this
	*/
	String getPrimaryName()
	{
		return primaryName;
	}
	/**
	Sets currentRoom of this to room
	@author Jim Bramnick
	*/
	public void setRoom(Room room)
	{
		currentRoom=room;	
	}
	/**
	Stores the this to a text file according to .sav file format
	*/
	public String storeState()
	{
		return this.primaryName+":"+health+"/"+aggression;
	}
	public static Npc restoreState(String save, Room r)
	{
		String value=save.split(":")[1];
		Npc npc=GameState.instance().getDungeon().getNpc(save.split(":")[0]);
		String[] values=value.split("/");
		npc.setHealth(Integer.parseInt(values[0]));
		npc.setAggressive(Boolean.valueOf(values[1]));
		npc.setRoom(r);
		return npc;

	}
}
