package edu.umw.cpsc240fall2015team7.zork;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
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
	 *@author Carson Meadows
	 */
	public Gun (Scanner scanner) throws Item.NoItemException,Dungeon.IllegalDungeonFormatException{
		super(scanner);
		this.capacity = Integer.parseInt(scanner.nextLine());
		this.ammoType=scanner.nextLine();
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
	public Gun(Weapon i,int capacity,int ammo,String ammoType)
	{
		this.primaryName=i.primaryName;
		this.actions=i.actions;
		this.secondaryNames=i.secondaryNames;
		this.weight=i.weight;
		this.messages=i.messages;
		this.speed=i.speed;
		this.power=i.power;
		this.capacity=capacity;
		this.ammo=ammo;
		this.ammoType=ammoType;
	}
	/*
	public Gun(String primaryName,Hashtable<String,ArrayList<Event>> actions,ArrayList<String> secondaryNames,int weight,Hashtable<String,String> messages,int speed,int power,int capacity,int ammo,String ammoType)
	{
		super(primaryName,actions,secondaryNames,weight,messages,speed,power);
		this.capacity=capacity;
		this.ammo=ammo;
		this.ammoType=ammoType;
	}
	*/
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
	public void setAmmo(int ammo)
	{
		this.ammo=ammo;
	}
	/**
	 *Reduces this Gun's ammo by 1 unit.
	 *@author Carson Meadows
	 */

	public void decay() {
		this.ammo--;
	}
	public Gun clone()
	{
		return new Gun(super.clone(),this.capacity,this.ammo,this.ammoType);
	}
	/**
	  Stroes the state of this Gun according to the zork sav format
	  @author Jim Bramnick
	 */
	public String storeState()
	{
		return super.storeState()+":"+ammo;
	}
	public static Gun restore(String save)
	{
		String ammoString=save.split(":")[1];
		Gun g=(Gun)GameState.instance().getDungeon().getItem(save.split(":")[0]);
		g.setAmmo(Integer.parseInt(ammoString));
		return g;
	}
	String type(){
		return"Gun";
	}



}
