package edu.umw.nwoodhea.bork;
class TakeCommand extends Command{
	private String itemName;
	TakeCommand(String itemName){
		this.itemName = itemName;
	}
	String execute(){
		Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
		try{
			Item item = currentRoom.getItemNamed(itemName);
			currentRoom.remove(item);
			GameState.instance().addToInventory(item);
			return "Taken: " + itemName;
		}
		catch(Item.NoItemException e){
			return "There is no " + itemName + "here.";
		}
	}
}	
