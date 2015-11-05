package edu.umw.cpsc240fall2015team7.zork;
/**
  *Changes the score. Takes an int and changes the players score by that ammount.
  *@author Nathanael Woodhead
  */
class ScoreEvent extends Event{
	private int score;

	/**
	*Constructs this ScoreEvent with passed int.
	*@param score the number that the score should be changed by, this can be positive or negative.
	*@author Carson Meadows and Nathanael Woodhead
	*/
	ScoreEvent(int score){
		this.score=score;
	}
	/**
	*Adds this Event's score to the overall in-game score.
	*@author Carson Meadows
	*/
	String execute() {
		return "";
	}
}
