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
	if(beenHere == false){
		beenHere = true;
		return desc;
}
	else{
		return null;
}
}
public String getTitle(){
	return title;
}
public static void main(){
	Room nathansRoom = new Room("Nathans Room");
	nathansRoom.setDesc("This is Nathans Room. There is lots of mess on the floor");
	System.out.println(nathansRoom.describe() );
	System.out.println(nathansRoom.describe());
}
}

 
