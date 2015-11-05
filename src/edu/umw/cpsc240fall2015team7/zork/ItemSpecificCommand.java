package edu.umw.cpsc240fall2015team7.zork;
/**
  *Command that allows the player to interact with Items.
  *@author Nathanael Woodhead
  */
class ItemSpecificCommand extends Command{
	private String verb;
	private String noun;
	/**
	  *Creates a new ItemSpecificCommand object taking a verb and a noun.
	  *@param verb Action to do to or with the noun
	  *@param noun The name of the item.
	  *@author Nathanael Woodhead
	  */
	ItemSpecificCommand(String verb, String noun){
		this.verb = verb;
		this.noun = noun;
	}
	/**
	  *Executes the command and returns the text associated with the verb. If the verb has events associated with it
	  *it will create the Event objects and execute them.
	  *@throws Item.NoItemException when you do not have the specified item.
	  *@throws Item.NoVerbException When the given noun does not have the specified verb.
	  *@author Nathanael Woodhead
	  */
	String execute(){
		try{
			Item item = GameState.instance().getItemFromInventoryNamed(noun);
			String text = item.getMessageForVerb(verb);
			return text;
		}
		catch(Item.NoItemException e){
			return "You don't have a: " + noun;
		}
		catch(Item.NoVerbException e){
			return  "You can't "+ verb + " a " + noun;
		}
	}
}
		
