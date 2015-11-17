package edu.umw.cpsc240fall2015team7.zork;

/**
*Abstract class which all weapons extend from.
*@author Carson Meadows
*/
abstract class Weapon{
	String primaryName;
	int weight, speed;

	/**
	*Returns the weight of this.
	*@author Carson Meadows
	*/
	public int getWeight() {
		return weight;
	}
	/**
	*Returns the speed of this.
	*@author Carson Meadows
	*/
	public int getSpeed() {
		return speed;
	}
	/**
	*Returns the name of this.
	*@author Carson Meadows
	*/
	public String getName() {
		return primaryName;
	}
}

	
