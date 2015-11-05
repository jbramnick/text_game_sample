package edu.umw.cpsc240fall2015team7.zork;
/**
  *Changes item from one thing to another.
  *@author Nathanael Woodhead
  */
public class TransformEvent extends Event{
	private Item item;
	private Item endItem;
	/**
	  *Creates a new TransformEvent object. Requires the item to be transformed and the item that it turns into.
	  *@param item The item that is transforming
	  *@param endItem The item that it becomes
	  *@author Nathanael Woodhead
	  */
	TransformEvent(Item item, Item endItem){
		this.item = item;
		this.endItem = endItem;
	}
	/**
	  *Replaces the item with endItem.
	  *@author Nathanael Woodhead
	  */
	String execute(){
		return "";
	}
}
