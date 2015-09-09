package edu.umw.nwoodhea.bork;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;
public class Room{

private String title;
private String desc;
private ArrayList<Exit> exits;
private boolean beenHere = false;

public Room(String title){
	this.title = title;
	exits = new ArrayList<Exit>();

}
public void setDesc(String desc){
	this.desc = desc;
}
String describe(){
	String text = title;

	if(beenHere == false){
		beenHere = true;
		text = text+": "+desc;
}
	for(Exit exit: exits){
		text = text +"\n"+exit.describe();
}
	return text;
}

public String getTitle(){
	return title;
}
public void addExit(Exit exit){
	exits.add(exit);
}

Room leaveBy(String dir){
	Exit out = null;
	boolean found = false;
	for(Exit exit : exits){
		if(exit.getDir().equals(dir)){
			out = exit;
			found = true;
}}
	if(found == true){
		return out.getDest();
}
	else{
		throw new IllegalArgumentException("ERROR");
}

}
}



 
