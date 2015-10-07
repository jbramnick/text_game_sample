package edu.umw.nwoodhea.bork;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Item{
	static class NoVerbException extends Exception {}
	static class NoItemException extends Exception {}

	private String primaryName;
	private int weight;
	private Hashtable <String, String>  messages;

	public Item(String primaryName, int weight){
		this.primaryName = primaryName;
		this.weight = weight;
	}

	public Item(Scanner scan) throws NoItemException, Dungeon.IllegalDungeonFormatException{
		this.primaryName = scan.nextLine();
		if(this.primaryName.equals("===")){
			throw new NoItemException();
		} 
		try{
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
			String[] x = message.split(":");
			messages.put(x[0],x[1]);		
			message = scan.nextLine();
		}
	}
	boolean goesBy(String name){
		if(primaryName.equals(name)){
			return true;
		}
		else{
			return false;
		}
	}
	public String getPrimaryName(){
		return primaryName;
	}
	public String getMessageForVerb(String verb) throws NoVerbException{
		if(!messages.containsKey(verb)){
			throw new NoVerbException();
		}
		String text = messages.get(verb);
		return text;
	}
	public String toString(){
		return primaryName;	
	}
	public ArrayList<String> getVerbs(){
		ArrayList<String> verbs = new ArrayList<String>();
		Enumeration<String> verb = messages.keys();	
		 while(verb.hasMoreElements()){
			verbs.add(verb.nextElement());
		}
		return verbs;
	}
}
