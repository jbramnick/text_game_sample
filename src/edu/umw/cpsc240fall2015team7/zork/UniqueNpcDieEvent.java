package edu.umw.cpsc240fall2015team7.zork;
/**
*Event that handles the death of a UniqueNpc.
*@author Jim Bramnick
*/
class UniqueNpcDieEvent{
	private UniqueNpc npc;
	/**
	Sets the UniquNpce that is killed.
	@param the UniqueNpc to be killed.
	@author Jim Bramnick
	*/
	UniqueNpcDieEvent(UniqueNpc npc){

	}
	/**
	Executes the killing of the UniqueNpc.
	Calls an {@link ScoreEvent} to add to the {@link Player} score.
	Removes the UniqueNpc from the current {@link Dungeon} and the {@link Room}.
	@author Jim Bramnick
	*/
	public String execute(){

	}

}
