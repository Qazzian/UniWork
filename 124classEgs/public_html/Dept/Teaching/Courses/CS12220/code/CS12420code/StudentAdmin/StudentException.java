/**
 *  A class for providing the base class for List exceptions
 * @author John Woodbury
 * @author Mark Ratcliffe
 * @version Last revision date: 10/2/98
 *
 */

public class StudentException extends Exception
{
    /**
     * Base class exception for general student exception
    */
    public StudentException()
    {
        super( "Generic Student Error!");
    }

    /**
     * Base class exception for general student exception
    */
    public StudentException(String msg)
    {
        super(msg);
    }
}

