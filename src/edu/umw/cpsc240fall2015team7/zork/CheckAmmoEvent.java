package edu.umw.cpsc240fall2015team7.zork;

class CheckAmmoEvent extends Event
{
	private Gun gun;
	
	public CheckAmmoEvent(Object o)
	{
		this.gun=(Gun)o;
	}
	public String execute()
	{	
		return "This "+gun.getPrimaryName()+" has "+gun.getAmmo()+" ammo in it.";

	}

}
