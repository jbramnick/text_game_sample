package edu.umw.cpsc240fall2015team7.zork;
/**
  *A Command for attacking an NPC.
  *@author Nathanael Woodhead
  */
class AttackCommand extends Command{
	private Melee weapon;
	private String commandString;
	private Npc npc;
	/**
	  *Creates a new AttackCommand object.
	  *@author Nathanael Woodhead
	  */
	AttackCommand(String commandString){
		this.commandString = commandString;
	}
	/**
	  *Parses the commandString and carries out the necessary actions. 
	  *The commandString should be in the format of "Attack Npc with MeleeWeapon". If the commandString does not include a 
	  *MeleeWeapon then this should attack the NPC with the power of the player instead of the weapon. This will result in a message 
	  *being returned saying that the NPC was attacked with fists. If the player does not have the MeleeWeapon in their inventory 
	  *it will return a message stating "You do not have a 'MeleeWeapon'". If the player does have the MeleeWeapon then the 
	  *weapon's power should be used in attacking the NPC and a message will be returned saying "Attacked Npc with 'MeleeWeapon'"
	  *@author Nathanael Woodhead
	  */
	String execute(){
		CombatEngine.instance().fight(weapon, npc);
		return "";
	}
}

