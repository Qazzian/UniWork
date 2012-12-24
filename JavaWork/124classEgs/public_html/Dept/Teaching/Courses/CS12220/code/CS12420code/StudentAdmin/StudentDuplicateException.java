/**
 *  A class for throwing when a student is entered into a module twice
 * @author Mark Ratcliffe
 * @version Last revision date: 10/2/2000
 *
 */

public class StudentDuplicateException extends StudentException
{
    /**
     * Base class exception for StudentDuplicateException exception
    */
    public StudentDuplicateException()
    {
        super( "Duplicate Student!");
    }

    /**
     * Base class exception for StudentDuplicateException exception
    */
    public StudentDuplicateException(String msg)
    {
        super(msg);
    }
}

