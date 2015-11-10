package edu.umw.cpsc240fall2015team7.zork;
/**
*Moves the player from Room to Room.
*@author Carson Meadows
*/
class MovementCommand extends Command{

	private String dir;

	/**
	*Constructs this MovementCommand.
	*@author Carson Meadows
	*/
	MovementCommand(String dir){
		this.dir = dir;
	}
	/**
	*Moves the player to the adjacent Room in the direction passed.
	*@author Carson Meadows
	*/
	String execute(){
		Room currentRoom = Player.instance().getCurrentRoom();
		try{
			Room newRoom = currentRoom.leaveBy(dir);
			Player.instance().setCurrentRoom(newRoom);
			return "";
		}
		catch(IllegalArgumentException e){
			return "There is no exit there.";
		}
	}

}
