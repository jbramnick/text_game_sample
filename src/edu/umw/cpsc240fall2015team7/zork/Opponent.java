package edu.umw.cpsc240fall2015team7.zork;
import java.util.*;
/**
*An intrinsically aggressive NPC. 
*@author Nathanael Woodhead 
*/
class Opponent extends Npc {
	protected int health;
	protected int power;
	protected int speed;
	protected String primaryName;
	protected Room room;
        /**
        *Constructs this Opponent with the default Speed value of 2.
        *@author Carson Meadows
        */
        public Opponent (int health, int power, String primaryName, Room room) {
		this.health=health;
		this.speed= 2;
		this.power=power;
		this.primaryName=primaryName;
		this.room=room;
        }
        /**
        *Constructs this Opponent.
        *@author Carson Meadows
        */
        public Opponent (int health, int power,int speed, String primaryName, Room room) {
		this.health=health;
		this.speed=speed;
		this.power=power;
		this.primaryName=primaryName;
		this.room=room;
        }

        /**
        *Damages the player equal to this Opponent's power.
        *@author Carson Meadows
        */
        public void attackPlayer () {
		Player.instance().takeWound(power);
        }
	/**
	*Moves this Opponent to an adjacent Room.
	*@author Carson Meadows
	*/
	public void move () {
		ArrayList<Exit> exits = room.getExits();
		Random selector = new Random();
		int select = selector.nextInt(exits.size());
		Exit go = exits.get(select);
		room = go.getDest();
	}
}

