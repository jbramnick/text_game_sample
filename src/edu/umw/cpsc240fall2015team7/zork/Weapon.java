package edu.umw.cpsc240fall2015team7.zork;

/**
*Abstract class which all weapons extend from.
*@author Carson Meadows
*/
abstract class Weapon{
	String primaryName;
	int power,weight, speed;
	int getSpeed(){
		return speed;
	}
	int getPower(){
		return power;
	}
}

	
