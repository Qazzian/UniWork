/**
 *  A class for providing the exception to be thrown when Queue is full
 * @author Mark Ratcliffe
 * @version Last revision date: 27/04/99
 *
 */

public class QueueExceptionFull extends QueueException
{
    /**
     * Exception thrown when Queue is full
     */
    public QueueExceptionFull()
    {
        super( "Queue is Full - operation failed!");
    }

    /**
     * Exception thrown when Queue is full
     */
    public QueueExceptionFull(String msg)
    {
        super(msg);
    }
}

