package edu.umw.cpsc240fall2015team7.zork;
/**
*A weapon used to fight enemies in the Dungeon.
*@author Carson Meadows
*/
class Gun extends Weapon {
        private int power,speed;
	private int ammo;
	private int capacity;

        /**
        *Constructs this Gun.
        *@author Carson Meadows
        */
        public Gun (int capacity, int power, String primaryName, int weight) {
		this.capacity=capacity;
		this.power=power;
		this.primaryName=primaryName;
		this.weight=weight;
		this.speed = -1;
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
	
	public int getCapacity() {
		return capacity;
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
	/**
	  *Returns the name of the gun.
	  *@author Nathanael Woodhead
	  */
	public String getName(){
		return primaryName;
	}
	/**
	  *Returns the speed. Speed is used for deciding what who hits first in combat.
	  *@author Nathanael Woodhead
	  */
	public int getSpeed(){
		return speed;
	}

}
