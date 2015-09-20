package edu.umw.nwoodhea.bork;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.*;
public class Dungeon{

private String name;
private Hashtable <String,Room> map =  new Hashtable<String,Room>(5);
private Room entry;
public Dungeon (String filename){
//	FileReader fr = new FileReader(filename);
//	System.out.println(filename);
	try{
		Scanner scanner = new Scanner(new File(filename));
		while(scanner.hasNextLine()){
			System.out.println(scanner.next());
}
}

	catch (FileNotFoundException e)
        {
            System.out.println("File not found");

        } 
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
