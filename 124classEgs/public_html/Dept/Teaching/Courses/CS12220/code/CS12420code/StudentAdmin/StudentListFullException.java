/**
 *  A class for providing the base class for List exceptions
 * @author John Woodbury
 * @author Mark Ratcliffe
 * @version Last revision date: 10/2/98
 *
 */

public class StudentListFullException extends StudentException
{
    /**
     * Base class exception for ListFull student exception
    */
    public StudentListFullException()
    {
        super( "Generic Student Error!");
    }

    /**
     * Base class exception for ListFull student exception
    */
    public StudentListFullException(String msg)
    {
        super(msg);
    }
}

