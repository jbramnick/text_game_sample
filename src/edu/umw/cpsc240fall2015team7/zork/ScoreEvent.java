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
	ScoreEvent(Iteme i,String score){
		this.score=Integer.getVal(score);
	}

	/**
	 *Adds this Event's score to the overall in-game score. If the score is negative will subtract from the player's score. 
	 *@return A message concerning the score added or subtracted. If score is 0 then will return an empty string. 
	 *@author Carson Meadows and Nathanael Woodhead
	 */
	String execute() {
		String text = "";
		Player.instance().changeScore(score);
		if(score < 0){
			text = "You have lost " +  score + " points!";
		}
		else if(score > 0){
			text = "You have gained " + score + "points!";
		}
		return text;
	}
}
