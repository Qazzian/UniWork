// package Qaz.util;

/**
 * Queue Exception for when the Queue is Empty.
 * @author 	<A href=mailto:Qaz_Wallis@Yahoo.com>Ian Wallis</A>
 * 			<A href=mailto:ifw9@aber.ac.uk>ifw9@aber.ac.uk</A>
 * 			with help from Aberystwyth University
 * @version 1.00, 19 March 2000
 */
public class QueueUnderflowException extends QueueException
{
	/**
	 * Calls the base class Constructor parsing in a String 
	 * stating that the "Queue is Empty!"
	 */
	public QueueUnderflowException()
	{
		super("Queue is Empty!");
	}
	
	/**
	 * Calls the base class Constructor parsing in a user defined String.
	 * @param String str The user defined String.
	 */
	public QueueUnderflowException(String str)
	{
		super(str);
	}
	
	/**
	 * Calls the base class Constructor parsing in a String stating that 
	 * the <code>Queue</code> is Empty and the toString for the 
	 * <code>Object</code> holding the <code>Queue</code>.
	 * @param Object obj The Object holding the Queue.
	 */
	public QueueUnderflowException(Object obj)
	{
		super("The Queue is Empty in Object " + 
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
	public QueueUnderflowException(String str, Object obj)
	{
		super(str + obj);
	}	
}