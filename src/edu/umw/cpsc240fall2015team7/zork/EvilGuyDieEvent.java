package edu.umw.cpsc240fall2015team7.zork;
/**
*The Event that handles the death of an EvilGuy NPC.
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
        public EvilGuyDieEvent(Item i, EvilGuy evilGuy) {
        }
        
	/**
        *Executes this Event to kill the evilGuy.
        *@author Carson Meadows
        */
        public String execute() {
        	return "";
	}
}

