package edu.umw.nwoodhea.bork;

class Command{

private String dir;

command(String dir){
	this.dir = dir;
	execute();
}

private String execute(){
	return dir;
}

