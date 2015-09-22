package edu.umw.nwoodhea.bork;
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
	String source = scanner.next();
	if(source.equals("===")){
		throw new NoExitException();
}
	this.dir = scanner.next();
	this.src = d.getRoom(source);
	this.dest = d.getRoom(scanner.next());
	src.addExit(this);
	
}
String describe(){
	String text = "You can go " + dir + " to " + dest.getTitle()+ ".";
	return text;
}
public String getDir(){
	return dir;
}

public Room getDest(){
	
	return dest;
}

public Room getSrc(){
	return src;
}
}

