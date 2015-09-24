package edu.umw.nwoodhea.bork;
import java.io.*;
import java.util.Scanner;
class GameState{


	public static GameState theInstance;
	private Room adeventurersCurrentRoom;
	private Dungeon map;
	public class IllegalSaveFormatException extends Exception {};

	public static GameState instance(){
		if(theInstance == null){
			theInstance = new GameState();
		}
		return theInstance;

	}
	private GameState(){
	}

	void initialize(Dungeon dungeon){
		this.map = dungeon;
	}
	Room getAdventurersCurrentRoom(){
		return adeventurersCurrentRoom;
	}
	void setAdventurersCurrentRoom(Room room){
		adeventurersCurrentRoom = room;
	}
	Dungeon getDungeon(){
		return map;
	}
	void store(){
		File s = new File("Save.sav");
		PrintWriter save;
		try{
			save = new PrintWriter(s);
			save.println("Bork v2.0 save data");
			map.storeState(save);
			save.println("Current room: " + adeventurersCurrentRoom.getTitle());
			save.println("===");
			save.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not found.");
		}
	}	
	void restore(String filename) throws Exception{
		try{
			File f = new File(filename);
			Scanner restore = new Scanner(f);
			restore.useDelimiter(":");
			if(restore.nextLine().equals("Bork v2.0 save data")){
				if(restore.next().equals("Dungeon file:")){
					try{
						this.map = new Dungeon(restore.nextLine()); 
						map.restoreState(restore);
						}
					catch(Exception e){
						throw new Dungeon.IllegalDungeonFormatException();
						} 
				}
				else{
					throw new IllegalSaveFormatException();
					}
				}
			else{
				throw new IllegalSaveFormatException();
				}
			}
		catch(IllegalSaveFormatException e){
			System.out.println("Illegal .sav file");
			}
		catch(FileNotFoundException e){
			System.out.println("File not found.");
			}
		}
}
