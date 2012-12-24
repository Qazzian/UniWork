/**
 * A simple exception for handling empty lists
 *
 * @author Mark Ratcliffe
 * @version 1.0 (10/02/99)
 *
 * @see aber.util.ListException
 */


public class ListEmptyException extends ListException
{
    /**
     * ListEmptyException thrown if list is empty
    */
    public ListEmptyException()
    {
        super( "The list is empty!");
    }

    /**
     * ListEmptyException thrown if list is empty
     * @param msg message to be passed to Exception handler
    */
    public ListEmptyException(String msg)
    {
        super(msg);
    }
}

