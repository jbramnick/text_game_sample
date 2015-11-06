package edu.umw.cpsc240fall2015team7.zork;
/**
*Command to interact with a UniqueNPC.
*UniqueNPC may perform some task in reaction to being talked to.
*@author Carson Meadows
*/
class TalkCommand {
	private String commandString;

        /**
        *Constructs this TalkCommand. 
        *@author Carson Meadows
        */
        public TalkCommand (String commandString) {
		this.commandString=commandString;
        }

        /**
        *Parses commandString and activates the NPC's response. If the NPC specified in 
	*commandString is not in the player's current Room, prints error message.
        *@author Carson Meadows
        */
        public String execute () {
        }

}

