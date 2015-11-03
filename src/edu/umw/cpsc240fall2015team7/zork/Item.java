package edu.umw.cpsc240fall2015team7.zork;;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Item{
	static class NoVerbException extends Exception {}
	static class NoItemException extends Exception {}

	private String primaryName;
	private ArrayList<String> secondaryNames;
	private int weight;
	private Hashtable <String, String>  messages;

	public Item(String primaryName, int weight){
		this.primaryName = primaryName;
		this.weight = weight;
	}

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
			String[] x = message.split(":");
			String[] other = x[0].split(",");
			for(String verb : other){
				messages.put(verb,x[1]);
			}
			message = scan.nextLine();
		}
	}
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
	ArrayList<String> getVerbs(){
		ArrayList<String> verbs = new ArrayList<String>();
		Enumeration<String> verb = messages.keys();	
		 while(verb.hasMoreElements()){
			verbs.add(verb.nextElement());
		}
		return verbs;
	}
	ArrayList<String> getSecondaryNames(){
		return secondaryNames;
	}
	int getWeight(){
		return weight;
	}	
}
