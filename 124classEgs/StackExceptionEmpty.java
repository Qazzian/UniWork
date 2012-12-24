/**
 * A class for providing the empty exception for a Stack
 * @author Mark Ratcliffe
 * @version Last revision date: 22/04/99
 *
 */

public class StackExceptionEmpty extends StackException
{
    /**
     * Exception to be thrown when a stack is empty
     */
    public StackExceptionEmpty()
    {
        super("Generic List Error!");
    }

    /**
     * Exception to be thrown when a stack is empty
     *
     * @param msg the error message
     */
    public StackExceptionEmpty (String msg)
    {
        super(msg);
    }
}
