package edu.umw.cpsc240fall2015team7.zork;
import java.util.ArrayList;
/**
*Abstract class which all weapons extend from.
*@author Carson Meadows
*/
abstract class Weapon{
	class NoWeaponException extends Exception{}
	protected String primaryName;
	protected ArrayList<String> secondaryNames = new ArrayList <String>();
	protected int weight, speed, power;

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
		for(String x : secondaryNames){
			if(x.equals(name)){
				return true;
			}
		}
		return false;
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

}

	
