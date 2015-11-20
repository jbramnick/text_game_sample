package edu.umw.cpsc240fall2015team7.zork;
/**
*The Event that handles the death of an Npc NPC.
*@author Jim Bramnick
*/
class NpcDieEvent
{
	private Npc npc;
	/**
	Sets the npc to be killed.
	@author Jim Bramnick
	*/
	public NpcDieEvent(Object i, Npc npc)
	{
	}
	/**
	Sets the npc to be killed.
	@author Jim Bramnick
	*/
	public NpcDieEvent(Npc npc)
	{
	}
	/**
	Executes this Event to kill the npc.
	A scoreEvent is called to add score.
	The {@link Npc} is removed form {@link Dungeon} and {@link Room}.
	@author Jim Bramnick
	*/
	public String execute(){
		return "";
	}

}
