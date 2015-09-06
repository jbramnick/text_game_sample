package edu.umw.nwoodhea.bork;
import java.util.Hashtable;
public class Dungeon{

private String name;
private Hashtable <String,Room> map =  new Hashtable<String,Room>(5);
private Room entry;
public Dungeon (Room entry, String name){
	this.entry = entry;
	this.name = name;
	Room one = new Room("Nathan's Room");
	Room two = new Room("Heather's Room");
	Room three = new Room("Hallway");
	Room four = new Room("Kitchen");
	Room five = new Room("Living Room");


	map.put("1", one);
	map.put("2", two);
	map.put("3", three);
	map.put("4", four);
	map.put("5", five);

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
