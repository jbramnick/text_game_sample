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
			Player.instance().getItemInInventoryNamed(item.getPrimaryName());
			Player.instance().addToInventory(end);
			Player.instance().removeFromInventory(item);
			return item.getPrimaryName() + " became " + end.getPrimaryName()+"\n";

		}
		catch(Item.NoItemException e)
		{
			try
			{
				Item end = GameState.instance().getDungeon().getItem(endItem);
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
