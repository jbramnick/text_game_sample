package edu.umw.cpsc240fall2015team7.zork;
import java.util.ArrayList;
import java.lang.reflect.Constructor;
/**
  *A singleton class that parses event strings and returns {@link Event} objects.
  *@author Nathanael Woodhead
  */
class EventFactory{

	public static EventFactory theInstance;
	/**
	  *Creates a new EventFactory object if one does not already exist. If one already exists, it will
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
	  *Creates a new EventFactory object.
	  *@author Nathanael Woodhead
	  */
	private EventFactory(){
	}
	/**
	 *Returns the correct Event object for the String entered. 
	 *@throws NoEventException If no Event matches the String passed.
	 *@author Nathanael Woodhead
	 */
	ArrayList<Event> parse(Item item,String eventString) throws Dungeon.IllegalDungeonFormatException
	{
		try
		{
			System.out.println(eventString);
			String[] eventsStrings=eventString.split(",");
			ArrayList<String> parenEvents=new ArrayList<String>();
			ArrayList<String> normalEvents=new ArrayList<String>();
			ArrayList<Event> events=new ArrayList<Event>();
			for(int i=0;i<eventsStrings.length;i++)
			{
				if(eventsStrings[i].contains("("))
					parenEvents.add(eventsStrings[i]);
				else
					normalEvents.add(eventsStrings[i]);
			}
			for(int i=0;i<parenEvents.size();i++)
			{
				String event=parenEvents.get(i).substring(0,parenEvents.get(i).indexOf("("));
				System.out.println(event);
				event = "edu.umw.cpsc240fall2015team7.zork."+event+"Event";
				Class clazz=Class.forName(event);
				Constructor cons=clazz.getDeclaredConstructor(Item.class,String.class);
				System.out.println("here");
				String value=parenEvents.get(i).substring(parenEvents.get(i).indexOf("(")+1,parenEvents.get(i).indexOf(")"));
				Event theEvent=(Event)cons.newInstance(item,value);
				events.add(theEvent);
			}
			for(int i=0;i<normalEvents.size();i++)
			{
				String event=normalEvents.get(i);
				System.out.println(event);
				event = "edu.umw.cpsc240fall2015team7.zork."+event+"Event";
				Class clazz=Class.forName(event);
				System.out.println("Hello");
				Constructor cons=clazz.getDeclaredConstructor(Item.class);
				System.out.println("Hello");
				Event theEvent=(Event)cons.newInstance(item);
				events.add(theEvent);
			}
			return events;
		}
		catch(Exception e)
		{
			System.out.println("Shit went down");
			return null;
		}
		

	}
	public static void main(String args[])
	{
		try
		{
			Item item=new Item("test",7);
			EventFactory.instance().parse(item,"Heal");

		}
		catch(Exception e)
		{}

	}
}

