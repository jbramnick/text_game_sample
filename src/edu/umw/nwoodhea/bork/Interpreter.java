package edu.umw.nwoodhea.bork;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Interpreter {
public static void main (String args[]){
	System.out.println("Welcome to Bork V1");
	String input;
	BufferedReader buffy = new BufferedReader(new InputStreamReader(System.in));
	Dungeon game = null;
	try{
		game = new Dungeon(args[0]); 

}
	catch (Dungeon.IllegalDungeonFormatException e){
		System.out.println("Invalid .bork format.");
		System.exit(0);
}
	GameState.instance().initialize(game);
	GameState.instance().setAdventurersCurrentRoom(game.getEntry());
	System.out.println(game.getEntry().describe());
	input = promptUser(buffy); 
	while(!input.equals("q")){
		try{
		Command move = CommandFactory.instance().parse(input);
		System.out.println(move.execute());
		System.out.println(GameState.instance().getAdventurersCurrentRoom().describe());
}

		catch(java.lang.IllegalArgumentException e){
		System.out.println("I cannot understand what you said.");
}
		input = promptUser(buffy); 
}
}
private static String promptUser(BufferedReader commandLine){
	String output = "";
	String out = "";
	try{
	
	out= commandLine.readLine();
}
	catch(Exception e){
	System.out.println("Error");
}
	output = out;
	return output;
}
}
