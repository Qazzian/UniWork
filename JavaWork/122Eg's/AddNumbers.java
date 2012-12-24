import aber.util.TextInputReader;
import aber.util.TextIOException;

/**
 * Program to add up a series of integers terminated by -1
 */

public class AddNumbers
{

    public static void main (String[] args) 
	                   throws TextIOException
    {
        TextInputReader t = new TextInputReader();

        int inputValue = 0;
        int accumulator = 0;

        while (inputValue != -1)
        {
             inputValue = t.promptForInt("\nEnter new number ");
             accumulator = accumulator + inputValue;
        }
        System.out.println("\nTotal is " + accumulator);
    }

}