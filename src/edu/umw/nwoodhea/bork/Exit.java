package edu.umw.nwoodhea.bork;

class Exit{

private String dir;
private String dest;
private String src;

public  void Exit(String dir, String src, String dest){
	this.dir = dir;
	this.dest = dest;
	this.src = src;
}
String describe(){
	String text = "You can go " + dir + " to " + dest+ ".";
	return text;
}
public String getDir(){
	return dir;
}

public Room getLoc(){
	
	return null;
}
}
