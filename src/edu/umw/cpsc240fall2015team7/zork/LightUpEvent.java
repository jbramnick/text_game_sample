package edu.umw.cpsc240fall2015team7.zork;
/**
*The event that toggles the lights.
*@author Jim Bramnick and Nathanael Woodhead
*/
class LightUpEvent extends Event
{
	/**
	*Create new LightUpEvent object.
	*@author Jim Bramnick
	*/
	public LightUpEvent(){
	}
	public LightUpEvent(Object i){
	}
	/**
	*Executes this Event to change the light of the current room.
	*@author Jim Bramnick
	*/
	public String execute(){
		Player.instance().getCurrentRoom().changeLight(true);
		return "\n";
	}

}
