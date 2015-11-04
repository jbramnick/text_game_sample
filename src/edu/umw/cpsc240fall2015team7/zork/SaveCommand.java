/**
*Command that saves the current game status
*@author Carson Meadows
*/
package edu.umw.cpsc240fall2015team7.zork;
class SaveCommand extends Command{
	private String saveFilename;
	
	/**
	*Constructs this SaveCommand.
	*@author Carson Meadows
	*/
	SaveCommand(String f){
		this.saveFilename = f;
	}
	/**
	*Saves current game status. Returns confirmation String.
	*@author Carson Meadows
	*/
	String execute(){
		GameState.instance().store();
		return "Game Saved!";
	}
}
