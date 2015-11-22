package edu.umw.cpsc240fall2015team7.zork;
import java.util.ArrayList;
import java.util.Collections;
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
		ArrayList<String> uniqueItemNames=new ArrayList<String>();
		if((inventory.size()>0)){
			text = "You are carrying:"+ "\n";
			for(String item : inventory){
				if(!uniqueItemNames.contains(item))
					uniqueItemNames.add(item);
			}
			for(String item:uniqueItemNames)
			{
				int times=Collections.frequency(inventory,item);
				if(times>1)
					text+=item+" x"+times+"\n";
				else
					text+=item+"\n";
				

			}
			return text;

		}

		else{
			return "You are empty handed.";
		}
	}
}	
