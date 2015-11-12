package edu.umw.cpsc240fall2015team7.zork;
/**
  *Ends the game and returns a win message and the score.
  *@author Nathanael Woodhead
  */
class WinEvent extends Event{
	private int score;
	
	/**
	  *Constructs this WinEvent object.
	  *@author Nathanael Woodhead
	  */
	WinEvent(){
		this.score=Player.instance().getScore();
	}
	/**
	  *Ends the game and returns a win message.
	  *@author Nathanael Woodhead
	  */
	String execute(){
		String message = "You win! Game Complete!\nScore: ";
		message+=Integer.toString(score);
		return message;
	}
}

