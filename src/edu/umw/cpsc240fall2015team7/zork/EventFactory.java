package edu.umw.cpsc240fall2015team7.zork;
/**
  *A singleton class that parses event strings and returns Event objects.
  *@author Nathanael Woodhead
  */
class EventFactory{

	public static EventFactory theInstance;
	/**
	  *Creates a new EventFactory object if one does not already exist. If one already exists it will
	  *return the existing EventFactory object.
	  *@author Nathanael Woodhead
	  */
	public static EventFactory instance(){
		if(theInstance == null){
			theInstance = new EventFactory();
		}
		return theInstance;
	}
	/**
	  *Empty Constructor.
	  *@author Nathanael Woodhead
	  */
	private EventFactory(){
	}
	/**
	  *Returns the correct Event object for the string entered. 
	  *@throws NoEventException If no Event matches the string passed.
	  *@author Nathanael Woodhead
	  */
	Event parse(String eventString){

		return null;
	}
}

