package edu.umw.nwoodhea.bork;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.*;
public class Dungeon{

private String name;
private Hashtable <String,Room> map =  new Hashtable<String,Room>(5);
private Room entry;
public Dungeon (String filename){
	try{
		Scanner scanner = new Scanner(new File(filename));
		scanner.useDelimiter("/n");
		this.name = scanner.nextLine();
		String version = scanner.nextLine();
		if (!version.equals("Bork v2.0")){
			System.out.println("Not a valid bork file");
			System.exit(0);
}
		if(!scanner.nextLine().equals("===")){
			System.out.println("Not a valid bork file");
			System.exit(0);
}
		if(scanner.nextLine().equals("Rooms:"){
			this.add(new Room(scanner));
			while(scanner.nextLine().equals("---")){
				this.add(new Room(scanner));
}}
		else{
			System.out.println("Not a valid bork file");
			System.exit(0);
}

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
