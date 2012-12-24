// package Qaz.util;

import java.io.*;

/**
 * An Interface for any object to be put in a list.
 * these objects must include the isEqual Method
 *
 * @author 	<A href=mailto:Qaz_Wallis@Yahoo.com>Ian Wallis</A>
 * 			<A href=mailto:ifw9@aber.ac.uk>ifw9@aber.ac.uk</A>
 * 			with help from Aberystwyth University
 * @version 1.00, 19 March 2000
 */
public interface Listable extends Serializable
{
	/**
	 * Tests to see if the Listable Object parsed in to the isEqual Method  
	 * is similar to the Object the Method is called on.
	 * This Method needs to redefined to test one or more attributes
	 * of the class which have Unique values for each instance.
	 * @param Listable obj The Listable Object to be tested against.
	 * @return True if the Objects are similar as defined by the method.
	 * @return False if the Objects are not similar as defined by the method.
	 */
	public boolean isEqual(Listable obj);

    /**
     * Same as above but has a string value which you can use for 
	 * added functionality such as a choice of tests. eg Use a series of
     * If statements which test against the String to select the attribute
     * to test.
	 * @param Listable obj The Listable Object to be tested against.
     * @param String s A label for extra functionality
	 * @return True if the Objects are similar as defined by the method.
	 * @return False if the Objects are not similar as defined by the method.
     */
	public boolean isEqual(Listable obj,String s);
}