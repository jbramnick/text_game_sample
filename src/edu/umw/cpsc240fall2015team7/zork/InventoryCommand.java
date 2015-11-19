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
		ArrayList<String> weaponInventory=Player.instance().getWeaponNames();
		if((inventory.size()>0)||(weaponInventory.size()>0)||(Player.instance().getMedkits()>0)||(Player.instance().getSnacks()>0)||(Player.instance().getAmmo()>0)){
			text = "You are carrying:"+ "\n";
			for(String item : inventory){
				text = text + item + "\n";
			}
			if(weaponInventory.size()>0)
			{
				text+="Weapons:\n";
				for(String weapon:weaponInventory)
				{
					text+=weapon+"\n";
				}

			}
			if(Player.instance().getMedkits()>0)
			{
				text+="Medkits:"+Player.instance().getMedkits()+"\n";

			}
			if(Player.instance().getSnacks()>0)
			{
				text+="Snacks:"+Player.instance().getSnacks()+"\n";
			}
			if(Player.instance().getAmmo()>0)
			{
				text+="Ammo:"+Player.instance().getAmmo()+"\n";
			}
			return text;

		}

		else{
			return "You are empty handed.";
		}
	}
}	
