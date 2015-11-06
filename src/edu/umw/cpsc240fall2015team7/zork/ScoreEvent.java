package edu.umw.cpsc240fall2015team7.zork;
/**
  *Changes the player's total score by the amount passed.
  *@author Nathanael Woodhead
  */
class ScoreEvent extends Event{
	private int score;

	/**
	*Constructs this ScoreEvent with passed int.
	*@param score The number that the total score should be changed by. Can be positive or negative.
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
