package edu.umw.cpsc240fall2015team7.zork;
/**
*Command to use a Gun against an NPC.
*@author Carson Meadows
*/
class ShootCommand extends Command {
        private Gun weapon;
	private String commandString;
	private Npc npc;

        /**
        *Constructs this ShootCommand.
        *@author Carson Meadows
        */
        public ShootCommand (String commandString) {
		this.commandString=commandString;
        }

        /**
        *Executes this and depletes the Npc's health and the Gun's ammo. 
	*Determines the correct amount of health to deplete based on the Gun's power.
        *@author Carson Meadows
        */
        public String execute () {
		String text = CombatEngine.instance().fight(npc,weapon);
        	return text;
	}

}

