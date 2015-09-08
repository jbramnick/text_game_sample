package edu.umw.nwoodhea.bork;

class GameState{


public GameState theInstance;
private Room adeventurersCurrentRoom;
private Dungeon map;

public GameState instance(){
	if(theInstance == null){
		theInstance = new GameState();
}
	return theInstance;

}
private GameState(){
}

public void initalize(Dungeon dungeon){
	this.map = dungeon;
}
public Room getAdventurersCurrentRoom(){
	return adeventurersCurrentRoom;
}
public void setAdventurersCurrentRoom(Room room){
	adeventurersCurrentRoom = room;
}
public Dungeon getDungeon(){
	return map;
}
}
