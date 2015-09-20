package edu.umw.nwoodhea.bork;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.FileReader;
public class Dungeon{

private String name;
private Hashtable <String,Room> map =  new Hashtable<String,Room>(5);
private Room entry;
public Dungeon (String filename) throws exception {
	FileReader fr = new FileReader(filename);
	System.out.println(filename);
	Scanner scanner = new Scanner(filename);
}

public Room getEntry(){
	return entry;
}

public String getName(){
	return name;
}

public void add(Room room){
	String title = room.getTitle();

	map.put(title, room);
}
public Room getRoom(String roomKey){
	return map.get(roomKey);
}
}
