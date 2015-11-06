package edu.umw.cpsc240fall2015team7.zork;
/**
*The event that handles the wounding of a zombie NPC.
*@author Jim Bramnick
*/
class ZombieWoundEvent
{
	private Zombie zombie;
	/**
	Sets the zombie to be wounded.
	@author Jim Bramnick
	*/
	public ZombieWoundEvent(Zombie zombie)
	{
	}
	/**
	Executes the command to reduce the zombie's health.
	If the zombie's health falls below zero a ZombieDieEvent is called.
	@author Jim Bramnick
	*/
	public String execute()
	{
	}

}
