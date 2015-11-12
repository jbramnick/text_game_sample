package edu.umw.cpsc240fall2015team7.zork;
/**
  *Changes item from one thing to another.
  *@author Nathanael Woodhead
  */
public class TransformEvent extends Event{
	private Item item;
	private Item endItem;
	/**
	  *Constructs this TransformEvent object.
	  *@param item The item that is transforming
	  *@param endItem The item that it becomes
	  *@author Nathanael Woodhead
	  */
	TransformEvent(Item item, Item endItem){
		this.item = item;
		this.endItem = endItem;
	}
	/**
	  *Replaces the item with endItem. If endItem does not exist,
	  * then it will return "Error: No endItem.".
	  *@author Nathanael Woodhead
	  */
	String execute(){
		try {
			Player.instance().addToInventory(endItem);
			Player.instance().removeFromInventory(item);
			return item.getPrimaryName() + " became " + 
				endItem.getPrimaryName();
		} catch (Item.NoItemException e) {
			return "Error: No endItem";
		}
	}
}
