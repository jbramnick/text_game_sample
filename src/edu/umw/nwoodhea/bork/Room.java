package edu.umw.nwoodhea.bork;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Room{
	static class NoRoomException extends Exception {}
	private String title;
	private String desc;
	private ArrayList<Exit> exits;
	private boolean beenHere = false;
	private ArrayList<Item> contents;

	public Room(String title){
		this.title = title;
		exits = new ArrayList<Exit>();
		contents = new ArrayList<Item>();

	}
	public Room(Scanner scanner,Dungeon d, boolean initState) throws NoRoomException{
		this.title = scanner.nextLine();
		if(this.getTitle().equals("===")){
			throw new NoRoomException();
		}
		exits = new ArrayList<Exit>();
		contents = new ArrayList<Item>();
		String content = scanner.nextLine();
		if(content.contains("Contents: ")){
			if(initState == true){
				content.substring(10,content.length());
				String[] list = content.split(",");
				for(String x : list){
					this.add(d.getItem(x));
					}
				}
			}
		else{
			this.desc = content;
			}
		String part = scanner.nextLine();
		while(!part.equals("---")){
			this.desc = this.desc + "\n" + part;
			part = scanner.nextLine();
		}
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
	String describe(){
		String text = title;

		if(beenHere == false){
			beenHere = true;
			text = text+": "+desc;
		}
		for(Exit exit: exits){
			text = text +"\n"+exit.describe();
		}
		return text;
	}

	public String getTitle(){
		return title;
	}
	public void addExit(Exit exit){
		exits.add(exit);
	}

	Room leaveBy(String dir){
		Exit out = null;
		boolean found = false;
		for(Exit exit : exits){
			if(exit.getDir().equals(dir)){
				out = exit;
				found = true;
			}}
		if(found == true){
			return out.getDest();
		}
		else{
			throw new IllegalArgumentException("ERROR");
		}

	}
	void storeState(PrintWriter save){
		save.println(getTitle() +":");
		save.println("beenHere="+beenHere);
		save.println("---");
	}
	void restoreState(Scanner restore){
		String state = restore.nextLine();
		int x = state.length();
		state = state.substring(9,x);
		if(state.equals("true")){
			beenHere = true;
			}
		else if(state.equals("false")){
			beenHere = false;
			}
		restore.nextLine();
		}
	void add(Item item){
		contents.add(item);
		}
		
}
