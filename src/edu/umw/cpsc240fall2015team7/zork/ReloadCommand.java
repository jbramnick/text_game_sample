package edu.umw.cpsc240fall2015team7.zork;
/**
*Command to reload a Gun.
*@throws NoAmmoException
*@author Carson Meadows
*/
class ReloadCommand {
        private Gun weapon;

        /**
        *Constructs this ReloadCommand
        *@author Carson Meadows
        */
        public ReloadCommand (Gun weapon) {
		this.weapon=weapon;
        }

        /**
        *Reloads the weapon this holds. If the player does not have sufficient ammo to refill weapon,
	* will refill until depletion, then throw NoAmmoException().
	*@throws NoAmmoException
        *@author Carson Meadows
        */
        public String execute () {
        }

}


