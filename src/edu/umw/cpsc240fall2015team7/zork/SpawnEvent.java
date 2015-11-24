package edu.umw.cpsc240fall2015team7.zork;
/**
*Event for spawning Npc's.
*@author Carson Meadows
*/
class SpawnEvent extends Event {
	private String name;

	/**
	*Constructs this.
	*@author Carson Meadows
	*/
	SpawnEvent (String name) {
		this.name=name;
	}
	/**
	*Spawns a new Npc in the current room as specified in the constructor.
	*@author Carson Meadows
	*/
	String execute () {
		Npc npc = Gamestate.instance().getDungeon().getNpc(name);
		Player.instance().getCurrentRoom().addNpc(npc);
		return name + " appeared!";
	}
}
