package edu.umw.cpsc240fall2015team7.zork;
/**
*The Event that handles the death of an Opponent NPC.
*@author Jim Bramnick
*/
class OpponentDieEvent
{
	private Opponent opponent;
	/**
	Sets the opponent to be killed.
	@author Jim Bramnick
	*/
	public OpponentDieEvent(Item i, Opponent opponent)
	{
	}
	/**
	Sets the opponent to be killed.
	@author Jim Bramnick
	*/
	public OpponentDieEvent(Opponent opponent)
	{
	}
	/**
	Executes this Event to kill the opponent.
	A scoreEvent is called to add score.
	The {@link Opponent} is removed form {@link Dungeon} and {@link Room}.
	@author Jim Bramnick
	*/
	public String execute(){
		return "";
	}

}
