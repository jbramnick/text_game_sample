package edu.umw.nwoodhea.bork;
import java.util.Hashtable;
public class Dungeon{

private String name;
private Hashtable <String,Room> map =  new Hashtable<String,Room>(5);
private Room entry;
public Dungeon (Room entry, String name){
	this.entry = entry;
	this.name = name;
	

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
