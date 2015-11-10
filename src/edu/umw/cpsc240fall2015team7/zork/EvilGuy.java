package edu.umw.cpsc240fall2015team7.zork;
/**
*NPC which spawns other NPC's.
*@author Carson Meadows
*/
class EvilGuy extends Npc {
	private String primaryName;
	/**
	*Constructs this EvilGuy.
	*@author Carson Meadows
	*/
	public EvilGuy (String primaryName) {
		this.primaryName=primaryName;
	}

	/**
	*Spawns an opponent using the {@link Spawner}.
	*@author Carson Meadows
	*/
	public void spawn() {
	}
        /**
        *Damages the player by the amount of this NPC's power.
        *@author Carson Meadows
        */
        public void attackPlayer () {
        }

}

