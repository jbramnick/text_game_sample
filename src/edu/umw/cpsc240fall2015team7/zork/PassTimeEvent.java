package edu.umw.cpsc240fall2015team7.zork;

class PassTimeEvent extends Event
{
	private int turns;
	public PassTimeEvent(Item i,String turns)
	{
		this.turns=Integer.parseInt(turns);

	}
	public String execute()
	{
		String text="";
		if(turns>0)
		{
			for(int i=0;i<turns;i++)
			{
				text+=Player.instance().hunger();
			}

		}
		return text;

	}

}
