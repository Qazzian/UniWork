/**
 * A better class for TimesTables
 * @author Mark Ratcliffe
 * @version Last revision by Mark Ratcliffe 18/06/98
 */
public class TimesTables
{

    // Give maxValue an initial value of 12
    public int maxValue = 12;

    /**
	 * Default constructor
	 * 
	 */
	public TimesTables()
    {
        // uses default value
    }

    /**
     * Constructor creates a new instance of object
     * @param max the maximum number of tables to generate
     */
    public TimesTables(int max)
    {
        // Constructor can be used to change initial value
        maxValue = max; 
    }

    /**
     * Display times table on screen
     */
    public String toString ()
    {
        String tempString ="";

        for (int rowCount = 1; rowCount <= maxValue; rowCount++) 
        {
            for (int colCount = 1; colCount <= maxValue; colCount++)
            {
                tempString = tempString +(rowCount * colCount + "\t");
            }
            tempString = tempString + "\n";
        }
        return tempString;
    }

    public static void main (String [] args)
    {
        // We use main for our testing

        // Build a TimesTable for 10
        TimesTables tenTimes = new TimesTables(10);

        System.out.println(tenTimes);

    }
}
