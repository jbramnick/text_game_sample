package edu.umw.nwoodhea.bork;
import java.lang.IllegalArgumentException;
class CommandFactory{


	public static CommandFactory theInstance;

	public static CommandFactory instance(){
		if(theInstance == null){
			theInstance = new CommandFactory();
		}
		return theInstance;

	}
	private CommandFactory(){
	}
	Command parse(String commandString){
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
		else{
			throw new IllegalArgumentException("Illegal input");
		} 
	}
}
