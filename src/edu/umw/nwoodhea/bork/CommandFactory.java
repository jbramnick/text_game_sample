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
		Command move = new Command(commandString);
		return move;
}
	else{
		throw new IllegalArgumentException("Illegal input");
} 
}
}
