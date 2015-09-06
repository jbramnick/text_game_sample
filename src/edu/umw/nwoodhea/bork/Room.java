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
public static void main(){
	Room nathansRoom = new Room("Nathans Room");
	nathansRoom.setDesc("This is Nathans Room. There is lots of mess on the floor");
	System.out.println(nathansRoom.describe() );
	System.out.println(nathansRoom.describe());
	Room heathersRoom = new Room("Heathers Room");
        heathersRoom.setDesc("This is Heathers Room. There is a heather here.");
        System.out.println(heathersRoom.describe() );
        System.out.println(heathersRoom.describe());

}
}

 
