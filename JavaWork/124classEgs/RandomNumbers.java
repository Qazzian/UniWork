
import java.util.Random;

/**
 *  A class that demonstrates the use of random numbers
 * @author Mark Ratcliffe
 * @version Last revision date: 11/2/98
 */
public class RandomNumbers
{
    // define an upper limit for our random number
    public static final int MAXRANGE = 99;

    public static void main (String [] args)
    {
        // Declare an object of type java.util.Random;
        Random randomiser = new Random();
        
        int theNum;
    
        // Set seed of randomiser to a fixed value
        // Optional - ensures we get same sequence for testing
        randomiser.setSeed(10);

        for (int i=0; i<10; i++)
        {
            // call nextInt to get the next random number
            theNum = randomiser.nextInt();
            
            // it might be negative so we use absolute value
            theNum = Math.abs(theNum);
             
            // use mod to ensure number between 0 and MAXRANGE
            theNum = theNum % MAXRANGE;
    
            System.out.println("Next number is " + theNum);
        }
    }
}

