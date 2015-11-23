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
	  *@param verb Action to do to or with the noun.
	  *@param noun The name of the item.
	  *@author Nathanael Woodhead
	  */
	ItemSpecificCommand(String verb, String noun){
		this.verb = verb;
		this.noun = noun;
	}
	/**
	  *Executes this command and returns the text associated with the verb. If the verb has Events associated with it, 
	  *it will create the Event objects and execute them.
	  *catches Item.NoItemException when you do not have the specified item.
	  *catches Item.NoVerbException When the given noun does not have the specified verb.
	  *@author Nathanael Woodhead
	  */
	String execute(){
		try{
			Item item = Player.instance().getItemInInventoryNamed(noun);
			System.out.println(item.getMessageForVerb(verb));
			String text="";
			text+=item.executeEventsFor(verb);
			PassTimeEvent e=new PassTimeEvent(null,"1");
			e.execute();
			return text;
		}
		catch(Item.NoItemException e){
			try 
			{
				Item i=Player.instance().getCurrentRoom().getItemNamed(noun);
				System.out.println(i.getMessageForVerb(verb));
				String t="";
				t+=i.executeEventsFor(verb);
				PassTimeEvent ex=new PassTimeEvent(null,"1");
				ex.execute();
				return t;

			}
			catch(Item.NoItemException ex)
			{
				return "There is not a "+ noun + " in the room or your inventory";

			}
			catch(Item.NoVerbException ex){
				return  "You can't "+ verb + " a " + noun;
			}
		}
	catch(Item.NoVerbException e){
			return  "You can't "+ verb + " a " + noun;
		}
	}
}

