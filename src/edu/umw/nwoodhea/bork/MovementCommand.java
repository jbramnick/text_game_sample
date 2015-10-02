package edu.umw.nwoodhea.bork;
class MovementCommand extends Command{

	private String dir;

	MovementCommand(String dir){
		this.dir = dir;
		}
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