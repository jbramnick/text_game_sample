package edu.umw.cpsc240fall2015team7.zork;
import java.util.Scanner;
import java.io.*;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Random;
/**
 *Superclass for all NPC's. An NPC is a non-player character.
 *@author Nathanael Woodhead
 */
class Npc{
	protected String primaryName;
	protected ArrayList<String> secondaryNames;
	protected int health, power, speed, score;
	protected String talkText;
	protected boolean beenTalkedTo;
	private boolean aggression;
	private boolean canMove;
	protected Room currentRoom;
	protected Hashtable<String,ArrayList<Event>> choiceEvents;
	private ArrayList<Event> dieEvents;
	protected Hashtable<String,String> messages;
	static class NoNpcException extends Exception{}

	public Npc(String primaryName,ArrayList<String> secondaryNames, int health, int power, int speed,int score, String talkText, boolean aggression, boolean beenTalkedTo,Room currentRoom,Hashtable<String,ArrayList<Event>> choiceEvents,Hashtable<String,String> messages,ArrayList<Event> dieEvents, boolean canMove){
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
		this.dieEvents = dieEvents;
		this.secondaryNames = secondaryNames;
		this.canMove=canMove;
	}
	public Npc clone() {
		return new Npc(this.primaryName,this.secondaryNames, this.health, this.power, this.speed,this.score, this.talkText,this.aggression,this.beenTalkedTo,this.currentRoom,this.choiceEvents,this.messages,this.dieEvents,this.canMove);
	}
	public Npc(Scanner scan) throws NoNpcException,Dungeon.IllegalDungeonFormatException{
		this.currentRoom=null;
		choiceEvents=new Hashtable<String,ArrayList<Event>>();
		dieEvents = new ArrayList<Event>();
		messages=new Hashtable<String,String>();
		String current=scan.nextLine();
		if(current.equals("==="))
			throw new NoNpcException();
		secondaryNames = new ArrayList<String>();
		try {
			if (current.contains(",")) {
				String[] names = current.split(",");
				this.primaryName=names[0];
				for (String name : names) {
					if (name == names[0]) continue;
					this.secondaryNames.add(name);
				}
			} else {
				this.primaryName=current;
			}
		} catch (Exception e) {
			throw new Dungeon.IllegalDungeonFormatException();
		}
		current=scan.nextLine();
		this.health=Integer.parseInt(current);
		current=scan.nextLine();
		this.power=Integer.parseInt(current);
		current=scan.nextLine();
		this.speed=Integer.parseInt(current);
		current=scan.nextLine();
		this.score=Integer.parseInt(current);
		current=scan.nextLine();
		if(current.contains("Die: ")){
			current = current.substring(5,current.length());
			dieEvents = EventFactory.instance().parse(this,current);
			current=scan.nextLine();
		}
		this.aggression=Boolean.valueOf(current);
		current=scan.nextLine();
		this.canMove=Boolean.valueOf(current);
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
	 *Returns true if the passed String is applicable to this Npc
	 *@author Carson Meadows
	 */
	boolean goesBy(String name) {
		if (primaryName.equals(name)) {
			return true;
		} else {
			for(String nam : secondaryNames){
				if(nam.equals(name)){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 *Kills this NPC and removes it from the game.
	 *@author Nathanael Woodhead
	 */
	String die(){
		this.currentRoom.removeNpc(this);
		for(Event event : dieEvents){
			event.execute();
		}
		ScoreEvent e=new ScoreEvent(this.score);
		return "The "+primaryName+ " is dead."+"\n"+e.execute();
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
			System.out.println(Player.instance().takeHit(this.power,this.primaryName + " hit you!!!"));
		}


	}
	public void move()
	{
		Random x=new Random();
		boolean yesMove=x.nextInt(100)<50;
		if(!(Player.instance().getCurrentRoom().getTitle().equals(this.currentRoom.getTitle()))&&(yesMove)&&(canMove))
		{
			ArrayList<Exit> exits=this.currentRoom.getExits();
			ArrayList<Exit> openExits=new ArrayList<Exit>();
			for(Exit exit:exits)
			{
				if(!exit.isLocked())
				{
					openExits.add(exit);

				}

			}
			Random r=new Random();
			Exit exit=openExits.get(r.nextInt(openExits.size()));
			this.currentRoom.removeNpc(this);
			this.currentRoom=exit.getDest();
			exit.getDest().addNpc(this);
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
	void getTalkedAt(){
		if(beenTalkedTo)
		{
			System.out.println(this.primaryName+" does not want to talk anymore");

		}
		else
		{
			System.out.println(talkText);
			if(messages.size()>0)
			{

				Scanner scan=new Scanner(System.in);
				System.out.print("Say:");
				String choice=scan.nextLine();
				int tries=0;
				while(messages.get(choice)==null&&tries<5)
				{
					System.out.println(this.primaryName+" does not understand what you said.");
					System.out.print("Say:");
					choice=scan.nextLine();
					tries++;
				}
				if(messages.get(choice)!=null)
				{
					System.out.println(messages.get(choice));
					ArrayList<Event> cons=choiceEvents.get(choice);
					if(cons.size()>0)
					{
						for(Event e:cons)
							System.out.println(e.execute());
					}
				}
			}

		}

		this.beenTalkedTo=true;

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
		return this.primaryName+";"+health+"/"+aggression;
	}
	public static Npc restoreState(String save, Room r)
	{
		String value=save.split(";")[1];
		Npc npc=GameState.instance().getDungeon().getNpc(save.split(";")[0]);
		String[] values=value.split("/");
		npc.setHealth(Integer.parseInt(values[0]));
		npc.setAggressive(Boolean.valueOf(values[1]));
		npc.setRoom(r);
		return npc;

	}
}
