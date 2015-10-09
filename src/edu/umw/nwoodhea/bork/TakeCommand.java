package edu.umw.nwoodhea.bork;
import java.util.ArrayList;
class TakeCommand extends Command{
	private String itemName;
	private String commandString;
	TakeCommand(String commandString){
		this.commandString = commandString;
	}
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
