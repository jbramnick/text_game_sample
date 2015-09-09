package edu.umw.nwoodhea.bork;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Interpreter {
public static void main (String args[]){
	String input;
	BufferedReader buffy = new BufferedReader(new InputStreamReader(System.in));
	Dungeon game = buildSampleDungeon();
	GameState.instance().initialize(game);
	System.out.println(game.getEntry().describe());
	input = promptUser(buffy); 
	while(!input.equals("q")){
		Command move = CommandFactory.instance().parse(input);
		System.out.println(move.execute());
		System.out.println(GameState.instance().getAdventurersCurrentRoom().describe());
		input = promptUser(buffy); 
}}
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
private static Dungeon buildSampleDungeon(){
	Room one = new Room("Nathan's Room");
        Room two = new Room("Heather's Room");
        Room three = new Room("Hallway");
        Room four = new Room("Kitchen");
        Room five = new Room("Living Room");
	one.setDesc("There is a very large mess here. Clothes are strewn all over the floor haphazzardly.. Obviously someone messy lives here");
	two.setDesc("This room is immaculate. There is a beautiful woman here.");
	three.setDesc("A long hallway with three doors. The pictures on the walls watch you.");
	four.setDesc("A delicious smell is comming from the oven. There are dirty dishes all over the counter. Obviously the cook is a messy person.");
	five.setDesc("There is a couch here with a tv on the wall. Playing on the tv is a show called Big Bang Theroy.");
	Exit stairs = new Exit("u",one,four);
	Exit stairs1 = new Exit("d", four, one);
	Exit door = new Exit("w",two,three);
	Exit door1 = new Exit("e",three,two);
	Exit door2 = new Exit("w",three,four);
	Exit door3 = new Exit("e",four,three);
	Exit door4 = new Exit("w",four,five);
	Exit door5 = new Exit("e",five,four);
	one.addExit(stairs);
	two.addExit(door);
	three.addExit(door1);
	three.addExit(door2);
	four.addExit(stairs1);
	four.addExit(door3);
	four.addExit(door4);
	five.addExit(door5);
	Dungeon game = new Dungeon(one, "map");
	game.add(one);
	game.add(two);
	game.add(three);
	game.add(four);
	game.add(five);
	return game;
}
}
