// // package Qaz.util;

/**
 * Base class For Queue Exceptions.
 *
 * @author 	<A href=mailto:Qaz_Wallis@Yahoo.com>Ian Wallis</A>
 * 			<A href=mailto:ifw9@aber.ac.uk>ifw9@aber.ac.uk</A>
 * 			with help from Aberystwyth University
 * @version 1.00, 19 March 2000
 */
public class QueueException extends Exception
{
	/**
	 * Calls the base class Constructor parsing in a String 
	 * stating that there is an  "Error in Queue!"
	 */
	public QueueException()
	{
		super("Error in Queue!");
	}
	
	/**
	 * Calls the base class Constructor parsing in a user defined String.
	 * @param String str The user defined String .
	 */
	public QueueException(String str)
	{
		super(str);
	}
	
	/**
	 * Calls the base class Constructor parsing in a String stating that 
	 * there is an "Error in Queue!" and the toString for the 
	 * <code>Object</code> holding the Queue.
	 * @param Object obj The Object holding the Queue .
	 */
	public QueueException(Object obj)
	{
		super("There is an error with the Queue in Object " + 
				obj.toString() );
	}
	
	/**
	 * Calls the base class Constructor parsing in a user defined String 
	 * and the toString of the <code>Object<code> holding the 
	 * <code>Queue</code>.
	 * @param String str The user defined String.
	 * @param Object obj The <code>Object</code> holding the 
	 * 					 <code>Queue</code>.
	 */
	public QueueException(String str, Object obj)
	{
		super(str + obj);
	}	
}