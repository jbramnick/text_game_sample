package edu.umw.cpsc240fall2015team7.zork;
import java.util.ArrayList;
/**
*Command to use a Gun against an NPC.
*@author Carson Meadows
*/
class ShootCommand extends Command {
        private Gun weapon;
	private String commandString;
	private Npc npc;

        /**
        *Constructs this ShootCommand.
        *@author Carson Meadows
        */
        public ShootCommand (String commandString) {
		this.commandString=commandString;
        }
        /**
        *Executes this and depletes the Npc's health and the Gun's ammo. 
	*Determines the correct amount of health to deplete based on the Gun's power.
        *@author Carson Meadows
        */
        public String execute () {
		if(commandString.equals("shoot")){
			return"Shoot what?\n";
		}
		String[] commandList = commandString.split(" ");
		if(commandString.contains("with")){
			String with = "with";
			int index = -1;
			for(int i = 0; i < commandList.length; i++){
				if(commandList[i].equals("with")){
					index = i;
					break;
				}
			}
			try{
				this.weapon  = (Gun) Player.instance().getItemInInventoryNamed(commandList[index+1]);
			}catch(Item.NoItemException e){
				return"you do not have that \n";
			}
			catch(Exception e){return "Could not understand you \n";}
		}
		else{
			try{
			this.weapon = Player.instance().getGun();
			}catch(Player.NoGunException e){
				return "You do not have a gun.\n";
			}
		}
		ArrayList<Npc> targets = Player.instance().getCurrentRoom().getInhabitants();
		if(targets.size()==0){
			return "There is nothing to shoot here.\n";
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

