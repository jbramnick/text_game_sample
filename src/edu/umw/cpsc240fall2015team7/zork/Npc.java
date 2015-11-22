package edu.umw.cpsc240fall2015team7.zork;
import java.util.Scanner;
import java.io.*;
import java.util.Hashtable;
import java.util.ArrayList;
/**
  *Superclass for all NPC's. An NPC is a non-player character.
  *@author Nathanael Woodhead
  */
class Npc{
	protected String primaryName;
	protected int health, power, speed, score;
	protected String talkText;
	protected boolean beenTalkedTo;
	private boolean aggression;
	protected Room currentRoom;
	protected Hashtable<String,ArrayList<Event>> choiceEvents;
	protected Hashtable<String,String> messages;
	static class NoNpcException extends Exception{}
	public Npc(String primaryName, int health, int power, int speed,int score, String talkText, boolean aggression, boolean beenTalkedTo,Room currentRoom,Hashtable<String,ArrayList<Event>> choiceEvents,Hashtable<String,String> messages){
		this.primaryName = primaryName;
		this.currentRoom=currentRoom;
		this.health = health;
		this.power = power;
		this.speed = speed;
		this.score=score;
		this.talkText = talkText;
		this.aggression = aggression;
		this.choiceEvents=choiceEvents;
		this.messages=messages;
	}
	public Npc clone() {
		return new Npc(this.primaryName,this.health, this.power, this.speed,this.score, this.talkText,this.aggression,this.beenTalkedTo,this.currentRoom,this.choiceEvents,this.messages);
	}
	public Npc(Scanner scan) throws NoNpcException,Dungeon.IllegalDungeonFormatException{
		this.currentRoom=null;
		choiceEvents=new Hashtable<String,ArrayList<Event>>();
		messages=new Hashtable<String,String>();
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
		while(!(current.equals("~~~")))
		{
			this.talkText+=current+"\n";
			current=scan.nextLine();
		}
		current=scan.nextLine();
		while(!(current.equals("---")))
		{
			ArrayList<Event> consequences= null;
			String[] x = current.split(":");
			if(x[0].contains("[")){
				int start = x[0].indexOf("[")+1;
				int end = x[0].indexOf("]");
				String part = x[0].substring(start,end);
				x[0] = x[0].substring(0,start-1);
				try{
					consequences = EventFactory.instance().parse(this,part);
				}catch(Exception e){
					throw new Dungeon.IllegalDungeonFormatException();
				}
			}
			String[] other = x[0].split(",");
			for(String verb : other){
				messages.put(verb,x[1]);
				if(consequences != null){
					choiceEvents.put(verb,consequences);
				}
			}
			current = scan.nextLine();

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
