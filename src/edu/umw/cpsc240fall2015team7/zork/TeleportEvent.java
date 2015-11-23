package edu.umw.cpsc240fall2015team7.zork;
import java.util.*;
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
	TeleportEvent(Object i){
		selector = new Random();
	}
	/**
	*Constructs this TeleportEvent without an Item passed.
	*@author Carson Meadows
	*/
	TeleportEvent(){
		selector = new Random();
	}
	
	/**
	  *Moves the player randomly to another Room in the Dungeon. 
	  *The player could be teleported into the current Room.
	  *@author Nathanael Woodhead
	  */
	String execute(){
		//Get list of Rooms from Dungeon
		ArrayList <String> rooms = GameState.instance().getDungeon().getKeys();

		//Choose random Room
		int select = selector.nextInt(rooms.size());
		String newRoomTitle = rooms.get(select);
		Room newRoom = GameState.instance().getDungeon().getRoom(newRoomTitle);

		//Make that Room the CurrentRoom
		Player.instance().setCurrentRoom(newRoom);
		return"\n" + "Teleported to "+newRoomTitle+"!\n";
	}
}
