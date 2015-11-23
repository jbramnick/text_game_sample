package edu.umw.cpsc240fall2015team7.zork;

class CheckAmountEvent extends Event
{
	private Object o;
	
	public CheckAmountEvent(Object o)
	{
		this.o=o;
	}
	public String execute()
	{
		if(o instanceof Gun)
		{
			Gun gun=(Gun)o;
			try
			{
				gun=(Gun)Player.instance().getItemInInventoryNamed(gun.getPrimaryName());
				return "This "+gun.getPrimaryName()+" has "+gun.getAmmo()+" ammo in it.\n";
			}
			catch(Exception e)
			{
				try
				{
					gun=(Gun)Player.instance().getCurrentRoom().getItemNamed(gun.getPrimaryName());
					return "This "+gun.getPrimaryName()+" has "+gun.getAmmo()+" ammo in it.\n";
				}
				catch(Exception ex)
				{
					return "You do not have a "+gun.getPrimaryName()+".\n";

				}

			}
		}
		else
		{
			Light light=(Light)o;
			try
			{
				light=(Light)Player.instance().getItemInInventoryNamed(light.getPrimaryName());
				return "This "+light.getPrimaryName()+" has "+light.getBattery()+" battery left.\n";
			}
			catch(Exception e)
			{
				try
				{
					light=(Light)Player.instance().getCurrentRoom().getItemNamed(light.getPrimaryName());
					return "This "+light.getPrimaryName()+" has "+light.getBattery()+" battery left.\n";
				}
				catch(Exception ex)
				{
					return "You do not have a "+light.getPrimaryName()+".\n";

				}

			}
		}



	}

}
