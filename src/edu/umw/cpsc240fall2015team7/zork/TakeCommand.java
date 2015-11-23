package edu.umw.cpsc240fall2015team7.zork;
import java.util.ArrayList;
/**
 *Takes an item from the surrounding area and moves it into the players inventory.
 *@author Nathanael Woodhead
 */
class TakeCommand extends Command{
	private String itemName;
	private String commandString;
	/**
	 *Creates a new TakeCommand object.
	 *@param commandString A string containing an item name.
	 *@author Nathanael Woodhead
	 */
	TakeCommand(String commandString){
		this.commandString = commandString;
	}
	/**
	 *Executes the TakeCommand by parsing the commandString to extract the item and moves the item to the players inventory
	 *removing it from the current room. If the item is too heavy this will return a String with a message saying that
	 *the item is too heavy to carry. If no item name is given it will return a string saying "Take what?".  
	 *taken this will return a String saying "Taken itemName".
	 *@author Nathanael Woodhead and Jim Bramnick
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
		Room currentRoom = Player.instance().getCurrentRoom();
		try{
			if(commandString.matches(".*\\d+.*"))
			{
				int number=Integer.parseInt(itemName.split(" ")[0]);
				itemName=itemName.split(" ")[1];
				ArrayList<Item> items = new ArrayList<Item>();
				for(Item item : currentRoom.getContents()){
					if(item.goesBy(itemName))
						items.add(item);
				}
				if(number>items.size())
				{
					return "There are not "+number+ " "+itemName+"s in here.";

				}
				for(int i=0;i<number;i++)
				{
					weight+=items.get(i).getWeight();
				}
				if((items.size()== 0)){
					return "There are no "+itemName+"s here to take.";
				}
				int load = Player.instance().getLoad();
				if((weight + load) > Player.instance().getCarryWeight()){
					return "You cannot carry that much weight.\n";
				}
				String text = "Taken:" + "\n";
				for(int i=0;i<number;i++){
					currentRoom.remove(items.get(i));
					Player.instance().addToInventory(items.get(i));
				}
				text+=number + " " + itemName+"s.";
				PassTimeEvent e=new PassTimeEvent(null,"1");
				e.execute();
				return text;

			}
			if(commandString.contains("all")){
				ArrayList<Item> items = new ArrayList<Item>();
				for(Item item : currentRoom.getContents()){
					items.add(item);
				}
				for(Item item : items){
					weight += item.getWeight();
				}
				if((items.size()== 0)){
					return "There is nothing here to take." + "\n";
				}
				int load = Player.instance().getLoad();
				if((weight + load) > Player.instance().getCarryWeight()){
					return "You cannot carry that much weight.\n";
				}
				String text = "Taken:" + "\n";
				for(Item item : items){
					currentRoom.remove(item);
					Player.instance().addToInventory(item);
					text += item+ "\n"; 
				}
				PassTimeEvent e=new PassTimeEvent(null,"1");
				e.execute();
				return text;
			}
			Item item = currentRoom.getItemNamed(itemName);
			weight = item.getWeight();
			int load = Player.instance().getLoad();
			if((weight + load) > Player.instance().getCarryWeight()){

				return "You cannot carry that much weight.\n";
			}
			currentRoom.remove(item);
			Player.instance().addToInventory(item);
			PassTimeEvent e=new PassTimeEvent(null,"1");
			e.execute();
			return "Taken: " + itemName+"\n";
		}
		catch(Item.NoItemException e){

			return "There is no " + itemName + " here."+ "\n";
		}

	}
}
