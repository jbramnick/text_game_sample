package edu.umw.cpsc240fall2015team7.zork;

class DropEvent extends Event
{
	Item item;
	public DropEvent(Object i)
	{
		this.item=(Item)i;
	}
	public String execute()
	{
		try
		{
			item=Player.instance().getItemInInventoryNamed(item.getPrimaryName());
			Player.instance().removeItem(item.getPrimaryName());
			Player.instance().getCurrentRoom().add(item);
			return "Dropped: "+item.getPrimaryName()+".\n";

		}
		catch(Item.NoItemException e)
		{
			return "You do not have a "+item.getPrimaryName()+" in your inventory.\n";
		}

	}
}
