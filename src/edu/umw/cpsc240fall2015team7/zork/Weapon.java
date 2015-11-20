package edu.umw.cpsc240fall2015team7.zork;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;
/**
*Abstract class which all weapons extend from.
*@author Carson Meadows
*/
class Weapon extends Item{
	static class NoWeaponException extends Exception{}
	protected int  speed, power;
	/**
	Constructs an instance of this using the Items superclass constructer.
	@author Jim Bramnick
	*/
	public Weapon(Scanner scan) throws Item.NoItemException, Dungeon.IllegalDungeonFormatException
	{
		super(scan);
		String current=scan.nextLine();
		this.speed=Integer.parseInt(current);
		current=scan.nextLine();
		this.power=Integer.parseInt(current);
		current=scan.nextLine();
	}
	public Weapon(Item i,int speed,int power)
	{
		this.primaryName=i.primaryName;
		//test
		//System.out.println(primaryName);
		this.actions=i.actions;
		this.secondaryNames=i.secondaryNames;
		this.weight=i.weight;
		this.messages=i.messages;
		this.speed=speed;
		this.power=power;
	}
	/*
	public Weapon(String primaryName,Hashtable<String,ArrayList<Event>> actions,ArrayList<String> secondaryNames,int weight,Hashtable<String,String> messages,int speed,int power)
	{
		super(primaryName,actions,secondaryNames,weight,messages);
		this.speed=speed;
		this.power=power;
	}
*/
	public Weapon(){}
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
	  Returns a boolean true if this goes by the string passed.
	  @author Nathanael Woodhead
	 */
	boolean goesBy(String name){
		if(primaryName.equals(name)){
			return true;
		}
		else if(secondaryNames.contains(name)){
			return true;
		}	
		else{
			return false;
		}
	}
	/**
	  Returns PrimaryName
	  @author Jim Bramnick
	 */
	public String toString(){
		return primaryName;	
	}
	/**
	 *Returns the name of this.
	 *@author Carson Meadows
	 */
	public String getName() {
		return primaryName;
	}
	/**
	  Returns the power for this weapon.
	  @author Nathanael Woodhead
	 */
	public int getPower() {
		return power;
	}
	/**
	  Returns an ArrayList consisting of the secondary names for this Weapon.
	  @author Nathanael Woodhead
	 */
	ArrayList<String> getSecondaryNames(){
		return secondaryNames;
	}
	public Weapon clone()
	{
		Weapon wep=new Weapon(super.clone(),this.speed,this.power);
		//System.out.println(wep.getPrimaryName());
		return new Weapon(super.clone(),this.speed,this.power);

	}

}


