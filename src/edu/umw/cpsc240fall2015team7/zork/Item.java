package edu.umw.cpsc240fall2015team7.zork;
import java.lang.reflect.Constructor;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
/**
*Represents an object to be found and used in the Dungeon.
*@author Carson Meadows
*/
public class Item{
	static class NoVerbException extends Exception {}
	static class NoItemException extends Exception {}

	protected Hashtable<String, ArrayList<Event> > actions = new Hashtable<String, ArrayList<Event> >();
	protected String primaryName;
	protected ArrayList<String> secondaryNames;
	protected int weight;
	protected Hashtable <String, String>  messages;

	/**
	*Constructs this Item with parameters.
	*@author Carson Meadows
	*/
	public Item(String primaryName, int weight){
		this.primaryName = primaryName;
		this.weight = weight;
	}
	public Item(String primaryName,Hashtable<String,ArrayList<Event>> actions,ArrayList<String> secondaryNames,int weight,Hashtable<String,String> messages)
	{
		this.primaryName=primaryName;
		this.actions=actions;
		this.secondaryNames=secondaryNames;
		this.weight=weight;
		this.messages=messages;

	}
	public Item()
	{}
	/**
	*Hydrates this Item by reading passed Scanner object. Reads in primary and any
	*secondary names, weight, and messages used for ItemSpecificCommands. Any consequences will be stored where they can be retrieve at
	*when the verb is called.
	*@throws Dungeon.IllegalDungeonFormatException When the actions do not match any existing action.
	*@author Carson Meadows
	*/
	public Item(Scanner scan) throws NoItemException, Dungeon.IllegalDungeonFormatException{
		String chunk = scan.nextLine();
		if(chunk.equals("===")){
			throw new NoItemException();
		}
	       	secondaryNames = new ArrayList<String>();	
		try{
			if(chunk.contains(",")){
				String[] names = chunk.split(",");
				this.primaryName = names[0];
				for(String name : names){
					if(name == names[0]) continue;
					this.secondaryNames.add(name);
				}
			}
			else{
				this.primaryName = chunk;
			}
			if(scan.hasNextInt()){
				int mass = scan.nextInt();
				this.weight = mass;
				scan.nextLine();
			}		
			else{
				throw new Dungeon.IllegalDungeonFormatException();
			}
		}
		catch(Exception e){
			throw new Dungeon.IllegalDungeonFormatException();
		} 
		messages = new Hashtable <String, String>();
		String message = scan.nextLine();
		while(!message.equals("---")){
			Constructor constructor;
			ArrayList<Event> consequences= null;
			String[] x = message.split(":");
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
					actions.put(verb,consequences);
				}
			}
			message = scan.nextLine();
		}
	}
	/**
	*Returns true if this Item's primary or secondary names equal the 
	*String passed. Returns false if 
	*the string does not match any names.
	*@author Carson Meadows
	*/
	boolean goesBy(String name){
		if(primaryName.equals(name)){
			return true;
		}
		else if(secondaryNames.contains(name)){
			return true;
		}	
		else{
			return false;
		}
	}
	public void decay(){}
	/**
	*Returns this Item's primary name.
	*@author Carson Meadows
	*/
	public String getPrimaryName(){
		return primaryName;
	}
	/**
	*Returns message associated with passed String and executes all the events associated with the message. 
	*@throws NoVerbException If this Item does not contain the passed String. 
	*@author Carson Meadows
	*/
	public String getMessageForVerb(String verb) throws NoVerbException{
		if(!messages.containsKey(verb)){
			throw new NoVerbException();
		}
		String text = messages.get(verb);
		return text;
	}
	public String executeEventsFor(String verb) 
	{
		String text="";
		if(actions.containsKey(verb)){
			for(Event event : actions.get(verb)){
				text = text + event.execute();
			}
		}
		return text;


	}
	/**
	*Returns this Item's primary name.
	*@author Carson Meadows
	*/
	public String toString(){
		return primaryName;	
	}
	/**
	*Returns an ArrayList of verbs used for this Item's 
	*ItemSpecificCommands.
	*@author Carson Meadows
	*/
	ArrayList<String> getVerbs(){
		ArrayList<String> verbs = new ArrayList<String>();
		Enumeration<String> verb = messages.keys();	
		 while(verb.hasMoreElements()){
			verbs.add(verb.nextElement());
		}
		return verbs;
	}
	/**
	*Returns an ArrayList of this Item's secondary names.
	*@author Carson Meadows
	*/
	ArrayList<String> getSecondaryNames(){
		return secondaryNames;
	}
	/**
	*Returns the weight of this Item.
	*@author Carson Meadows
	*/
	int getWeight(){
		return weight;
	}
	/**
  	*Returns an ArrayList of events for given verb.
	*If there are no event will return null.
	*@author Nathanael Woodhead
	*/	
	ArrayList<Event> getEventsForVerb(String verb){
		return null;
	}
	/**
	  *Adds an Event object to a verb. If the verb does not already exist then it will create the verb and add the event.
	  *@author Nathanael Woodhead
	  */
	void addEventToVerb(String verb, Event event){
	}
	public Item clone(){
		return new Item(this.primaryName,this.actions,this.secondaryNames,this.weight,this.messages);

	}
	/**
	Stores the state of the item 
	*/
	String storeState(){
		return this.primaryName;

	}
	public static Item restore(String save)
	{
		return GameState.instance().getDungeon().getItem(save);
	}
	/**
	  Returns an empty String. This is used to find what type of item this is. This method should be overwritten in all subclasses.
	  @author Nathanael Woodhead
	  */
	String type(){
		return "";
	}
}

