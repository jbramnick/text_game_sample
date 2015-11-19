package edu.umw.cpsc240fall2015team7.zork;
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
	public Melee(Scanner scanner) thows Item.NoItemException{
		Super(scanner);
		super.power = Integer.parseInt(scanner.nextLine());
		super.speed = Integer.parseInt(scanner.nextLine());
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
		super.power = 25;
		super.primaryName  = primaryName;
	       	super.weight = weight;
		super.speed = speed;
	}
	

	/**
	  *Returns the power.
	  *@author Nathanael Woodhead
	  */
	/*
	public int getPower(){
		return power;
	}
	*/
	/**
	*Returns the primaryName of this.
	*@author Carson Meadows
	*/
	/*
	public String getName(){
		return primaryName;
	}
	*/
	/**
	*Returns thew weight of this.
	*@author Carson Meadows
	*/
	/*
	public int getWeight(){
		return weight;
	}
	*/
	/**
	*Returns the speed of this.
	*@author Carson Meadows
	*/
	/*
	public int getSpeed(){
		return speed;
	}
	*/
}
