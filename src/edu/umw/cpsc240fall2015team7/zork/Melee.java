package edu.umw.cpsc240fall2015team7.zork;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.ArrayList;
/**
  *A close range weapon.
  *@author Nathanael Woodhead
  */
public class Melee extends Weapon{
	private int speed;
	private int power;
	/**
	  *Creates a new Melee object from a scanner. A Melee weapon is a weapon that hits at close range. This allows opponents to 
	  *hit back at the same time.
	  *@param power The amount of damage done by the weapon with one hit.
	  */
	public Melee(Scanner scanner) throws Item.NoItemException,Dungeon.IllegalDungeonFormatException{
		super(scanner);
		String current = scanner.nextLine();
		this.speed = Integer.parseInt(current);
		current = scanner.nextLine();
		this.power = Integer.parseInt(current);
	}
	/**
	*Constructs this with parameters.
	*@author Carson Meadows
	*/
	public Melee(String primaryName,Hashtable<String,ArrayList<Event>> actions,ArrayList<String> secondaryNames,int weight,Hashtable<String,String> messages,int speed,int power){
	       super(primaryName,actions,secondaryNames,weight,messages,power,speed);
	       
	}
	/**
	*Constructs and returns a copy of this object.
	*@author Carson Meadows
	*/
	public Melee clone(){
		return new Melee(this.primaryName,this.actions,this.secondaryNames,
			this.weight,this.messages,this.speed,this.power);
	}
	/**
	  *Returns the power of this.
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
	*Returns the weight of this.
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
