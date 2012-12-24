import java.io.*;
import java.util.Iterator;

/**
 * ListTest is a test routine for the List class
 * @author class of CS12420
 * @version First written 01/03/99
 */
public class ListTest
{
    public static void main (String [] args)
        throws IOException, ListFullException
    {
        GenericList studentList = new GenericList();

        BufferedReader stdin =  new BufferedReader(
                                 new InputStreamReader(System.in));

        System.out.println("Enter you string now please");

        theString = stdin.readLine();  // throws IOException

        String theName;
        String theAddress;
        String theUCAS;
        Student theStudent;
        for (int i=0; i < 3; i++)
        {
             System.out.println("Enter name ");
             theName = stdin.readLine();

             System.out.println("Enter address ");
             theAddress = stdin.readLine();

             System.out.println("Enter UCAS ");
             theUCAS = stdin.readLine();

             theStudent = new Student();
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
                System.out.println(((Student)studentList.getItem(i))
                                                   .getUcasCode());
            }
        }
        catch (ListException e)
        {
            System.out.println("A problem with the list");
            e.printStackTrace();  // display problem but continue
        }
        // Lets test the isEqual interafce with getItem
        Student found;
        Student toFind = new Student();
        System.out.println("Enter UCAS ");
        toFind.setUcasCode(stdin.readLine());
        try
        {
            found = (Student)studentList.getItem(toFind);
            System.out.println("Found " + found);
        }
        catch (ListException e)
        {
            System.out.println("Could not locate student");
        }

        // Let's now look at the iterator interface

        Student temp;

        Iterator theIter = studentList.elements();

        while (theIter.hasNext())
        {
            temp = (Student) theIter.next();
            System.out.println("Iterating -> " + temp.getUcasCode() + "\n");
        }

    }
}