import aber.util.TextInputReader;
import aber.util.TextIOException;

/**
 * A simple program to demonstrate if statements
 * @author Class of CS12220
 * @version 2/11/99
 */
public class NumberCheck
{

    public static void main (String [] args) 
	                        throws TextIOException
    {
        TextInputReader t = new TextInputReader();
        int theNum = t.promptForInt("Enter a number ");
        if (theNum % 2 == 0)
        {
            System.out.println("The number is even ");
        }
        else
        {
            System.out.println("The number is odd ");
        }
        System.out.println("Number you typed was " + theNum);
	}
}