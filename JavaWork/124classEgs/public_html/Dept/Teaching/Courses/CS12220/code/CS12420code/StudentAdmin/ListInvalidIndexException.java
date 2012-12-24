/**
 * A simple exception for handling items not found in list
 *
 * @author Mark Ratcliffe
 * @version 1.0 (10/02/99)
 *
 * @see aber.util.ListException
 */


public class ListInvalidIndexException extends ListException
{
    /**
     * ListInvalidIndexException thrown if index is invalid
    */
    public ListInvalidIndexException()
    {
        super( "The list is empty!");
    }

    /**
     * EmptyListException thrown if  if index is invalid
     * @param msg message to be passed to Exception handler
    */
    public ListInvalidIndexException(String msg)
    {
        super(msg);
    }
}

