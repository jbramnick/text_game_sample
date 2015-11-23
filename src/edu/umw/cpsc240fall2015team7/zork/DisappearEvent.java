package edu.umw.cpsc240fall2015team7.zork;
/**
  *Causes an Item to disappear.
  *@author Nathanael Woodhead
  */
class DisappearEvent extends Event{
	private Object i;
	/**
	  *Creates a new DisappearEvent object. 
	  *@param item  The item that will be disappearing.
	  *@author Nathanael Woodhead
	  */
	DisappearEvent(Object i){
		this.i=i;
	}
	/**
	  *Removes the Item from the game.
	  *@author Nathanael Woodhead
	  */ 
	String execute(){
		if(i instanceof Item)
		{
			
			Item item=(Item)i;
			try
			{
				item=Player.instance().getItemInInventoryNamed(item.getPrimaryName());
				Player.instance().removeFromInventory(item);
				Player.instance().getCurrentRoom().remove(item);
				GameState.instance().getDungeon().removeItem(item);

			}
			catch(Item.NoItemException e)
			{
				try
				{
					item=Player.instance().getCurrentRoom().getItemNamed(item.getPrimaryName());
					Player.instance().getCurrentRoom().remove(item);
					GameState.instance().getDungeon().removeItem(item);

				}
				catch(Exception ex)
				{
					return "Ther is no " + item.getPrimaryName() + " in your inventory or this room\n"; 
				}
			}

		}
		else
		{
			Npc item=(Npc)i;
			try
			{
				item=Player.instance().getCurrentRoom().getNpcNamed(item.getPrimaryName());
			}
			catch(Exception e)
			{
				return "There is no "+item.getPrimaryName()+" in this room\n";

			}
			Player.instance().getCurrentRoom().removeNpc(item);

		}

		return "\n";
	}

}

