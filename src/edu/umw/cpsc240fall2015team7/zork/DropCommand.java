package edu.umw.cpsc240fall2015team7.zork;
import java.util.ArrayList;
/**
*A Command for dropping items.
*@author Nathanael Woodhead
*/
class DropCommand extends Command{
	private String itemName;
	private String commandString;
	
	/**
	*Constructs this DropCommand.
	*@throws NoItemException
	*@author Carson Meadows
	*/
	DropCommand(String commandString)throws Item.NoItemException{
		try{
			this.itemName = commandString.substring(5,commandString.length());
			this.commandString = commandString;
		}
		catch(Exception e){
			throw new Item.NoItemException();
		}
		this.itemName = itemName;
	}
	/**
	*Removes the Item specified within the commandString from the inventory of GameState or all the Items if "all" is specified. 
	*If Item specified in commandString is not in inventory of GameState, an error message is printed.
	*@author Nathanael Woodhead
	*@return A message to the user regarding their drop action
	*/
	String execute(){
		try{
			Room currentRoom = Player.instance().getCurrentRoom();
			if(commandString.contains("all")){
			 	ArrayList<Item> inventory = new ArrayList<Item>();
				for(Item thing : Player.instance().removeAllFromInventory()){
					inventory.add(thing);
				}	
				for(Item thing : inventory){
					currentRoom.add(thing);
				}
				PassTimeEvent e=new PassTimeEvent(null,"1");
				e.execute();
				return "Dropped everything" + "\n";
			}
			Item item = Player.instance().getItemInInventoryNamed(itemName);
			Player.instance().removeFromInventory(item);
			currentRoom.add(item);
			PassTimeEvent e=new PassTimeEvent(null,"1");
			e.execute();
			return "Dropped: " + itemName + "\n";
		}
		catch(Item.NoItemException e){
			return "You are not holding an " + itemName;
		}
	}
}

