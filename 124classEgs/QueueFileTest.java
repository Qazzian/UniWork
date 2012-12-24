import java.util.Iterator;
import java.io.*;

/**
 * A class to test the functioning of the Queue class
 * @author Mark Ratcliffe
 * @version Last revision by Mark Ratcliffe 27/02/99
 */
public class QueueFileTest
{
    
    /**
     *  A simple test harness to make sure the queue works OK
     * - raised if an error occurs on input
     */
    public static void main (String [] args) throws IOException,
                                                    Exception
    {
        final int maxItems=10;
        Queue myQueue;

        String theString;

        BufferedReader stdin =  new BufferedReader(
                                 new InputStreamReader(System.in));
	     
        // read in from file
        try
        {
             myQueue = Queue.readFromFile("temp");
        }
        catch (IOException e)
        {
             myQueue = new Queue(15);
        }

        for (int count = 0; count < maxItems; count++)
        {
            System.out.println("Enter you string now please");
            theString = stdin.readLine();  // throws IOException
            try
            {
                myQueue.addItem(theString);
            }
            catch (Exception e)
            {
                System.out.println("Error in adding " + e);
            }

        }
        myQueue.writeToFile("temp");
        System.out.println(myQueue);
    }
}
