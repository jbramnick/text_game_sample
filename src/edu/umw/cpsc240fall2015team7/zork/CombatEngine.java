package edu.umw.cpsc240fall2015team7.zork;
import java.util.ArrayList;
class CombatEngine{

	public static CombatEngine theInstance;
	public static CombatEngine instance(){
		if(theInstance == null){
			theInstance = new CombatEngine();
		}
		return theInstance;
	}	
	private CombatEngine(){
	}
	String fight(Npc npc, Weapon weapon){
		int npcSpeed = npc.getSpeed();
		int playerSpeed=0;
		int playerDamage=0;
		if(weapon==null)
		{
			playerSpeed=5;
			playerDamage=10;

		}
		else
		{
			playerSpeed=weapon.getSpeed();
			playerDamage=weapon.getPower();
		}
		String text = "";
		int npcDamage = npc.getPower();
		ArrayList<Npc> npcs=Player.instance().getCurrentRoom().getInhabitants();
		if(npcSpeed == playerSpeed){
			text = text + npc.takeWound(playerDamage);
			for(Npc np:npcs)
				np.attackPlayer();
		}
		else if(npcSpeed > playerSpeed){
			text = text + npc.takeWound(playerDamage);
			for(Npc np:npcs)
			{
				if(np.isAlive()){
					np.attackPlayer();
				}
			}
		}
		else{
			for(Npc np:npcs)
				np.attackPlayer();
			text += npc.takeWound(playerDamage);
		}
		return text;
		}
	}

