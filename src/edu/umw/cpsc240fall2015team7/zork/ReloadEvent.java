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
	ReloadEvent(Object item, String commandString)
	{
		this.commandString = commandString;
	}
	
	/**
	  *Parses the commandString and carries out the necessary actions. 
	  *The commandString should be in the format "reload gun". This should parse the commandString and extract the gun's name. 
	  *If there is no gun by that name in the players inventory then this should return a message saying that you do not have that 
	  *gun. If the gun is in the player inventory this will reload it and return conformation message.
	  *@author Nathanael Woodhead
	  */
	String execute(){
		String weaponString = commandString.substring(7,commandString.length());
		try{
			System.out.println(weaponString);
			this.weapon =(Gun)Player.instance().getItemInInventoryNamed(weaponString);
		}catch (Exception e){
			return "You can't reload a " +  weaponString+".";
		}
		if(weapon == null){
			return "You don't have a " + weaponString + ".";
		}
		int capacity = weapon.getCapacity();
		int currAmmo = weapon.getAmmo();
		String ammoType = weapon.getAmmoType();
		int supply = Player.instance().countAmmo(ammoType);
		int toAdd = capacity - currAmmo;
		if (supply >= toAdd) {
			weapon.reload(toAdd);
			while(toAdd>0){
				Player.instance().removeItem(ammoType);
				toAdd = toAdd - 1;
			}
		}else{
			weapon.reload(supply);
			while(toAdd>0){
				Player.instance().removeItem(ammoType);
				toAdd = toAdd -1;
			}
		}
		return "Reloaded " + weaponString+".";	
	}
}
	
