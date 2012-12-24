import java.io.*;       // gives access to general io facilities
import aber.util.TextInputReader;
import aber.util.TextIOException;

/**
 * A class to demonstrate simple file handling of text
 * @author Mark Ratcliffe
 * @version Last revision by Mark Ratcliffe 18/06/98
 */

class TextFileTest
{
    TextInputReader t = new TextInputReader();

    public void keysToFile(String filename)
    {
        String theLine;
        try
        {
            PrintWriter myStream =
                new PrintWriter(           // writes characters not bytes
                new FileWriter(filename)); // convenience class with buffer

            System.out.println("Preparing to write to the file now....");

            for (int i=0; i<5; i++)
            {
                theLine = t.promptForString("Enter line of text  ");
                myStream.println(theLine);
            }

            myStream.close();

        }
        catch (TextIOException e)
        {
            System.out.println("Error in TextInputReader");
        }
        catch (Exception e)
        {
            System.out.println("Error in writing to file: ");
        }


    }


    public void fileToScreen(String filename)
    {
        String theLine;
        try
        {
            BufferedReader myStream =
                new BufferedReader(           // reads characters not bytes
                new InputStreamReader(
                new FileInputStream(filename)));

            System.out.println("Reading from the file now....");

            // Read the object from file - it needs to be cast

            do
            {
                theLine = myStream.readLine();  // reads in a string
                if (theLine == null)
                {
                    break; // that's it - no more
                }
                System.out.println(theLine);
            } while (true);

             myStream.close();
        }
        catch(Exception e)
        {
            System.out.println("Error in reading from file: ");
        }
    }


    public static void main (String [] args) throws IOException 
    {
        TextFileTest theText = new TextFileTest();
        theText.keysToFile("temp");
        theText.fileToScreen("temp");
    }
}

