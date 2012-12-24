/**
 *  A class for providing the base class for List exceptions
 * @author John Woodbury
 * @author Mark Ratcliffe
 * @version Last revision date: 10/2/98
 *
 */

public class ListException extends Exception
{
    /**
     * Base class exception for general list exception
    */
    public ListException()
    {
        super( "Generic List Error!");
    }

    /**
     * Base class exception for general list exception
    */
    public ListException(String msg)
    {
        super(msg);
    }
}

