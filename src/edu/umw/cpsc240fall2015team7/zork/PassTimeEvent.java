package edu.umw.cpsc240fall2015team7.zork;
import java.util.ArrayList;

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
				ArrayList<Npc> npcs=GameState.instance().getDungeon().getInPlayNpcs();
				for(Npc npc:npcs)
				{
					npc.attackPlayer();
					npc.move();
				}
				ArrayList<Room> rooms=GameState.instance().getDungeon().getRooms();
				for(Room room:rooms)
				{
					room.reset();
				}
				ArrayList<Item> items=GameState.instance().getDungeon().getInPlayItems();
				for(Item item:items)
				{
					if(!(item instanceof Gun))
						item.decay();
				}
				Spawner.instance().spawn();
			}

		}
		return text;

	}

}
