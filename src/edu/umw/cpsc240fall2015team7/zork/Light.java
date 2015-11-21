package edu.umw.cpsc240fall2015team7.zork;
import java.util.Scanner;
/**
  An Item that illuminates dark rooms. They have batteries and can run out of power.
  @author Nathanael Woodhead
  */
class Light extends Item{
	private int battery;
	private boolean power;
	public Light(Scanner scanner) throws Item.NoItemException, Dungeon.IllegalDungeonFormatException{
		super(scanner);
		this.battery = 100;
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
		if(power == false){
			power = true;
			return "You turn the light on.";
		}
		else{
			power = false;
			return "You turn the light off.";
		}
	}
}
