/**
 *  A class for providing the base class for Queue exceptions
 * @author Mark Ratcliffe
 * @version Last revision date: 10/2/98
 *
 */

public class QueueException extends Exception
{
    /**
     * Base class exception for general list exception
    */
    public QueueException()
    {
        super( "Generic Queue Error!");
    }

    /**
     * Base class exception for general list exception
    */
    public QueueException(String msg)
    {
        super(msg);
    }
}

