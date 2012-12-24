import java.io.*;

public class KeyboardDemo
{
    /**
     * This example demonstrates using a tokeniser to read in
     * several separate items off one line
     */
    public static void main(String args[]) throws IOException
    {
        StreamTokenizer stdin = new StreamTokenizer(
		                         new BufferedReader(
		                         new InputStreamReader(System.in)));

        int theType;  // holds the return type of the token

        int bigNumber = 0;
        int inputDigit = 0;
        stdin.eolIsSignificant(true);  // use EOL as a token

        System.out.println("Enter your number, separated by spaces "
		                   + "and terminated by the enter key");
        do
        {
        	bigNumber = bigNumber * 10;		// Shift left, eg: 23 -> 230
            inputDigit = Math.abs(inputDigit);	// Disallow negatives!
        	bigNumber = bigNumber + inputDigit; // Add digit: 230 -> 238

            theType = stdin.nextToken();        // this can throw exception

            if (theType != StreamTokenizer.TT_NUMBER)
            {
                 break;
            }
            inputDigit=(int)stdin.nval;

		} while (true);

        System.out.println("The number was " + bigNumber);
    }
}
