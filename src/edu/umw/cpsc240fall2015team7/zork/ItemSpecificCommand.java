package edu.umw.cpsc240fall2015team7.zork;;
class ItemSpecificCommand extends Command{
	private String verb;
	private String noun;
	ItemSpecificCommand(String verb, String noun){
		this.verb = verb;
		this.noun = noun;
	}
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
		
