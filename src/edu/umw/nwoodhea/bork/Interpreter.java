package edu.umw.nwoodhea.bork;
import java.util.Scanner;


class Interpreter {
private Dungeon game;
public void main (String args[]){
	String input;
	Scanner scan = new Scanner(System.in);
	input = scan.next();
	while(!input.equals("q")){
		input = scan.next();
		buildSampleDungeon();
		Room entry = game.getEntry();
		System.out.println(entry.describe());
}
}

private void buildSampleDungeon(){
	Room one = new Room("Nathan's Room");
        Room two = new Room("Heather's Room");
        Room three = new Room("Hallway");
        Room four = new Room("Kitchen");
        Room five = new Room("Living Room");
	game = new Dungeon(one, "map");
	game.add(one);
	game.add(two);
	game.add(three);
	game.add(four);
	game.add(five);
	


}
}



