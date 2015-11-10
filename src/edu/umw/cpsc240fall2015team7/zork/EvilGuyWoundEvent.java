package edu.umw.cpsc240fall2015team7.zork;
/**
*The Event that handles the wounding of the EvilGuy NPC.
*@author Carson Meadows
*/
class EvilGuyWoundEvent {
        private EvilGuy evilGuy;
	private int damage;

        /**
        *Constructs this Event.
	*@param evilGuy The evilGuy who will be receiving the damage. 
        *@param damage The damage amount that will be inflicted. 
	*@author Carson Meadows and Nathanael Woodhead
        */
        public EvilGuyWoundEvent(EvilGuy evilGuy, int damage){
		this.evilGuy = evilGuy;
		this.damage = damage;
        }

        /**
        *Executes this Event to wound the evilGuy.
        *@author Carson Meadows
        */
        public String execute() {
        	return "";
	}

}
