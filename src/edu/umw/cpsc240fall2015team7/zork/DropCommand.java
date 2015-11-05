package edu.umw.cpsc240fall2015team7.zork;
import java.util.ArrayList;
/**
*Used so the user can drop items.
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
		System.out.println(itemName);
	}
	/**
	*removes the item specified within the command string from the inventory of GameState or all the items if all is specified. If item specified in commandstring is not in inventory of GameState a message is printed.
	*@author Nathanael Woodhead
	*@return a string of a message to the user regarding their drop action
	*/
	String execute(){
		try{
			Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
			if(commandString.contains("all")){
			 	ArrayList<Item> inventory = new ArrayList<Item>();
				for(Item thing : GameState.instance().removeAllFromInventory()){
					inventory.add(thing);
				}	
				for(Item thing : inventory){
					currentRoom.add(thing);
				}
				return "Dropped everything" + "\n";
			}
			Item item = GameState.instance().getItemFromInventoryNamed(itemName);
			GameState.instance().removeFromInventory(item);
			currentRoom.add(item);

			return "Dropped: " + itemName + "\n";
		}
		catch(Item.NoItemException e){
			return "You are not holding an " + itemName;
		}
	}
}

