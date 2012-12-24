
/**
 * A simple exception class to identify exceptions relating to the
 * ListException .
 *
 * @author John Woodbury
 * @author Mark Ratcliffe
 * @version 1.0 (10/02/98)
 *
 * @see aber.util.ListException
 */


public class ListFullException extends ListException
{
    /**
     * ListFullException thrown if list is full
    */
    public ListFullException()
    {
        super( "The list is empty!");
    }

    /**
     * FullListException thrown if list is full
     * @param msg message to be passed to Exception handler
    */
    public ListFullException(String msg)
    {
        super(msg);
    }
}

