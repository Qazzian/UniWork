import java.util.Iterator;
import aber.util.TextInputReader;
import aber.util.TextIOException;

/**
 * A class to test the functioning of the Queue class
 * @author Mark Ratcliffe
 * @version Last revision by Mark Ratcliffe 27/02/99
 */
public class QueueTest
{
    
    /**
     *  A simple test harness to make sure the queue works OK 
     * @exception aber.util.TextIOException
     * - raised if an error occurs on input
     */
    public static void main (String [] args) throws TextIOException,
                                                    Exception
    {
        final int maxItems=10;
        Queue myQueue = new Queue(15);
        Queue otherQueue = new Queue(maxItems);
        
        TextInputReader t = new TextInputReader();
        
        String theName;
        
        for (int count = 0; count < maxItems; count++)
        {
            theName = t.promptForString("Enter name ");
            
            try
            {
                myQueue.addItem(theName);
                otherQueue.addItem(theName);
            }
            catch (Exception e)
            {
                System.out.println("Error in adding " + e);
            }

        }
        
        System.out.println(myQueue);

        // two queues should be equal so let's test
        if (!myQueue.equals(otherQueue))
        {
            System.out.println("Error in equals - queues are equal");
        }
        else
        {
            System.out.println("Equals ok on equal queues");
        }
        
        while (myQueue.length() != 0)
        {
            System.out.println(myQueue.frontOf() + "\t is at front");
            System.out.println(myQueue.removeItem() + "\t has been removed");
        }
        
        // two queues should not be equal so let's test
        if (myQueue.equals(otherQueue))
        {
            System.out.println("Error in equals - queues are unequal");
        }
        else
        {
            System.out.println("Equals ok on unequal queues");
        }

        // Now let's test the iterator over the Queue
        String temp;

        Iterator theIter = otherQueue.elements();
        while (theIter.hasNext())
        {
            temp = (String) theIter.next();
            System.out.println("Iterating -> " + temp + "\n");
        }
       
    }
}
