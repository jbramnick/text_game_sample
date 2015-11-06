package edu.umw.cpsc240fall2015team7.zork;
/**
*Items used to heal the player when a {@link HealEvent} is executed.
*@author Jim Bramnick
*/
class Medkit extends Item {
        private int uses;

        /**
        *Constructs this Medkit.
        *@author Carson Meadows
        */
        public Medkit (int uses) {
        	this.uses=uses;
	}
        /**
        *Adds a use to the Medkit. 
        *@author Jim Bramnick
        */
        public void addUse () {
        }
	/**
	returns uses of this Medkit.
	@author Jim Bramnick
	*/
	public int getUses()
	{

	}
	/**
	Removes a use from the medkit. 
	If uses is less than or equal to one removes Medkit from GameState Inventory.
	@author Jim Bramnick
	*/
	public void disapear()
	{

	}
	/**
	Adds a use to this Medkit If Item exisist in GameState inventory.
	If Medkit does not exisit in GameState Inventory adds Medkit to GameStateInventory.
	@author Jim Bramnick
	*/
	public addToInventory()
	{

	}
	
}


