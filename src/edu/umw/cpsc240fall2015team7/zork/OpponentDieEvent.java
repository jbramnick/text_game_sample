package edu.umw.cpsc240fall2015team7.zork;
/**
*The event that handles the death of a opponent NPC.
*@author Jim Bramnick
*/
class OpponentDieEvent
{
	private Opponent opponent;
	/**
	Sets the opponent to be killed.
	@author Jim Bramnick
	*/
	public OpponentDieEvent(Opponent opponent)
	{
	}
	/**
	Executes the command to kill the opponent.
	A score event is called to add score.
	The {@link Opponent} is removed form {@link Dungeon} and {@link Room}.
	@author Jim Bramnick
	*/
	public String execute()
	{
	}

}
