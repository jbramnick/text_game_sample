package edu.umw.cpsc240fall2015team7.zork;
/**
  *Totals up the score and ends the game.
  *@author Nathanael Woodhead
  */
class DieEvent extends Event{
	/**
	  *Creates a new DieEvent object.
	  *@author Nathanael Woodhead
	  */
	DieEvent(){
	}
	DieEvent(Item i){
	}
	/**
	  *Returns the ending score and a "You lose" message.
	  *@author Nathanael Woodhead
	  */
	String execute(){
		String message = "You died! Game Over!\nScore: ";
		int score = Player.instance().getScore();
		message += Integer.toString(score);
		System.out.println(message);
		System.exit(0);
		return "";
	}
}
