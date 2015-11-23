package edu.umw.cpsc240fall2015team7.zork;
/**
  *A Event for reloading a gun.
  *@author Nathanael Woodhead
  */
class ReloadEvent extends Event{
	private Gun weapon;
	private String commandString;
	/**
	  *Creates a new ReloadEvent object.
	  *@author Nathanael Woodhead
	  */
	ReloadEvent(Object item)
	{
		this.weapon=(Gun)item;
	}
	
	/**
	  *Parses the commandString and carries out the necessary actions. 
	  *The commandString should be in the format "reload gun". This should parse the commandString and extract the gun's name. 
	  *If there is no gun by that name in the players inventory then this should return a message saying that you do not have that 
	  *gun. If the gun is in the player inventory this will reload it and return conformation message.
	  *@author Nathanael Woodhead
	  */
	String execute(){
		try
		{
			this.weapon=(Gun)Player.instance().getItemInInventoryNamed(weapon.getPrimaryName());
		}
		catch(Exception e)
		{
			return "You do not have a "+weapon.getPrimaryName()+"\n";
		}
		int capacity = weapon.getCapacity();
		int currAmmo = weapon.getAmmo();
		String ammoType = weapon.getAmmoType();
		int supply = Player.instance().countAmmo(ammoType);
		int toAdd = capacity - currAmmo;
		if ((supply >= toAdd)&&(toAdd>0)) {
			weapon.reload(toAdd);
			while(toAdd>0){
				Player.instance().removeItem(ammoType);
				toAdd = toAdd - 1;
			}
			return "Reloaded " + weapon.getPrimaryName()+".\n";
		}else if(supply==0){
			return "You have no " +ammoType+" to reload "+weapon.getPrimaryName()+ " with\n";
		}
		else if(toAdd==0)
		{	
			return weapon.getPrimaryName() + " is full.\n";
		}
		else
		{
			weapon.reload(supply);
			while(toAdd>0){
				Player.instance().removeItem(ammoType);
				toAdd = toAdd -1;
			}
			return "Partially Reloaded " + weapon.getPrimaryName()+".\n";	
		}
	}
}

