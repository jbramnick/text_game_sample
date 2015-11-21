package edu.umw.cpsc240fall2015team7.zork;
import java.util.Scanner;
/**
 *Connects Rooms in the Dungeon to each other. Each exit is only one way.
 *@author Carson Meadows
 */
public class Exit{
	static class NoExitException extends Exception {}

	private boolean locked;
	private String dir;
	private Room dest;
	private Room src;

	/**
	 *Constructs this Exit with the parameters given.
	 *@author Carson Meadows
	 */
	public Exit(String dir, Room src, Room dest){
		this.dir = dir;
		this.dest = dest;
		this.src = src;
	}
	/**
	 *Hydrates this Exit from the passed Scanner object. 
	 *@throws NoExitException When the scanner reaches the end of the exits.
	 *@author Carson Meadows
	 */
	public Exit(Scanner scanner, Dungeon d) throws NoExitException{
		String source = scanner.nextLine();
		if(source.equals("===")){
			throw new NoExitException();
		}
		this.dir = scanner.nextLine();
		this.src = d.getRoom(source);
		this.dest = d.getRoom(scanner.nextLine());
		String next=scanner.nextLine();
		if(next.equals("true")||next.equals("false")){
			this.locked = Boolean.valueOf(next);
			scanner.nextLine();
		}
		else{
			this.locked = false;
		}
		this.src.addExit(this);

	}
	/**
	 *Returns a description of the direction and destination of this Exit.
	 *@author Carson Meadows
	 */
	String describe(){
		String text = "You can go " + dir + " to " + dest.getTitle()+ ".";
		return text;
	}
	/**
	 *Returns the direction of this Exit.
	 *@author Carson Meadows
	 */
	public String getDir(){
		return dir;
	}
	/**
	 *Returns the Room this Exit leads to.
	 *@author Carson Meadows
	 */
	public Room getDest(){

		return dest;
	}
	public boolean unlock(){
		if(locked == true){
			locked = false;
			return true;
		}
		return false;
	}
	/**
	 *Returns the Room this Exit came from.
	 *@author Carson Meadows
	 */
	public Room getSrc(){
		return src;
	}
}

