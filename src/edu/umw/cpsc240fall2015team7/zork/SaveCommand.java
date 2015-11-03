package edu.umw.cpsc240fall2015team7.zork;
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
