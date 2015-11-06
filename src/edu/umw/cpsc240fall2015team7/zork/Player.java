package edu.umw.cpsc240fall2015team7.zork;
/**
  *The Player of the game.
  *@author Nathanael Woodhead
  */
class Player{
	private int ammo, snacks, medkit;
	public static Player theInstance;
	/**
	  *If a player does not already exist creates a new player and return it. Otherwise will return the Player object.
	  *@author Nathanael Woodhead
	  */
	public static Player instance(){
	}
	/**
	  *A private constructor that creates a player object with the default Values.
	  *@author Nathanael Woodhead
	  */
	private Player(){
	}
}

