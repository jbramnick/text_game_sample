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
		try
		{
			gun=(Gun)Player.instance().getItemInInventoryNamed(gun.getPrimaryName());
			return "This "+gun.getPrimaryName()+" has "+gun.getAmmo()+" ammo in it.";
		}
		catch(Exception e)
		{
			try
			{
				gun=(Gun)Player.instance().getCurrentRoom().getItemNamed(gun.getPrimaryName());
				return "This "+gun.getPrimaryName()+" has "+gun.getAmmo()+" ammo in it.";
			}
			catch(Exception ex)
			{
				return "You do not have a "+gun.getPrimaryName()+".";

			}

		}


	}

}
