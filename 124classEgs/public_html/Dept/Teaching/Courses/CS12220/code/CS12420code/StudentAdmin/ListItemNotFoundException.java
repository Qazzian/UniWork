/**
 * A simple exception for handling items not found in list
 *
 * @author Mark Ratcliffe
 * @version 1.0 (10/02/99)
 *
 * @see aber.util.ListException
 */


public class ListItemNotFoundException extends ListException
{
    /**
     * ListEmptyException thrown if list is empty
    */
    public ListItemNotFoundException()
    {
        super( "The list is empty!");
    }

    /**
     * EmptyListException thrown if list is empty
     * @param msg message to be passed to Exception handler
    */
    public ListItemNotFoundException(String msg)
    {
        super(msg);
    }
}

