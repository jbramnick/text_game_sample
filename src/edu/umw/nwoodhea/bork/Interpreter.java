package edu.umw.nwoodhea.bork;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Interpreter {
	public static void main (String args[]){
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
				System.out.println(GameState.instance().getAdventurersCurrentRoom().describe());
			}
			catch(Dungeon.IllegalDungeonFormatException e){
				System.out.println("Invalid .bork format.");
				System.exit(0);
			}
			catch(GameState.IllegalSaveFormatException e){
				System.out.println("Illegal Save format");
				System.exit(0);
			}
			catch(Exception e){
				System.out.println("Na Na Na you  cant find me");
				System.exit(0);
			}
		}

		else if(extention.equals(".bork")){			
			try{
				game = new Dungeon(args[0]); 
				GameState.instance().initialize(game);
				GameState.instance().setAdventurersCurrentRoom(game.getEntry());
				System.out.println(game.getEntry().describe());

			}
			catch (Dungeon.IllegalDungeonFormatException e){
				System.out.println("Invalid .bork format.");
				System.exit(0);
			}
		}
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
