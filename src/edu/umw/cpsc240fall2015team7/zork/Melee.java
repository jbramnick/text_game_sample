package edu.umw.cpsc240fall2015team7.zork;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.ArrayList;
/**
  *A close range weapon.
  *@author Nathanael Woodhead
  */
public class Melee extends Weapon{
	/**
	  *Creates a new Melee object from a scanner. A Melee weapon is a weapon that hits at close range. This allows opponents to 
	  *hit back at the same time.
	  *@param power The amount of damage done by the weapon with one hit.
	  */
	public Melee(Scanner scanner) throws Item.NoItemException,Dungeon.IllegalDungeonFormatException{
		super(scanner);
		super.power = Integer.parseInt(scanner.nextLine());
		super.speed = Integer.parseInt(scanner.nextLine());
		}
	public Melee(String primaryName, Hashtable<String, ArrayList<Event>> actions, ArrayList<String> secondaryNames,int weight, Hashtable<String,String> messages,int speed, int power){
	       super(primaryName,actions,secondaryNames,weight,messages);
	       super.power= power;
	       super.speed = speed;
	}
	public Melee clone(){
		return new Melee(primaryName,actions,secondaryNames,weight, messages, speed, power);
	}
	/**
	  *Returns the power.
	  *@author Nathanael Woodhead
	  */
	public int getPower(){
		return power;
	}
	/**
	*Returns the primaryName of this.
	*@author Carson Meadows
	*/
	public String getName(){
		return primaryName;
	}
	/**
	*Returns thew weight of this.
	*@author Carson Meadows
	*/
	public int getWeight(){
		return weight;
	}
	/**
	*Returns the speed of this.
	*@author Carson Meadows
	*/
	public int getSpeed(){
		return speed;
	}
}
