package edu.umw.cpsc240fall2015team7.zork;
/**
*Command to interact with a UniqueNPC.
*UniqueNPC may perform some task in reaction to being talked to.
*@author Carson Meadows
*/
class TalkCommand {
        private UniqueNPC npc;

        /**
        *Constructs this TalkCommand.
        *@author Carson Meadows
        */
        public TalkCommand (NPC npc) {
		this.npc=npc;
        }

        /**
        *Executes this and activates UniqueNPC's response..
        *@author Carson Meadows
        */
        public String execute () {
        }

}

