package edu.umw.cpsc240fall2015team7.zork;
/**
  *A Command for reloading a gun.
  *@author Nathanael Woodhead
  */
class ReloadCommand extends Command{
	private Gun weapon;
	private String commandString;
	/**
	  *Creates a new ReloadCommand object.
	  *@author Nathanael Woodhead
	  */
	ReloadCommand(String commandString){
		this.commandString = commandString;
	}
	/**
	  *Parses the commandString and carries out the necessary actions. 
	  *The commandString should be in the format "reload gun". This should parse the commandString and extract the gun's name. 
	  *If there is no gun by that name in the players inventory then this should return a message saying that you do not have that 
	  *gun. If the gun is in the player inventory this will reload it and return conformation message.
	  *@author Nathanael Woodhead
	  */
	String execute(){
		return "";	
	}
	}
	
