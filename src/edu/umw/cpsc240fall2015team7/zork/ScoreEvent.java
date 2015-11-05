/**
  *Changes the score. Takes an int and changes the players score by that ammount.
  *@author Nathanael Woodhead
  */
package edu.umw.cpsc240fall2015team7.zork;
class ScoreEvent{
	private int score;

	/**
	*Constructs this ScoreEvent with passed int.
	*@author Carson Meadows
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
