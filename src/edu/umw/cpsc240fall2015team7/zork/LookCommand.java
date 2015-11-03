package edu.umw.cpsc240fall2015team7.zork;
class LookCommand extends Command{
	LookCommand(){
	}
	public String execute(){
		Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
		return " \n \n"+currentRoom.describe(false);
	}
}	
