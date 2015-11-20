package edu.umw.cpsc240fall2015team7.zork;
import java.util.*;
/**
  *The engine that controls the creation of opponents by Npcs or by events. The first opponent listed in the .zork file will be the
  *opponent that will be spawned by the spawner. This is a Singleton class. 
  *@author Nathanael Woodhead
  */
class Spawner{
	public static Spawner theInstance;
	/**
	  *If a Spawner object does not already exist, creates a new Spawner and return it. Otherwise will return the Spawner object.
	  *@author Nathanael Woodhead
	  */
	public static Spawner instance(){
		if (theInstance == null) {
			theInstance = new Spawner();
		}
		return theInstance;
	}
	/**
	  *A private constructor that creates this Spawner object with the default values.
	  *@author Nathanael Woodhead
	  */
	private Spawner(){
	}
	/**
	  *Makes a new Opponent and places it in a random Room.
	  *If this spawns an Opponent in a Room with an Opponent already present, deletes the spawned Opponent.
	  *@author Nathanael Woodhead, Caron Meadows and Jim Bramnick
	  */
	void spawn(){
		//Select Random Room from Dungeon
		Random selector = new Random();
		ArrayList <String> rooms = GameState.instance().getDungeon().getKeys();
		int select = selector.nextInt(rooms.size());
		String RandRoomTitle = rooms.get(select);
		Room room = GameState.instance().getDungeon().getRoom(RandRoomTitle);
		Npc original = GameState.instance().getDungeon().getSpawnedNpc();
		Npc clone = original.clone();
		room.addNpc(clone);
	}
}

