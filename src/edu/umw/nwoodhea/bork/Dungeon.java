package edu.umw.nwoodhea.bork;
import java.util.Hashtable;
public class Dungeon{

private String name;

public void Dungeon (Room entry, String name){
	this.name = name;
	Room one = new Room("Nathan's Room");
	Room two = new Room("Heather's Room");
	Room three = new Room("Hallway");
	Room four = new Room("Kitchen");
	Room five = new Room("Living Room");


	Hashtable map = new Hashtable(5);
	map.put("1", one);
	map.put("2", two);
	map.put("3", three);
	map.put("4", four);
	map.put("5", five);

}

public Room getEntry(){
	return null;
}
public String getName(){
	return null;
}
//Need to ask about this method.
public void add(Room room){
			
}
public Room getRoom(String roomTitle){
	return map.get(roomTitle);
}
}
