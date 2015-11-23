package edu.umw.cpsc240fall2015team7.zork;
import java.util.Scanner;
/**
  An Item that illuminates dark rooms. They have batteries and can run out of power.
  @author Nathanael Woodhead
  */
class Light extends Item{
	static class NoLightException extends Exception {}
	private int battery;
	private Boolean power;

	public Light(Scanner scanner) throws Item.NoItemException, Dungeon.IllegalDungeonFormatException{
		super(scanner);
		this.battery = 50;
		this.power = false;
	}
	/**
	  Constructor for used when cloning this item. Accessed by the {@link clone()} method.
	  @author Nathanael Woodhead
	 */
	public Light(Item i,int battery,boolean power){
		this.primaryName=i.primaryName;
		this.actions=i.actions;
		this.secondaryNames=i.secondaryNames;
		this.weight=i.weight;
		this.messages=i.messages;
		this.battery = battery;
		this.power=power;
	}
	/**
	  Returns an Identical Light object.
	  @author Nathanael Woodhead
	 */
	public Light clone(){
		return new Light(super.clone(),this.battery,this.power);
	}
	String togglePower(){
		if(battery>0)
		{
			if(this.power == false){
				this.power = !power;
				return "You turn the light on.";
			}
			else{
				this.power = !power;
				return "You turn the light off.";
			}

		}
		else
		{
			this.power=false;
			return "This "+this.primaryName+" is out of battery.";

		}

	}
	public void setPower(boolean power)
	{
		this.power=power;

	}
	public int getBattery()
	{
		return this.battery;
	}
	public void setBattery(int battery)
	{
		this.battery=battery;

	}
	public void decay()
	{
		if(power)
			battery--;
		if(battery<0)
			this.power=false;
	}
	boolean getPower(){
		return this.power;
	}
	public String storeState()
	{
		return this.primaryName+":"+battery+"/"+power;

	}
	public static Light restore(String save)
	{
		String value=save.split(":")[1];
		Light light=(Light)GameState.instance().getDungeon().getItem(save.split(":")[0]);
		String[] values=value.split("/");
		light.setBattery(Integer.parseInt(values[0]));
		light.setPower(Boolean.valueOf(values[1]));
		return light;

	}
}
