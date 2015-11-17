package edu.umw.cpsc240fall2015team7.zork;
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
		int weaponSpeed = weapon.getSpeed();
		String text = "";
		int npcDammage = npc.getPower();
		int playerDammage = weapon.getPower();
		
		if(npcSpeed == weaponSpeed){
			text = text + npc.takeWound(playerDammage);
			text = text + Player.instance().takeWound(npcDammage);
		}
		else if(npcSpeed > weaponSpeed){
			text = text + npc.takeWound(playerDammage);
			if(npc.isAlive()){
				text += Player.instance().takeWound(npcDammage);
			}
		}
		else{
			text += Player.instance().takeWound(npcDammage);
			text += npc.takeWound(playerDammage);
		}
		return text;
	}
}

