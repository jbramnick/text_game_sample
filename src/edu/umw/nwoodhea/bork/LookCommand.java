package edu.umw.nwoodhea.bork;
class LookCommand extends Command{
	LookCommand(){
	}
	public String execute(){
		Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
		return " \n \n"+currentRoom.describe(false);
	}
}	
