package edu.umw.cpsc240fall2015team7.zork;
/**
*The Event that handles the death of the EvilGuy NPC.
*@author Carson Meadows
*/
class EvilGuyDieEvent {
        private EvilGuy evilGuy;
        
	/**
        *Sets evilGuy to be killed.
        *@author Carson Meadows
        */
        public EvilGuyDieEvent(EvilGuy evilGuy) {
        }
        
	/**
        *Executes this Event to kill the evilGuy.
        *@author Carson Meadows
        */
        public String execute() {
        	return "";
	}
}

