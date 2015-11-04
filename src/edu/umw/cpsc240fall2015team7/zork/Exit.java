package edu.umw.cpsc240fall2015team7.zork;
import java.util.Scanner;
public class Exit{
static class NoExitException extends Exception {}

private String dir;
private Room dest;
private Room src;

public Exit(String dir, Room src, Room dest){
	this.dir = dir;
	this.dest = dest;
	this.src = src;
}
public Exit(Scanner scanner, Dungeon d) throws NoExitException{
	String source = scanner.nextLine();
	if(source.equals("===")){
		throw new NoExitException();
}
	this.dir = scanner.nextLine();
	this.src = d.getRoom(source);
	this.dest = d.getRoom(scanner.nextLine());
	this.src.addExit(this);
	scanner.nextLine();		
	
}
/**
*Returns description of the direction and destination of this Exit.
*@author Carson
*/
String describe(){
	String text = "You can go " + dir + " to " + dest.getTitle()+ ".";
	return text;
}
/**
*Returns the direction of this Exit.
*@author Carson
*/
public String getDir(){
	return dir;
}
/**
*Returns the Room this Exit leads to.
*@author Carson
*/
public Room getDest(){
	
	return dest;
}
/**
*Returns the Room this Exit came from.
*@author Carson
*/
public Room getSrc(){
	return src;
}
}

