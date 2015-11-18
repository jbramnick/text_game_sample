package edu.umw.cpsc240fall2015team7.zork;
/**
The Command that handles {@link Player} hunger.
@author Jim Bramnick
*/
class EatCommand extends Command {
	/**
	Create new EatCommand object and set the amt.
	@author Jim Bramnick
	*/
	public EatCommand() {
	}
	/**
	Executes this EatCommand and adds amt to the hunger in {@link Player}.
	If {@link Player} hunger is equal to {@link Player} hunger maximum, silently does nothing.
	@return A confirmation message of the format "That Snack was good".
	@author Jim Bramnick
	*/
	public String execute() {
		try
		{
			Player.instance().eat();
			return "That was a delicious snack!";
		}
		catch(Exception e)
		{
			return "No Snacks to consume!!!";
		}
	}

}