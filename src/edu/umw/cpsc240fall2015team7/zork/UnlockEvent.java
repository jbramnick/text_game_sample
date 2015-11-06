package edu.umw.cpsc240fall2015team7.zork;
/**
The event used to unlock locked {@link Exit}s.
@author Jim Bramnick
*/
class UnlockEvent
{
	/**
	Constructs a UnlockEvent Object.
	@author Jim Bramnick
	*/
	public UnlockEvent()
	{

	}
	/**
	Executes this and unlocks all locked {@link Exit}s in the current {@link Room}.
	Silently does nothing if there are no locked {@link Exit}s in the {@link Room}.
	@author Jim Bramnick
	*/
	public String execute()
	{
		return "";
	}

}
