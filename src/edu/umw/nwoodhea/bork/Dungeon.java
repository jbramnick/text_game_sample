package edu.umw.nwoodhea.bork;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.*;
import java.util.Set;
public class Dungeon{
	static class IllegalDungeonFormatException extends Exception {}
	private String name;
	private Hashtable <String,Room> map =  new Hashtable<String,Room>(5);
	private Room entry;
	private String version;
	private String filename;
	public Dungeon (String filename) throws IllegalDungeonFormatException{
		this.filename = filename;
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

					Room enter = new Room(scanner);
					this.entry = enter;
					this.add(entry);
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
					catch(Exit.NoExitException e){
						y = false;
					}

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
	public String getFilename(){
		return filename;
	}
	void storeState(PrintWriter save){
		save.println("Dungeon file: " + filename);
		Set<String> keys = map.keySet();
		for(String key: keys){
			map.get(key).storeState(save);
			}
		
		}
}
