package edu.umw.cpsc240fall2015team7.zork;
/**
*The event that toggles the lights.
*@author Jim Bramnick and Nathanael Woodhead
*/
class SwitchLightItemEvent extends Event{
	private Light item;
	String lightName;
	/**
	*Create new SwitchLightItemEvent object.
	@param i Should be a Light.
	*@author Nathanael Woodhead 
	*/
	public SwitchLightItemEvent(Object i){
		item = (Light) i;
		lightName=item.getPrimaryName();
	}
	/**
	*Executes this Event to change the light of the current room.
	*@author Jim Bramnick
	*/
	public String execute(){
		try
		{
			item=(Light)Player.instance().getItemInInventoryNamed(lightName);
			String text = item.togglePower();
			return text+"\n";
		}
		catch(Item.NoItemException e)
		{
			return "You do not have a "+lightName+".\n";
		}
	}

}
