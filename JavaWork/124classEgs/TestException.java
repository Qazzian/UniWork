import aber.util.TextInputReader;
import aber.util.TextIOException;

/**
 * A class to test exception handling
 * @author Mark Ratcliffe
 * @version Last revision by Mark Ratcliffe 18/06/98
 */

public class TestException
{
    public static void main (String [] args)
    {
        int ageOfPerson = 0;
        boolean successful = false;
        
        TextInputReader t = new TextInputReader();

        do 
        {
            // The exception is expected to be thrown in the try
            try 
            {
                ageOfPerson = t.promptForInt("Please enter your age ");
                successful = true;
            }
            // if an exception is thrown, it is caught here
            catch (TextIOException e)
            {
                System.out.println("That wasn't an integer " + e);
            }
            finally
            {
                System.out.println("I have just executed finally");
            }
        } while (!successful);
    
        System.out.println("Ok success - you typed in " 
                           + ageOfPerson);
    }
}

