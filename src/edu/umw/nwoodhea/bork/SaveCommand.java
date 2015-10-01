package edu.umw.nwoodhea.bork;
class SaveCommand extends Command{
	private String saveFilename;
	
	SaveCommand(String f){
		this.saveFilename = f;
		}
	String execute(){
		GameState.instance().store();
		return "Game Saved!";
		}
	}
