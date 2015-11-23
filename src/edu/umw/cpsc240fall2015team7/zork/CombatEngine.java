package edu.umw.cpsc240fall2015team7.zork;
import java.util.ArrayList;
import java.lang.reflect.*;
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
		if(weapon!=null)
		{
			if(weapon instanceof Gun)
			{
				Gun g=(Gun)weapon;
				if(g.getAmmo()<=0)
				{

					PassTimeEvent e=new PassTimeEvent(null,"1");
					e.execute();
					return "OUT OF AMMO!!!!";
				}

			}

		}
		String text = "";
		int npcDamage = npc.getPower();
		npc.setAggressive(true);
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
		if(weapon!=null)
			weapon.decay();
		return text;
	}
}
