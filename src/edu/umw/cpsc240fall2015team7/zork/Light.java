package edu.umw.cpsp240fall2015team7.zork;
import java.util.Scanner;
/**
  An Item that iluminates dark rooms. They have batteries and can run out of power.
  @author Nathanael Woodhaed
  */
Class Light extends Item{
	private int battery;
	private boolean power;
	Light(Scanner scanner){
		super(scanner);
		this.battery = 100;
		this.power = false;

	}
	Light(Item i, int battery, boolean power){
		this.primaryName = i.primaryName;

	Light clone(){
		return new Light(super.clone(),this.battery,this,power);
	}
}
