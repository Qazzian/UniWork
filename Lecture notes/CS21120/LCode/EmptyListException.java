// An exception for when the list is empty

public class EmptyListException extends RuntimeException
{
	public EmptyListException()
	{
		// Calls the superclass (RuntimeException) constructor with no parameters
   	super();
   }

	public EmptyListException(String msg)
	{
		// Calls the superclass (RuntimeException) constructor with a parameter
      // containing a message
   	super(msg);
   }
}