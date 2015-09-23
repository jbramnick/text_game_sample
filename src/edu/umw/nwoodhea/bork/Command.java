package edu.umw.nwoodhea.bork;
import java.lang.IllegalArgumentException;
class Command{

	private String dir = "";

	Command(String dir){
		this.dir = dir;
	}

	String execute(){
		if(dir.equals("save")){
			GameState.instance().store();
			return "Game Saved!";
		}
		else{
			Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
			try{
			Room newRoom = currentRoom.leaveBy(dir);
			GameState.instance().setAdventurersCurrentRoom(newRoom);
			return "";
			}
			catch(IllegalArgumentException e){
			return "There is no exit there.";
			}
		}
	}
}
