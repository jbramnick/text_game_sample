package edu.umw.cpsc240fall2015team7.zork;
/**
the event that handles the wounding of a zombie npc.
@author Jim Bramnick
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
	Executes the command to wound the zombie reducing the zombie's health.
	If the zombie's health falls below zero a ZombieDieEvent is called.
	@author Jim Bramnick
	*/
	public String execute()
	{
	}

}
