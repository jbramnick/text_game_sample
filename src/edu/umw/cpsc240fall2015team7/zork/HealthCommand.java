package edu.umw.cpsc240fall2015team7.zork;
/**
*Returns a message correlating to the Player's health.
*@author Carson Meadows
*/
class HealthCommand extends Command{
	HealthCommand(){}

	String execute(){
		String text = "";
		int health = Player.instance().getHealth();
		if(health == 100){
			text = "You couldn't feel better!";
		} else if (health > 75) {
			text = "You feel fine, but not perfect.";
		} else if (health > 50) {
			text = "You're hurt, but your strong will carries you on!";
		} else if (health > 25) {
			text = "Each step is a painful struggle. You need to heal!";
		} else {
			text = "You are inches from death.";
		}
		return text + "\n";
	}
}
