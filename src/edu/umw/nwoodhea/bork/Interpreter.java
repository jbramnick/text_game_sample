package edu.umw.nwoodhea.bork;
import java.util.Scanner;


class Interpreter {
public static void main (String args[]){
	String input;
	Scanner scan = new Scanner(System.in);
	input = scan.next();
	buildSampleDungeon();
	while(!input.equals("q")){
		input = scan.next();
}
}

private static void buildSampleDungeon(){
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
	Dungeon game = new Dungeon(one, "map");
	game.add(one);
	game.add(two);
	game.add(three);
	game.add(four);
	game.add(five);
	Room currentRoom = game.getRoom("one");
	System.out.println(one.describe());



}
}



