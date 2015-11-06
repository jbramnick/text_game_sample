package edu.umw.cpsc240fall2015team7.zork;
/**
The event that handles adding to {@link {@link Player}} hunger.
@author Jim Bramnick
*/
class EatEvent
{
	private int amt;
	/**
	Creat new EatEvent object and set the amt.
	@param the integert to set amount to.
	@author Jim Bramnick
	*/
	public EatEvent(int amt)
	{
		this.amt=amt;
	}
	/**
	Executes the command to add amt to the hunger in {@link Player}.
	If {@link Player} hunger is equal to {@link Player} hunger maximum silently does nothing.
	@author Jim Bramnick
	*/
	public String execute()
	{
		return "";
	}

}
