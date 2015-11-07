package edu.umw.cpsc240fall2015team7.zork;
/**
*Command to use a Gun against an NPC.
*@author Carson Meadows
*/
class ShootCommand {
        private Gun weapon;
	private Npc npc;

        /**
        *Constructs this ShootCommand.
        *@author Carson Meadows
        */
        public ShootCommand (Npc npc, Gun weapon) {
		this.npc=npc;
		this.weapon=weapon;
        }

        /**
        *Executes this and depletes the NPC's health and the Gun's ammo. 
	*Determines the correct amount of health to deplete based on the Gun's power..
        *@author Carson Meadows
        */
        public String execute () {
        }

}

