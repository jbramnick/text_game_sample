package edu.umw.cpsc240fall2015team7.zork;

class CheckAmmoEvent extends Event
{
	private Gun gun;
	private String gunName;
	
	public CheckAmmoEvent(Object o,String commandString)
	{
		this.gun=(Gun)o;
		this.gunName=commandString;
	}
	public String execute()
	{
		try
		{
			gun=(Gun)Player.instance().getItemInInventoryNamed(gunName);
			return "This "+gun.getPrimaryName()+" has "+gun.getAmmo()+" ammo in it.";
		}
		catch(Exception e)
		{
			return "You do not have a "+gunName+".";
		}


	}

}
