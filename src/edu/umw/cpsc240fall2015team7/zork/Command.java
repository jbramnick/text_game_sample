/**
*Abstract super class for all commands.
*@author Nathanael Woodhead
*/
package edu.umw.cpsc240fall2015team7.zork;
import java.lang.IllegalArgumentException;
abstract class Command{
	/**
	*super method for all subclasses to overwrite regarding their command behavior once executed.
	*@author Nathanael Woodhead
	*/
	abstract String execute(){
	}
}
