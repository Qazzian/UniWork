/**
 *  A class for providing the exception to be thrown when Queue is empty
 * @author Mark Ratcliffe
 * @version Last revision date: 27/04/99
 *
 */

public class QueueExceptionEmpty extends QueueException
{
    /**
     * Empty Exception
     */
    public QueueExceptionEmpty()
    {
        super( "Queue is Empty - operation failed!");
    }

    /**
     * Empty Exception
     */
    public QueueExceptionEmpty(String msg)
    {
        super(msg);
    }
}

