package edu.umw.cpsc240fall2015team7.zork;
import java.util.*
/**
  *Moves the player to another Room randomly.
  *@author Nathanael Woodhead
  */
class TeleportEvent extends Event{
	private Random selector;
	/**
	  *Constructs this TeleportEvent object.
	  *@author Nathanael Woodhead
	  */
	TeleportEvent(){
		selector = new Random();
	}
	
	/**
	  *Moves the player randomly to another Room in the Dungeon. The player could be teleported into the 
	  *current Room.
	  *@author Nathanael Woodhead
	  */
	String execute(){
		//Get list of Rooms from Dungeon
		ArrayList <String> rooms = Dungeon.getRooms();

		//Choose random Room
		int select = selector.nextInt(rooms.size());
		String newRoom = room.get(select);
		Room swag = Dungeon.getRoom(newRoom);

		//Make that Room the CurrentRoom
		Player.setCurrentRoom(swag);
		return "Teleported to "+Player.getCurrentRoom().getTitle()+"!";
	}
}
