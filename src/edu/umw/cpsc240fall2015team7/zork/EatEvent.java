package edu.umw.cpsc240fall2015team7.zork;
/**
The event that handles eating food to lower hunger.
@author Jim Bramnick
*/
class EatEvent
{
	private int amt;
	/**
	Create new EatEvent object and set the amt.
	@author Jim Bramnick
	*/
	public EatEvent(int amt )
	{
	}
	/**
	Executes the command to add the hunger amt to the hunger in GameState.
	If GameState hunger is equal to hunger maximum silently does nothing.
	@author Jim Bramnick
	*/
	public String execute()
	{
		return "";
	}

}
