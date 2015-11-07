package edu.umw.cpsc240fall2015team7.zork;
/**
The Event that handles adding to {@link Player} hunger.
@author Jim Bramnick
*/
class EatEvent
{
	private int amt;
	/**
	Create new EatEvent object and set the amt.
	@author Jim Bramnick
	*/
	public EatEvent(int amt)
	{
		this.amt=amt;
	}
	/**
	Executes this EatEvent and adds amt to the hunger in {@link Player}.
	If {@link Player} hunger is equal to {@link Player} hunger maximum, silently does nothing.
	@author Jim Bramnick
	*/
	public String execute()
	{
		return "";
	}

}
