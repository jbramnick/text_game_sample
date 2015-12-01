package edu.umw.cpsc240fall2015team7.zork;
/**
 *Changes item from one thing to another.
 *@author Nathanael Woodhead
 */
public class TransformEvent extends Event{
	private Item item;
	private String endItem;
	/**
	 *Constructs this TransformEvent object.
	 *@param item The item that is transforming
	 *@param endItem The item that it becomes
	 *@author Nathanael Woodhead
	 */
	TransformEvent(Object item, String endItem){
		this.item = (Item)item;
		this. endItem = endItem;
	}
	/**
	 *Replaces the item with endItem. If endItem does not exist,
	 * then it will return "Error: No endItem.".
	 *@author Nathanael Woodhead
	 */
	String execute(){
		try
		{
			Item end = GameState.instance().getDungeon().getItem(endItem);
			this.item =Player.instance().getItemInInventoryNamed(item.getPrimaryName());
			Player.instance().removeFromInventory(item);
			if((end.getWeight()+Player.instance().getLoad())>Player.instance().getCarryWeight())
			{
				Player.instance().getCurrentRoom().add(end);
				return item.getPrimaryName() + " became " + end.getPrimaryName()+", but you cant carry that much weight and you dropped it in the room.\n";
			}
			else
			{
				Player.instance().addToInventory(end);
				return item.getPrimaryName() + " became " + end.getPrimaryName()+"\n";

			}

		}
		catch(Item.NoItemException e)
		{
			try
			{
				Item end = GameState.instance().getDungeon().getItem(endItem);
				this.item = Player.instance().getCurrentRoom().getItemNamed(item.getPrimaryName());
				Player.instance().getCurrentRoom().remove(item);
				Player.instance().getCurrentRoom().add(end);
				return item.getPrimaryName() + " became " + end.getPrimaryName()+"\n";

			}
			catch(Exception ex)
			{
				return "";

			}
		}

	}
}
