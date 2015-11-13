package edu.umw.cpsc240fall2015team7.zork;
/**
  *A close range weapon.
  *@author Nathanael Woodhead
  */
public class Melee{
	private int power, weight;
	private String primaryName;
	/**
	  *Creates a new Melee object. A Melee weapon is a weapon that hits at close range. This allows opponents to hit back at the
	  *same time.
	  *@param power The amount of damage done by the weapon with one hit.
	  *@param primaryName The name of the Melee weapon object.
	  *@param weight How much the Melee object weighs.
	  *@author Nathanael Woodhead
	  */
	public Melee(int power,String primaryName, int weight){
		this.power = 25;
		this.primaryName  = primaryName;
	       	this.weight = weight;
	}
	/**
	  *Returns the power.
	  *@author Nathanael Woodhead
	  */
	public int getPower(){
		return power;
	}
}
