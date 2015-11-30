package edu.umw.cpsc240fall2015team7.zork;
/**
  *Ends the game and returns a win message and the score.
  *@author Nathanael Woodhead
  */
class WinEvent extends Event{
	private int score;
	private Object i;
	
	/**
	  *Constructs this WinEvent object.
	  *@author Nathanael Woodhead
	  */
	WinEvent(Object i){
		this.i=i;
	}
	WinEvent(){
		this.score=Player.instance().getScore();
	}
	/**
	  *Ends the game and returns a win message.
	  *@author Nathanael Woodhead
	  */
	String execute(){
		String message = "You win!\nScore: ";
		this.score=Player.instance().getScore();
		message+=Integer.toString(score);
		message+="\n"+Player.instance().getScoreMessage();
		System.out.println(message);
		System.exit(0);
		return "";
	}
}

