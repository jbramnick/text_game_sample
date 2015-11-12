package edu.umw.cpsc240fall2015team7.zork;
/**
  *Causes an Item to disappear.
  *@author Nathanael Woodhead
  */
class DisappearEvent extends Event{
	private Item item;
	/**
	  *Creates a new DisappearEvent object. 
	  *@param item  The item that will be disappearing.
	  *@author Nathanael Woodhead
	  */
	DisappearEvent(Item item){
		this.item=item;
	}
	/**
	  *Removes the Item from the game.
	  *@author Nathanael Woodhead
	  */ 
	String execute(){
		Player.instance().removeFromInventory(item);
		Player.instance().getCurrentRoom()remove(item);
		return "";
	}

}

