package edu.umw.nwoodhea.bork;
import java.io.*;

class GameState{


	public static GameState theInstance;
	private Room adeventurersCurrentRoom;
	private Dungeon map;

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

}
