package edu.umw.cpsc240fall2015team7.zork;
/**
The {@link Event} used to unlock locked {@link Exit}s.
@author Jim Bramnick
*/
class UnlockEvent{
	class NoLockedDoorException extends Exception{}

	/**
	Constructs this UnlockEvent object.
	@author Jim Bramnick
	*/
	public UnlockEvent(){
		
	}
	/**
	Executes this UnlockEvent and unlocks all locked {@link Exit}s in the current {@link Room}.
	Silently does nothing if there are no locked {@link Exit}s in the {@link Room}.
	@author Jim Bramnick
	*/
	public String execute(){
		ArrayList<Exit> exits = Player.instance().GetCurrentRoom().getExits();
		boolean needed = false;
		for(Exit exit : Exits){
			if(exit.unlock()){
				needed = true;
			}
		}
		if(needed == false){
			throw new UnlockEvent.NoLockedDoorException();
		}
		return "Door Unlocked!";
	}

}
