package edu.umw.cpsc240fall2015team7.zork;
/**
*The event that handles the wounding of a opponent NPC.
*@author Jim Bramnick
*/
class OpponentWoundEvent
{
	private Opponent opponent;
	/**
	Sets the opponent to be wounded.
	@author Jim Bramnick
	*/
	public OpponentWoundEvent(Opponent opponent)
	{
	}
	/**
	Executes the command to reduce the opponent's health.
	If the {@link Opponent} health falls below zero a {@link OpponentDieEvent} is called.
	@author Jim Bramnick
	*/
	public String execute()
	{
	}

}
