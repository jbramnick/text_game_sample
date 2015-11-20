package edu.umw.cpsc240fall2015team7.zork;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;
/**
*A singleton class that parses input String and returns a Command object. 
*@author Nathanael Woodhead
*/
class CommandFactory{

	
	public static CommandFactory theInstance;
	/**
	*Creates a new CommandFactory object. If a CommandFactory object already exists then returns that.
	*@author Carson Meadows 
	*/
	public static CommandFactory instance(){
		if(theInstance == null){
			theInstance = new CommandFactory();
		}
		return theInstance;

	}
	/**
	*Creates a new CommandFactory object.
	*@author Carson Meadows
	*/
	private CommandFactory(){
	}
	/**
	*Reads passed String and returns a new Command of the correct type. 
	*@throws  IllegalArgumentException If passed String does not refer to any Command type.
	*@author Carson Meadows
	*/
	Command parse(String commandString)throws Item.NoItemException{
		ArrayList<String> verbs = Player.instance().getVerbs();
		for(String verb : Player.instance().getCurrentRoom().getVerbs()){
			verbs.add(verb);
		}
		String[] command = commandString.split(" ");
		if(commandString.equals("u")||commandString.equals("d")||commandString.equals("n")||commandString.equals("s")||commandString.equals("e")||commandString.equals("w")){
			MovementCommand move = new MovementCommand(commandString);
			return move;
		}
		else if(commandString.equals("health")){
			HealthCommand i = new HealthCommand();
			return i;
		}
		else if (commandString.contains("attack")) {
			AttackCommand a = new AttackCommand(commandString);
			return a;
		}
		else if (commandString.contains("shoot")) {
			ShootCommand s = new ShootCommand(commandString);
			return s;
		}
		else if (commandString.contains("verbose")) {
			VerboseCommand v = new VerboseCommand();
			return v;
		}

		else if(commandString.equals("score")){
			ScoreCommand i = new ScoreCommand();
			return i;
		}

		else if(commandString.equals("save")){
			SaveCommand save = new SaveCommand(commandString);
			return save;
		}
		else if(commandString.contains("take")){
			TakeCommand take = new TakeCommand(commandString);
			return take;
		}
		else if (commandString.contains("reload")) {
			ReloadCommand reload = new ReloadCommand(commandString);
			return reload;
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
				catch(Item.NoItemException e){
					throw new Item.NoItemException();
				}
		}
		else if(verbs.contains(command[0])){
			String verb = command[0];
			String noun = "";
			try{
				noun = command[1];
			}catch(Exception e){
				throw new Item.NoItemException();	
			}
			ItemSpecificCommand action = new ItemSpecificCommand(verb,noun);
			return action;
		}
		else if(commandString.contains("look")){
			LookCommand look = new LookCommand();
			return look;
		}
		else{
			throw new IllegalArgumentException("Illegal input");
		} 
	}
}
