package edu.umw.cpsc240fall2015team7.zork;
/**
*Command object that returns a description of the player's current Room.
*@author Carson Meadows
*/
class LookCommand extends Command{
	/**
	*Empty Constructor.
	*@author Carson Meadows
	*/
	LookCommand(){
	}
	/**
	*Returns a description of the player's current Room.
	*@author Carson Meadows
	*/
	public String execute(){
		Room currentRoom = Player.instance().getCurrentRoom();
		return " \n \n"+currentRoom.describe(false)+"\n\n";
	}
}	
