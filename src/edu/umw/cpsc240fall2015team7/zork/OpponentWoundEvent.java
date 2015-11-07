package edu.umw.cpsc240fall2015team7.zork;
/**
*The Event that handles the wounding of an Opponent NPC.
*@author Jim Bramnick
*/
class OpponentWoundEvent
{
	private Opponent opponent;
	/**
	Sets the Opponent to be wounded.
	@author Jim Bramnick
	*/
	public OpponentWoundEvent(Opponent opponent)
	{
	}
	/**
	Executes this Event to reduce the Opponent's health.
	If the {@link Opponent} health falls below zero, an {@link OpponentDieEvent} is called.
	@author Jim Bramnick
	*/
	public String execute()
	{
	}

}
