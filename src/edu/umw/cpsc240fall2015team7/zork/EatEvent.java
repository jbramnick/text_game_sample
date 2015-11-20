package edu.umw.cpsc240fall2015team7.zork;
/**
The Event that handles {@link Player} food.
@author Jim Bramnick
*/
class EatEvent extends Event {
	private int food;
	/**
	Create new EatEvent object and set the amt.
	@author Jim Bramnick
	*/
	public EatEvent(int food) {
	this.food = food;
	}
	/**
	Executes this EatEvent and adds amt to the food in {@link Player}.
	If {@link Player} food is equal to {@link Player} hunger maximum, silently does nothing.
	@return A confirmation message of the format "That Snack was good".
	@author Jim Bramnick
	*/
	public String execute() {
			Player.instance().eat(food);
			return "That was a delicious snack!";
	}

}
