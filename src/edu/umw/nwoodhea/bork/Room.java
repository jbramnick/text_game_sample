package edu.umw.nwoodhea.bork;

public class Room{

private String title;
private String desc;
private boolean beenHere = false;
public Room(String title){
	this.title = title;

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
	return text;
}

public String getTitle(){
	return title;
}
}


 
