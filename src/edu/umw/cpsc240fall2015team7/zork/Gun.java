package edu.umw.cpsc240fall2015team7.zork;
/**
*An Item used to fight enemies in the Dungeon.
*@author Carson Meadows
*/
class Gun extends Item {
        private int power;
	private int ammo;
	private int capacity;

        /**
        *Constructs this Gun.
        *@author Carson Meadows
        */
        public Gun (int capacity, int power, String primaryName, int weight) {
        }

        /**
        *Adds passed int to this Gun's capacity.
        *@author Carson Meadows
        */
        public void addAmmo(int ammo) {
        	this.ammo+=ammo;
	}

	/**
	*Returns this Gun's ammo.
	*@author Carson Meadows
	*/
	public int getAmmo () {
		return ammo;
	}

	/**
	*Returns this Gun's power.
	*@author Carson Meadows
	*/
	public int getPower () {
		return power;
	}
	/**
	*Reduces this Gun's ammo by 1 unit.
	*@author Carson Meadows
	*/
	public void reduceAmmo() {
		this.ammo--;
	}
}
