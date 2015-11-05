package edu.umw.cpsc240fall2015team7.zork;
import java.util.ArrayList;
/**
  *Takes an item from the current room and moves it to the players inventory.
  *@author Nathanael Woodhead
  */
class TakeCommand extends Command{
	private String itemName;
	private String commandString;
	/**
	  *Creates a new TakeCommand object.
	  *@param commandString a string containing an item name.
	  *@author Nathanael Woodhead
	  */
	TakeCommand(String commandString){
		this.commandString = commandString;
	}
	/**
	  *Executes the TakeCommand by parsing the commandString to extract the item name and moving that item to the players inventory
	  *that item is then removed from the current room. If the item is too heavy this will return a String with a message saying that
	  *the item is too heavy to carry. If no item name is given it will return a string saying "Take what?". When the item is 
	  *taken this will return a String saying "Taken itemName".
	  *@author Nathanael Woodhead
	  */
	String execute(){
		String itemName = "";
		int weight = 0;
		try{
			itemName = commandString.substring(5,commandString.length());
		}
		catch(Exception e){
			return "\nTake what? \n";
		}
		this.itemName = itemName;
		Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
		try{
			if(commandString.contains("all")){
				ArrayList<Item> items = new ArrayList<Item>();
				for(Item item : currentRoom.getContents()){
					items.add(item);
				}
				for(Item item : items){
					 weight += item.getWeight();
				}
				if(items.size()== 0){
					return "There is nothing here to take." + "\n";
				}
				int load = GameState.instance().getLoad();
				if((weight + load) > 40){
					return "You cannot carry that much weight.";
				}
				String text = "Taken:" + "\n";
				for(Item item : items){
					currentRoom.remove(item);
					GameState.instance().addToInventory(item);
					text += item+ "\n"; 
				}
				return text;
			}
			Item item = currentRoom.getItemNamed(itemName);
			weight = item.getWeight();
			int load = GameState.instance().getLoad();
			if((weight + load) > 40){
				return "You cannot carry that much weight.";
			}
			currentRoom.remove(item);
			GameState.instance().addToInventory(item);
			return "Taken: " + itemName+"\n";
		}
		catch(Item.NoItemException e){
			return "There is no " + itemName + " here."+ "\n";
		}
	}
}	
