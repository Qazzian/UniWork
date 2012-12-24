import aber.util.TextInputReader;
import aber.util.TextIOException;

/**
 * Program to add up a series of integers terminated by -1
 */

public class AddNumbersTwo
{

    public static void main (String[] args) 
	                   throws TextIOException
    {
        TextInputReader t = new TextInputReader();

        int inputValue = 0;
        int accumulator = 0;
        while (true)
        {
             if (inputValue == 5)
             {
                 break;
             }
             accumulator = accumulator + inputValue;
             inputValue = t.promptForInt("\nEnter new number ");
        }
        System.out.println("\nTotal is " + accumulator);
    }

}