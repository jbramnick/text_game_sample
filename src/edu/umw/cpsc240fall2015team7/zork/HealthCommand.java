package edu.umw.cpsc240fall2015team7.zork;
class HealthCommand extends Command{
	HealthCommand(){}

	String execute(){
		String text = "";
		int health = Player.instance().getHealth();
		if(health == 100){
			text = "You couldn't feel better!";
		}	
		return text + "\n";
	}
}
