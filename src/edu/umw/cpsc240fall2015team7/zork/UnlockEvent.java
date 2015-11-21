package edu.umw.cpsc240fall2015team7.zork;
import java.util.ArrayList;
/**
The {@link Event} used to unlock locked {@link Exit}s.
@author Jim Bramnick
*/
class UnlockEvent extends Event{
	class NoLockedDoorException extends Exception{}

	/**
	Constructs this UnlockEvent object.
	@author Jim Bramnick
	*/
	public UnlockEvent(Object o){
		
	}
	/**
	Executes this UnlockEvent and unlocks all locked {@link Exit}s in the current {@link Room}.
	Silently does nothing if there are no locked {@link Exit}s in the {@link Room}.
	@author Jim Bramnick
	*/
	public String execute(){
		ArrayList<Exit> exits = Player.instance().getCurrentRoom().getExits();
		boolean needed = false;
		for(Exit exit : exits){
			if(exit.unlock()){
				needed = true;
			}
		}
		if(needed == false){
			//execute cant throw exceptions because the super class doesnt throw an exception
			//throw new UnlockEvent.NoLockedDoorException();
			return "";
		}
		return "Doors Unlocked!";
	}

}
