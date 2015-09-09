package edu.umw.nwoodhea.bork;
import java.lang.IllegalArgumentException;
class Command{

private String dir = "";

Command(String dir){
	this.dir = dir;
}

String execute(){
	Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
	try{
		Room newRoom = currentRoom.leaveBy(dir);
		return "";
}
	catch(IllegalArgumentException e){
		return "There is no exit there.";
}
}
}
