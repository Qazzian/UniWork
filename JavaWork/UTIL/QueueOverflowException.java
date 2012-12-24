// package Qaz.util;

/**
 * Queue Exception Class for when the Queue is Full.
 *
 * @author 	<A href=mailto:Qaz_Wallis@Yahoo.com>Ian Wallis</A>
 * 			<A href=mailto:ifw9@aber.ac.uk>ifw9@aber.ac.uk</A>
 * 			with help from Aberystwyth University
 * @version 1.00, 19 March 2000
 */
public class QueueOverflowException extends QueueException
{
	/**
	 * Calls the base class Constructor parsing in a String
	 * stating that the "Queue is Full!"
	 */
	public QueueOverflowException()
	{
		super("Queue is Full!");
	}
	
	/**
	 * Calls the base class Constructor parsing in a user defined String.
	 * @param String str The user defined String.
	 */
	public QueueOverflowException(String str)
	{
		super(str);
	}
	
	/**
	 * Calls the base class Constructor parsing in a String stating that 
	 * the <code>Queue</code> is Full and the toString for the 
	 * <code>Object</code> holding the <code>Queue</code>.
	 * @param Object obj The <code>Object</code> holding the 
	 * 					 <code>Queue</code>.
	 */
	public QueueOverflowException(Object obj)
	{
		super("The Queue is Full in Object " + 
				obj.toString() );
	}
	
	/**
	 * Calls the base class Constructor parsing in a user defined String 
	 * and the toString of the <code>Object</code> holding the 
	 * <code>Queue</code>.
	 * @param String str The user defined String.
	 * @param Object obj The <code>Object</code> holding the 
	 * 					 <code>Queue</code>.
	 */
	public QueueOverflowException(String str, Object obj)
	{
		super(str + obj);
	}	
}