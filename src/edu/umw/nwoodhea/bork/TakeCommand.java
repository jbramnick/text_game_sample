package edu.umw.nwoodhea.bork;
import java.util.ArrayList;
class TakeCommand extends Command{
	private String itemName;
	private String commandString;
	TakeCommand(String commandString){
		this.commandString = commandString;
	}
	String execute(){
		try{
			String itemName = commandString.substring(5,commandString.length());
		}
		catch(Exception e){
			return "\nTake what? \n";
		}
		this.itemName = itemName;
		Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
		try{
			if(itemName.equals("all")){
				ArrayList<Item> items = new ArrayList<Item>();
				for(Item item : currentRoom.getContents()){
					items.add(item);
				}
				if(items.size()== 0){
					return "There is nothing here to take." + "\n";
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
			currentRoom.remove(item);
			GameState.instance().addToInventory(item);
			return "Taken: " + itemName+"\n";
		}
		catch(Item.NoItemException e){
			return "There is no " + itemName + " here."+ "\n";
		}
	}
}	
