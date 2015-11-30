package edu.umw.cpsc240fall2015team7.zork;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 *Returns a short tutorial of the game.  
 *@author Nathanael Woodhead
 */
class HelpCommand extends Command{

	/**
	 *Constructs this HelpCommand.
	 *@author Nathanael Woodhead
	 */
	HelpCommand(){
	}
	/**
	 *Returns a short tutorial of the game.  
	 *@author Nathanael Woodhaed 
	 */
	String execute(){
		try{
			File help = new File("Help.txt");
			Scanner scan = new Scanner(help);
			while(scan.hasNextLine()){
				System.out.println(scan.nextLine());
			}
		}catch(Exception e){
			return "Help file not found";
		}
		return "";
	}
}	
