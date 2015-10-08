package edu.umw.nwoodhea.bork;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;
class CommandFactory{


	public static CommandFactory theInstance;

	public static CommandFactory instance(){
		if(theInstance == null){
			theInstance = new CommandFactory();
		}
		return theInstance;

	}
	private CommandFactory() throws Item.NoItemException{
	}
	Command parse(String commandString){
		ArrayList<String> verbs = GameState.instance().getVerbs();
		String[] command = commandString.split(" ");
		if(commandString.equals("u")||commandString.equals("d")||commandString.equals("n")||commandString.equals("s")||commandString.equals("e")||commandString.equals("w")){
			MovementCommand move = new MovementCommand(commandString);
			return move;
		}
		else if(commandString.equals("save")){
			SaveCommand save = new SaveCommand(commandString);
			return save;
		}
		else if(commandString.contains("take")){
			String item = commandString.substring(5,commandString.length());
			TakeCommand take = new TakeCommand(item);
			return take;
		}
		else if(commandString.equals("i")||commandString.equals("inventory")){
			InventoryCommand i = new InventoryCommand();
			return i;
		}
		else if(commandString.contains("drop")){
			try{
				DropCommand drop = new DropCommand(commandString);
				return drop;
			}
		}
		else if(verbs.contains(command[0])){
			String verb = command[0];
			String noun = command[1];
			ItemSpecificCommand action = new ItemSpecificCommand(verb,noun);
			return action;
		}
		else{
			throw new IllegalArgumentException("Illegal input");
		} 
	}
}
