// An exception for when the list is empty

public class EmptyQueueException extends RuntimeException
{
	public EmptyQueueException()
	{
		// Calls the superclass (RuntimeException) constructor with no parameters
   	super();
   }

	public EmptyQueueException(String msg)
	{
		// Calls the superclass (RuntimeException) constructor with a parameter
      // containing a message
   	super(msg);
   }
}