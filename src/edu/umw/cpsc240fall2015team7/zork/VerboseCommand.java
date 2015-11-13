package edu.umw.cpsc240fall2015team7.zork;
/**
  *A Command object used to change the Verbose setting.
  *@author Nathanael Woodhead
  */
public class VerboseCommand extends Command{
	 
	 /**
	   *Constructs this VerboseCommand object.
	   *@author Nathanael Woodhead
	   */
	 public VerboseCommand(){
	 }
	 /**
	   *Changes the verbose value in GameState. If the  current value is true then it will make it false. 
	   *If the current value is false then it will change it to true. 
	   *@return Returns a confirmation message.
	   *@author Nathanael Woodhead
	   */
	 public String execute(){
		if (GameState.instance().getVerbose()==true) {
			GameState.instance().setVerbose(false);
			return "Verbose Off";
		} else {
			GameState.instance().setVerbose(true);
		 	return "Verbose On";
		}
	 }
}
