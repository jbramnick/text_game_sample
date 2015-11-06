package edu.umw.cpsc240fall2015team7.zork;
/**
  *An Item that runs on batteries. When used the batteries are reduced.
  *@author Nathanael Woodhead
  */
public class FlashLight extends Item{
	private int batteryLvl;
	static class NoPowerException extends Exception {}

	/**
	  *Makes a new FlashLight object. 
	  *@param batteryLvl Initial battery level.
	  *@param primaryName The name of the object.
	  *@param weight The weight of the object.
	  *@author Nathanael Woodhead
	  */
	public FlashLight(int batteryLvl, String primaryName, int weight){
	}
	/**
	  *Returns the current battery level.
	  *@author Nathanael Woodhead
	  */
	public int getBatteryLvl(){
		return 0;
	}
	/**
	  *Restores the battery to 100.
	  */
	public void charge(){
	}
	/**
	  *Reduces the battery.
	  *@author Nathanael Woodhead
	  *@throws NoPowerException If the battery level is 0.
	  */
	public void use(){
	}
}
