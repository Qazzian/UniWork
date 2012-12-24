import aber.util.TextInputReader;
import aber.util.TextIOException;

/**
 * A class to demonstrate sorting within an array
 * @author Mark Ratcliffe
 * @version Last revision by Mark Ratcliffe 18/06/98
 */
public class DivingSort
{
    // We use a final i.e. a constant to define size of array
    private final int MAX_DATES = 5;

    // Now several definitions can make use of our constant
    private int[] datesThisMonth = new int [MAX_DATES];
        

    /**
     * Asks user to supply all dates for next events
     */
    public void setDates () throws TextIOException
    {
        TextInputReader t = new TextInputReader();

        for (int dateNum = 0; dateNum < MAX_DATES; dateNum++)
        {
            System.out.println("Please type in date for event "
                           + (dateNum+1)
                           + " ");
            datesThisMonth[dateNum] = t.readInt();
        }
    }
    
    /**
     * Sorts the array into numerical order
     */
    public void sortDates ()
    {
        int temporary;      // temporary variable used in swap
        boolean changed;    // used to record that a change has occured
        
        for (int outer = 0; outer < MAX_DATES; outer++)
        {
            changed = false;

             // this is the more efficient technique
             // for (int inner = 0; inner < (MAXDATES-(outer+1)); inner++)

            for (int inner = 0; inner < (MAX_DATES-1); inner++)
            {
                if (datesThisMonth[inner] > 
                       datesThisMonth[inner+1])
                {
                    temporary = datesThisMonth[inner+1];
                    datesThisMonth[inner+1] = datesThisMonth[inner];
                    datesThisMonth[inner] = temporary;
                    changed = true;
                }
            }
            if (!changed)
            {
                break;          // leave the outer loop
            }
        }
    }

    /**
     * Display prints out sorted list on screen
     */

    public void display()
    {
        // print out the list of dates 
        System.out.println("The list of dates is:");       
        for (int dateNum = 0; dateNum < MAX_DATES;  dateNum++)
            System.out.println(datesThisMonth[dateNum]);
    }
    

    /**
     * Simple test harness
     */
    public static void main (String [] args) throws TextIOException
    {
        DivingSort October = new DivingSort();
        October.setDates();
        October.display();
        System.out.println("And now I'm sorting");
        October.sortDates();
        October.display();
    }
}
