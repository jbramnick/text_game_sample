package edu.umw.cpsc240fall2015team7.zork;
import java.util.ArrayList;
/**
 *Returns a description of the player's inventory.
 *@author Carson Meadows
 */
class InventoryCommand extends Command{

	/**
	 *Constructs this InventoryCommand.
	 *@author Carson Meadows
	 */
	InventoryCommand(){
	}
	/**
	 *Returns description of player's inventory. If the inventory is empty, will return a message saying so. 
	 *@author Carson Meadows
	 */
	String execute(){
		String text="";
		ArrayList<String> inventory  = Player.instance().getInventoryNames();
		if((inventory.size()>0)){
			text = "You are carrying:"+ "\n";
			for(String item : inventory){
				text = text + item + "\n";
			}
			return text;

		}

		else{
			return "You are empty handed.";
		}
	}
}	
