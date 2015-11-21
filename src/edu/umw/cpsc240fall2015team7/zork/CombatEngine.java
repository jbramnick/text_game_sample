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
		if(npcSpeed == playerSpeed){
			text = text + npc.takeWound(playerDamage);
			text = text + Player.instance().takeWound(npcDamage,npc.getPrimaryName()+ " hit you!!!");
		}
		else if(npcSpeed > playerSpeed){
			text = text + npc.takeWound(playerDamage);
			if(npc.isAlive()){
				text = text + Player.instance().takeWound(npcDamage,npc.getPrimaryName()+ " hit you!!!");
			}
		}
		else{
			text = text + Player.instance().takeWound(npcDamage,npc.getPrimaryName()+ " hit you!!!");
			text += npc.takeWound(playerDamage);
		}
		return text;
	}
}

