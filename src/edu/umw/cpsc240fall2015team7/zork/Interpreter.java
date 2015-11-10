package edu.umw.cpsc240fall2015team7.zork;
import java.util.Scanner;
import java.io.*;
/**
 *The main for the Zork program. This is a command line game that is controlled from this class. 
 *@author Nathanael Woodhead
 */
class Interpreter {
	public static void main (String args[]){
		if(args.length == 0){
			System.out.println("You must enter a .zork file or a .sav file");
			System.exit(0);
		}
		String input;
		BufferedReader buffy = new BufferedReader(new InputStreamReader(System.in));
		Dungeon game = null;
		input = args[0];
		int dot = input.indexOf(".");
		int length = input.length();
		String extention = input.substring(dot, length);
		if(extention.equals(".sav")){
			try{
				GameState.instance().restore(args[0]);
				game = GameState.instance().getDungeon();
				System.out.println(Player.instance().getCurrentRoom().describe());
			}
			catch(Dungeon.IllegalDungeonFormatException e){
				System.out.println("Invalid .zork format.");
				System.exit(0);
			}
			catch(GameState.IllegalSaveFormatException e){
				System.out.println("Illegal Save format");
				System.exit(0);
			}
			catch(FileNotFoundException e){
				System.out.println("File not found");
				System.exit(0);
			}			
		}

		else if(extention.equals(".zork")){			
			try{
				game = new Dungeon(args[0],true); 
				GameState.instance().initialize(game);
				Player.instance().setCurrentRoom(game.getEntry());
				System.out.println(game.getEntry().describe());

			}
			catch (Dungeon.IllegalDungeonFormatException e){
				System.out.println("Invalid .zork format.");
				System.exit(0);
			}
		}
		input = promptUser(buffy); 
		while(!input.equals("q")){
			try{
				Command move = CommandFactory.instance().parse(input);
				System.out.println(move.execute());
				System.out.println(Player.instance().getCurrentRoom().describe());
			}

			catch(java.lang.IllegalArgumentException e){
				System.out.println("I cannot understand what you said.");
			}
			catch(Item.NoItemException e){
				System.out.println("What item?");
			}
			input = promptUser(buffy); 
		}

	}
	/**
	 *Returns the String inputed by the user.
	 *@author Nathanael Woodhead
	 *@param commandLine The BufferedReader object to read input from user. 
	 */
	private static String promptUser(BufferedReader commandLine){
		String out = "";
		try{

			out= commandLine.readLine();
		}
		catch(Exception e){
			System.out.println("Error");
		}
		return out;
	}
}
