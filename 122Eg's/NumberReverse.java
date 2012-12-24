/**
 * NumberReverse handles a simple array of integers
 * @author Evening Class of Cs12220
 * @version 05/12/99
 */

public class NumberReverse
{
    public static final int MAX_SIZE = 10;

    private int [] theList = new int[MAX_SIZE];

    private int currentCount = 0;


    /**
     * add - enables a user to add another value to list
     */
    public void add(int theNumber)
    {
        theList[currentCount] = theNumber;
        currentCount++;
    }
     
    /**
     * reverse - reverse the order of the integers in list
     */
    public void reverse()
    {
    }

    /**
     * toString - returns a formatted string of list
     */
    public String toString()
    {
        String tempString = "";
        for (int i=0; i < currentCount; i++)
        {
           tempString = tempString + "\n" + theList[i];
        }
        return tempString;
    }

    /**
     * main used for a simple test
     */
    public static void main(String [] args)
    {
        for (int i=0; i < args.length; i++)
        {
            System.out.println(args[i]);
        }

        NumberReverse test = new NumberReverse();

        test.add(1);
        test.add(5);
        test.add(3);

        System.out.println(test);

    }
}