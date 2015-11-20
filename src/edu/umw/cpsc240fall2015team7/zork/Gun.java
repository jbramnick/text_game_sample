package edu.umw.cpsc240fall2015team7.zork;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.ArrayList;
/**
*A weapon used to fight enemies in the Dungeon.
*@author Carson Meadows
*/
class Gun extends Weapon {
	private int ammo;
	private int capacity;
	private String ammoType;
        /**
        *Constructs this Gun from a scanner.
        *@author Carson Meadows
        */
        public Gun (Scanner scanner) throws Item.NoItemException,Dungeon.IllegalDungeonFormatException{
		super(scanner);
		super.power = Integer.parseInt(scanner.nextLine());
		super.speed =  -1; 
		this.capacity = Integer.parseInt(scanner.nextLine());
		this.ammo = capacity;
	}
	/**
	 *Constructs this Gun.
	 *@author Carson Meadows
	 */
	public Gun (int capacity, int power, String primaryName, int weight) {
		this.capacity=capacity;
		super.power=power;
		super.primaryName=primaryName;
		super.weight=weight;
		super.speed = -1;
	}
	/**
	  *Returns an exact copy of this gun.
	  *@author Nathanael Woodhead
	  */
	public Gun clone(){
		return new Gun(this.capacity, this.power, this.primaryName, this.weight);
	}
	/**
	 *Adds passed int to this Gun's capacity.
	 *@author Carson Meadows
	 */
	public void reload(int ammo) {
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
	public String getAmmoType()
	{
		return ammoType;
	}
	/**
	 *Returns this Gun's power.
	 *@author Carson Meadows
	 */

	/*
	public int getPower () {
		return power;
	}
	*/
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
	/*
	public String getName(){
		return primaryName;
	}
	*/
	/**
	 *Returns the speed. Speed is used for deciding what who hits first in combat.
	 *@author Nathanael Woodhead
	 */
	/*
	public int getSpeed(){
		return speed;
	}
	*/

}
