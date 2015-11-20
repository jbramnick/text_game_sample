package edu.umw.cpsc240fall2015team7.zork;
/**
  *An Item that runs on batteries. When used the batteries are reduced.
  *@author Nathanael Woodhead
  */
public class FlashLight extends Item {
	private String primaryName;
	private int weight;
	private int batteryLvl;
	private int max;
	static class NoPowerException extends Exception {}

	/**
	  *Makes a new FlashLight object. 
	  *@param batteryLvl Initial battery level.
	  *@param primaryName The name of the object.
	  *@param weight The weight of the object.
	  *@author Nathanael Woodhead
	  */
	public FlashLight(int max, String primaryName, int weight){
		this.max=max;
		this.batteryLvl=max;
		this.primaryName=primaryName;
		this.weight=weight;
	}
	/**
	  *Returns the current battery level.
	  *@author Nathanael Woodhead
	  */
	public int getBatteryLvl(){
		return batteryLvl;
	}
	/**
	  *Restores the battery to 100.
	  */
	public void charge(){
		batteryLvl=max;
	}
	/**
	  *Reduces the batteryLvl of this and light's up the current Room.
	  *@author Nathanael Woodhead
	  *@throws NoPowerException If the battery level is 0.
	  */
	public void use() throws NoPowerException {
		batteryLvl--;
		if (batteryLvl<1) {
			throw new NoPowerException();
		} else {
			Player.instance().getCurrentRoom().light();
		}
	}
}
