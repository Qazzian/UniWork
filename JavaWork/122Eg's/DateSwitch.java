/**
 *  A simple program to show the switch statement
 *  File: DateSwitch.java    test 4
 *  @author  Mark Ratcliffe
 *  @version Last revision by Mark Ratcliffe 28 September 1997
 */
public class DateSwitch
{
    // Declare the instance variables 
    private int theDay;
    private int theMonth;
    private int theYear;
        
    public DateSwitch(int day, int month, int year)
    {
        theDay = day;
        theMonth = month;
        if (theMonth > 12)
        {
            theMonth = 12;
        }
        theYear = year; 
    }       

    public void printDaysInMonth()
    {
        switch(theMonth)
        {
            case 9:
            case 4:
            case 6:
            case 11:
                System.out.println("30 days this month");
                break;
            case 2:
                System.out.println("28 days this month");
                break;
            default:
                System.out.println("31 days this month");
        }
        System.out.println("Hope that was correct");
    }
    
    // And now a main program to test the class
    public static void main (String [] args)
    {
        DateSwitch theDate = new DateSwitch(21, 7, 1962);
        theDate.printDaysInMonth();
    }
}

