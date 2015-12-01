package edu.umw.cpsc240fall2015team7.zork;
import java.util.ArrayList;
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
		String[] commandList = commandString.split(" ");
		if(commandString.equals("attack")){
			return "Attack what?";
		}
		if(commandString.contains("with")){
			int index = -1;
			for(int i = 0; i<commandList.length; i++){
				if(commandList[i].equals("with")){
					index = i;
					break;
				}
			}
			Item item;
			try{
				item = Player.instance().getItemInInventoryNamed(commandList[index+1]);
			}catch(Item.NoItemException e){
				return "You do not have a " + commandList[index+1] + ".";
			}
			catch(Exception e){return "Could not understand you\n";}
			Class clazz = Melee.class;
			if(clazz.isInstance(item)){
				this.weapon = (Melee) item;
			}
			else{
				return "You cannot attack with a " + commandList[index+1] + ".\n";
			}
		}
		else{
			try{
				this.weapon = Player.instance().getMelee();
			}catch(Player.NoMeleeException e){
				this.weapon = null;
			}
		}
		ArrayList<Npc> targets = Player.instance().getCurrentRoom().getInhabitants();
		if(targets.size()==0){
			return "There is nothing to attack here.\n";
		}
		boolean here = false;
		for(Npc target : targets){
			if(target.goesBy(commandList[1])){
				here = true;
				this.npc = target;
			}
		}
		if(here){}

		else{
			return "There is no "+ commandList[1] + " here.";	
		}
		String text = CombatEngine.instance().fight(npc,weapon);
		return text;
	}
}

