/**
*Moves the player from Room to Room.
*@author Carson Meadows
*/
package edu.umw.cpsc240fall2015team7.zork;
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
	*Moves the player to the adjacent Room in the direction of this.
	*@author Carson Meadows
	*/
	String execute(){
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
