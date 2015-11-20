package edu.umw.cpsc240fall2015team7.zork;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.ArrayList;
/**
*A weapon used to fight enemies in the Dungeon.
*@author Carson Meadows
*/
class Gun extends Weapon{
	private int ammo;
	private int capacity;
	private String ammoType;
        /**
        *Constructs this Gun from a scanner.
u       *@author Carson Meadows
        */
        public Gun (Scanner scanner) throws Item.NoItemException,Dungeon.IllegalDungeonFormatException{
		super(scanner);
		this.capacity = Integer.parseInt(scanner.nextLine());
		this.ammo = capacity;
		scanner.nextLine();
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
	public Gun(String primaryName,Hashtable<String,ArrayList<Event>> actions,ArrayList<String> secondaryNames,int weight,Hashtable<String,String> messages,int speed,int power,int capacity,int ammo)
	{
		super(primaryName,actions,secondaryNames,weight,messages,speed,power);
		this.capacity=capacity;
		this.ammo=ammo;
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
	 *Reduces this Gun's ammo by 1 unit.
	 *@author Carson Meadows
	 */
	
	public void reduceAmmo() {
		this.ammo--;
	}
	public Gun clone()
	{
		return new Gun(this.primaryName,this.actions,this.secondaryNames,this.weight,this.messages,this.speed,this.power,this.capacity,this.ammo);

	}

}
