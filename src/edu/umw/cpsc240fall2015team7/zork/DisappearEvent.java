package edu.umw.cpsc240fall2015team7.zork;
/**
  *Causes an item to disappear.
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
	}
	/**
	  *Removes the item from the game.
	  *@author Nathanael Woodhead
	  */ 
	String execute(){
		return "";
	}

}

