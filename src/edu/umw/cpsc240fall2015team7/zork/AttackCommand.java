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
		if(commandString.equals("attack")){
			return "Attack what?";
		}
		if(commandString.contains("with")){
			String[] commandList = commandString.split(" ");
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
			Class clazz = Melee.class;
			if(clazz.isInstance(item)){
				this.weapon = (Melee) item;
			}
			else{
				return "You cannot attack with a " + commandList[index+1] + ".";
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
			return "There is nothing to shoot here.";
		}
		ArrayList<String> targetNames = new ArrayList<String>();
		for(Npc target : targets){
			targetNames.add(target.getPrimaryName());
		}
		for(String target : targetNames){
			if(commandString.contains(target)){
				this.npc = targets.get(targetNames.indexOf(target));
			}
			else{
				this.npc = targets.get(0);
			}
		}
		String text = CombatEngine.instance().fight(npc,weapon);
        	return text;
	}
}

