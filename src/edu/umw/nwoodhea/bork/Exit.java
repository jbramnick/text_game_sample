package edu.umw.nwoodhea.bork;

class Exit{

private String dir;
private Room dest;
private Room src;

public Exit(String dir, Room src, Room dest){
	this.dir = dir;
	this.dest = dest;
	this.src = src;
}
String describe(){
	String text = "You can go " + dir + " to " + dest.getTitle()+ ".";
	return text;
}
public String getDir(){
	return dir;
}

public Room getDest(){
	
	return dest;
}

public Room getSrc(){
	return src;
}
}

