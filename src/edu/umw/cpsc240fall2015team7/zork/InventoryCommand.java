package edu.umw.cpsc240fall2015team7.zork;
import java.util.ArrayList;
class InventoryCommand extends Command{
	InventoryCommand(){}
	String execute(){
		ArrayList<String> inventory  = GameState.instance().getInventoryNames();
		if(inventory.size()>0){
			String text = "You are carrying:"+ "\n";
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
