package edu.umw.cpsc240fall2015team7.zork;
/**
  Objects that are named Non Player Characters that are not randomly spawned.
  @author Jim Bramnick
 */
class UniqueNPC
{
	private Item item;
	private int snacks,medkits,ammo;
	/**
	  Creates Unique Non Player Character.
	  @author Jim Bramnick
	 */
	public UniqueNPC()
	{

	}
	/**
	  Initiates conversation with NPC. 
	  This will print to the screan and give the user options to select an input.
	  If the NPC does not have any conversation or no options just prints a generic output message.
	  @author Jim Bramnick
	 */
	public void getTalkedAt()
	{

	}
	/**
	  Sets current Item to Null and adds that item to {@link Player} Inventory. 
	  @throws NoItemException if item is Null
	  @author Jim Bramnick
	 */
	public void giveItem()
	{
	}
	/**
	  Reduces {@link Player} health by the power of this NPC.
	  If this reduces {@link Player} health below zero then the {@link DieEvent} is called.
	  @author Jim Bramnick
	 */
	public void attackPlayer()
	{

	}
	/**
	  Adds this snacks to {@link Player} snacks and sets this snacks to zero.
	  @author Jim Bramnick
	 */
	public void giveSnacks()
	{

	}
	/**
	  Adds this medkits to {@link Player} medkits and sets this medkits to zero.
	  @author Jim Bramnick
	 */
	public void giveMedkits()
	{

	}
	/**
	  Adds this ammo to {@link Player} ammo and sets this ammo to zero.
	  @author Jim Bramnick
	 */
	public void giveAmmo()
	{

	}




}
