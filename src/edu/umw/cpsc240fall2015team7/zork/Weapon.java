package edu.umw.cpsc240fall2015team7.zork;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;
/**
*Abstract class which all weapons extend from.
*@author Carson Meadows
*/
abstract class Weapon{
	static class NoWeaponException extends Exception{}
	protected String primaryName;
	protected Hashtable<String, String> messages = new Hashtable<String, String>();
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

}


