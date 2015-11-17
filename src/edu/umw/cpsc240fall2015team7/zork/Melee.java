package edu.umw.cpsc240fall2015team7.zork;
/**
  *A close range weapon.
  *@author Nathanael Woodhead
  */
public class Melee extends Weapon{
	private int power, weight,speed;
	private String primaryName;
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
	public String getName(){
		return primaryName;
	}
	public int getWeight(){
		return weight;
	}
	public int getSpeed(){
		return speed;
	}
}
