/**
 * A class for providing the base class for Stack exceptions
 * @author Mark Ratcliffe
 * @version Last revision date: 22/04/99
 *
 */

public class StackException extends Exception
{
    /**
     * Base class exception for general stack exception
     */
    public StackException()
    {
        super("Generic List Error!");
    }

    /**
     * Base class exception for general stack exception
     *
     * @param msg the error message
     */
    public StackException (String msg)
    {
        super(msg);
    }
}
