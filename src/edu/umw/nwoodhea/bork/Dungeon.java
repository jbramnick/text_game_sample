package edu.umw.nwoodhea.bork;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.*;
public class Dungeon{
static class IllegalDungeonFormatException extends Exception {}
private String name;
private Hashtable <String,Room> map =  new Hashtable<String,Room>(5);
private Room entry;
private String version;
public Dungeon (String filename) throws IllegalDungeonFormatException{
	try{
		Scanner scanner = new Scanner(new File(filename));
		this.name = scanner.nextLine();
		System.out.println(this.name);
		version = scanner.nextLine();
		System.out.println(this.version);
		if (!version.equals("Bork v2.0")){
			throw new IllegalDungeonFormatException();
}

		if(!scanner.nextLine().equals("===")){

			throw new IllegalDungeonFormatException();
}
		if(scanner.nextLine().equals("Rooms:")){
			boolean x = true;
			try{
				this.add(new Room(scanner));
				while(x){
					try{
					this.add(new Room(scanner));
}
				catch(Room.NoRoomException e){
					x = false;
}
}
}
			catch(Room.NoRoomException e){
				x = false;
}
}
		else{
			throw new IllegalDungeonFormatException();
}
		if(scanner.nextLine().equals("Exits:")){
		boolean y = true;
		while(y){
				try{
					new Exit(scanner,this);

}
				catch(Exit.NoExitException e){}

}
}
		else{
			throw new IllegalDungeonFormatException();
}
}
	catch(FileNotFoundException e){
        
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
