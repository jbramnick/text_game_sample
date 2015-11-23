package edu.umw.cpsc240fall2015team7.zork;
/**
Event used to add item to inventory received from {@link Npc}.
@author Jim Bramnick
*/
class ReceiveEvent extends Event{
	private Object item;
	private String s;
	/**
	Constructs this ReceiveEvent.
	@author Jim Bramnick
	*/
	public ReceiveEvent(Object item,String s){
		this.item=item;
		this.s=s;
	}
	/**
	Adds the item to the {@link Player} Inventory and adds medkits, ammo and snacks to {@link Player} medkits, ammo and snacks. 
	If item already exists in the Inventory of {@link Player} still adds that item.
	If item is null silently does nothing.
	@author Jim Bramnick
	*/
	public String execute() {
		Item i=GameState.instance().getDungeon().getItem(s);
		
		if ((!item.equals(null))) {
			boolean canCarry=Player.instance().getLoad()+i.getWeight() < 40;
			if(canCarry)
				Player.instance().addToInventory(i);
			else
				return "You cannont carry that much weight";
		}
		return "";

	}

}
