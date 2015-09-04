package edu.umw.nwoodhea.bork;
import java.util.Hashtable;
public class Dungeon{

private String name;
private Hashtable <String,Room> map =  new Hashtable<String,Room>(5);
public void Dungeon (Room entry, String name){
	Room entry = entry;
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
//Need to ask about this method.
public void add(Room room){
			
}
public Room getRoom(String roomKey){
	return map.get(roomKey);
}
}
