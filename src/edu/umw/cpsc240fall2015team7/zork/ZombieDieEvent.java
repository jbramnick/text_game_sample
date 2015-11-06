package edu.umw.cpsc240fall2015team7.zork;
/**
*The event that handles the death of a zombie NPC.
*@author Jim Bramnick
*/
class ZombieDieEvent
{
	private Zombie zombie;
	/**
	Sets the zombie to be killed.
	@author Jim Bramnick
	*/
	public ZombieDieEvent(Zombie zombie)
	{
	}
	/**
	Executes the command to kill the zombie.
	A score event is called to add score.
	The zombie is removed from room and dungeon.
	@author Jim Bramnick
	*/
	public String execute()
	{
	}

}
