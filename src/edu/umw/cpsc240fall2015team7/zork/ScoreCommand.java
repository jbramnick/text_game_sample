package edu.umw.cpsc240fall2015team7.zork;
class ScoreCommand extends Command{
	private String commandString;
	/**
	  *Creates a new ScoreCommand object.
	  *@author Nathanael Woodhead
	  */
	ScoreCommand(){}
	/**
	  *Checks the Player's current score and returns a message containing it. 
	  *@author Nathanael Woodhead
	  */
	String execute(){
		String text = "";
		int score = 0;
		score = Player.instance().getScore();
		if(score>=50 && score<100)
		{
			text+="You are a dececnt adventurer.\n";

		}
		if(score>=100)
		{
			text+="You are an awesome adventurer!!!\n";

		}

		text += "You have " + score + " points.";
		return text + "\n";
	}
}
