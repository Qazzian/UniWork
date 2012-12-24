import java.io.*;
import java.util.Iterator;

/**
 * ListTest is a test routine for the List class
 * @author class of CS12420
 * @version First written 01/03/99
 */
public class SortedListTest
{
    public static void main (String [] args)
        throws IOException, ListFullException
    {
        SortedList studentList = new SortedList();

        BufferedReader stdin =  new BufferedReader(
                                 new InputStreamReader(System.in));

        String theName;
        String theAddress;
        String theUCAS;
        SortableStudent theStudent;
        for (int i=0; i < 3; i++)
        {
             System.out.println("Enter name ");
             theName = stdin.readLine();

             System.out.println("Enter address ");
             theAddress = stdin.readLine();

             System.out.println("Enter UCAS ");
             theUCAS = stdin.readLine();

             theStudent = new SortableStudent();
             theStudent.setForename(theName);
             theStudent.setAddress(theAddress);
             theStudent.setUcasCode(theUCAS);

             studentList.addItem(theStudent);

        }

        // And now to display UCAS codes only
        try
		{
		    int numInList = studentList.getListLength();

            for (int i=0; i < numInList; i++)
            {
                System.out.println(((SortableStudent)studentList.getItem(i))
                                                   .getUcasCode());
            }
        }
        catch (ListException e)
        {
            System.out.println("A problem with the list");
            e.printStackTrace();  // display problem but continue
        }
        // Lets test the isEqual interface with getItem
        SortableStudent found;
        SortableStudent toFind = new SortableStudent();
        System.out.println("Enter UCAS ");
        toFind.setUcasCode(stdin.readLine());
        try
        {
            found = (SortableStudent)studentList.getItem(toFind);
            System.out.println("Found " + found);
        }
        catch (ListException e)
        {
            System.out.println("Could not locate student");
        }

        // Let's now look at the iterator interface

        SortableStudent temp;

        Iterator theIter = studentList.elements();

        while (theIter.hasNext())
        {
            temp = (SortableStudent) theIter.next();
            System.out.println("Iterating -> " + temp.getUcasCode() + "\n");
        }

    }
}