import java.io.*;

public class CharCount
{
    public static void main(String args[]) throws IOException
    {
        int [] alphaCount = new int [26]; // for counting the chars

        String theString;

        int charAsInt =0;

        BufferedReader stdin =  new BufferedReader(
                                 new InputStreamReader(System.in));

        System.out.println("Enter you string now please");

        theString = stdin.readLine();  // throws IOException

        // convert to lower case

        theString = theString.toLowerCase();

        // loop through string analysing characters using charAt

        for (int i=0; i < theString.length(); i++)
        {

            char currentChar = theString.charAt(i);
            // ignore everything in string except a-z

            if ((currentChar < 'a') | (currentChar > 'z'))
            {
                continue;
            }

            // convert to an int
            charAsInt = (int) currentChar - (int) 'a';

            // increment appropriate location in array
            alphaCount[charAsInt]++;
        }

        //Print out table

        for (int i=0; i<26;i++)
        {
            System.out.print((char)(i + (int)'a'));
            System.out.println(" " + alphaCount[i]);
        }
        
    }
}
