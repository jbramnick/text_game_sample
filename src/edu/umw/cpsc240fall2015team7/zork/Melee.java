package edu.umw.cpsc240fall2015team7.zork;
import java.util.Scanner;
import java.util.ArrayList;
/**
  *A close range weapon.
  *@author Nathanael Woodhead
  */
public class Melee extends Weapon{
	private int power, weight,speed;
	private String primaryName;
	/**
	  *Creates a new Melee object from a scanner. A Melee weapon is a weapon that hits at close range. This allows opponents to hit back at the
	  *same time.
	  *@param power The amount of damage done by the weapon with one hit.
	  *@param primaryName The name of the Melee weapon object.
	  *@param weight How much the Melee object weighs.
	  *@param speed Used by the combat engine to determine who hits who first.
	  *@author Nathanael Woodhead
	  */
	public Melee(Scanner scanner) throws Weapon.NoWeaponException{
		String name = scanner.nextLine();
		if(name.equals("===")){
			throw new Weapon.NoWeaponException();
		}
		String[] names = name.split(",");
		this.primaryName = names[0];
		for(String x : names){
			if(!x.equals(primaryName)){
				this.secondaryNames.add(x);
			}
		}
		this.weight = Integer.parseInt(scanner.nextLine());
		this.speed = Integer.parseInt(scanner.nextLine());
		String next = scanner.nextLine();
		while(!next.equals("---")){
			String[] message = next.split(":");
			this.messages.put(message[0],message[1]);
			next = scanner.nextLine();
		}

	}
	/**
	  *Creates a new Melee object. A Melee weapon is a weapon that hits at close range. This allows opponents to hit back at the
	  *same time.
	  *@param power The amount of damage done by the weapon with one hit.
	  *@param primaryName The name of the Melee weapon object.
	  *@param weight How much the Melee object weighs.
	  *@param speed Used by the combat engine to determine who hits who first.
	  *@author Nathanael Woodhead
	  */
	public Melee(int power,String primaryName, int weight, int speed){
		this.power = 25;
		this.primaryName  = primaryName;
	       	this.weight = weight;
		this.speed = speed;
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
