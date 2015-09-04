package edu.umw.nwoodhea.bork;

public class Room{

private String title;
private String desc;
private boolean beenHere = false;
public void Room(String title){
	this.title = title;

}
public void setDesc(String desc){
	this.desc = desc;
}
String describe(){
	if(!beenHere){
		beenHere = !beenHere;
		return desc;
}
	else{
		return null;
}
}
public String getTitle(){
	return title;
}
}

 
