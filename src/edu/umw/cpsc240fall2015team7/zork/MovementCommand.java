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
	This method also creates a new {@link PassTimeEvent} and calls execute() on it.
	*@author Carson Meadows
	*/
	String execute(){
		Room currentRoom = Player.instance().getCurrentRoom();
		currentRoom.reset();
		try{
			PassTimeEvent pass=new PassTimeEvent(null,"1");
			String text = pass.execute();
			Room newRoom = currentRoom.leaveBy(dir);
			Player.instance().setCurrentRoom(newRoom);
			return text;
		}
		catch(Exit.NoExitException e){
			return "You can not go that way.\n";
		}
	}

}
