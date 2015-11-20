package edu.umw.cpsc240fall2015team7.zork;
/**
Event used to add item to inventory received from {@link Npc}.
@author Jim Bramnick
*/
class ReceiveEvent extends Event{
	private Item item;
	/**
	Constructs this ReceiveEvent.
	@author Jim Bramnick
	*/
	public ReceiveEvent(Item item){
		this.item=item;
	}
	/**
	Adds the item to the {@link Player} Inventory and adds medkits, ammo and snacks to {@link Player} medkits, ammo and snacks. 
	If item already exists in the Inventory of {@link Player} still adds that item.
	If item is null silently does nothing.
	@author Jim Bramnick
	*/
	public String execute() {
		if (!item.equals(null)) {
			Player.instance().addToInventory(item);
		}
		return "";

	}

}
