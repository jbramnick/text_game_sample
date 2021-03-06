package edu.umw.cpsc240fall2015team7.zork;
/**
Event used to set health in {@link Player} to {@link Player} MaxHealth.
@author Jim Bramnick
*/
class HealEvent extends Event
{
	/**
	Creates this HealEvent Object.
	@author Jim Bramnick
	*/
	public HealEvent(){
	}
	public HealEvent(Object i){

	}
	/**
	Executes this HealEvent and sets {@link Player} health to {@link Player} MaxHealth.
	@throws MaxHealthException If {@link Player} health is equal to {@link Player} MaxHealth.
	@author Jim Bramnick
	*/
	public String execute() {
		Player.instance().setHealth(Player.instance().getMaxHealth());
		return "You feel better now.\n";
	}

}
