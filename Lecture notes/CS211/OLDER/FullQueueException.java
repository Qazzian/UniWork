// An exception for when the list is empty

public class FullQueueException extends RuntimeException
{
	public FullQueueException()
	{
		// Calls the superclass (RuntimeException) constructor with no parameters
   	super();
   }

	public FullQueueException(String msg)
	{
		// Calls the superclass (RuntimeException) constructor with a parameter
      // containing a message
   	super(msg);
   }
}