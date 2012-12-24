import aber.util.TextInputReader;
import aber.util.TextIOException;
import java.io.*;        //required for saving to file

/**
 * A class to test the Stack class
 * @author Mark Ratcliffe
 * @version Last revision by Mark Ratcliffe 18/06/98
 */

public class TestStack
{
    private Stack theStack;

    public void initialiseFromFile(String fileName)
    {
        try
        {
            // low level input - passed as bytes
            // buffering minimises IO overheads
            // highest level - passed as objects
            ObjectInputStream myStream =
                new ObjectInputStream(
                new BufferedInputStream(
                new FileInputStream(fileName)));

            System.out.println("Reading from the file now....");

            // Read the object from file - it needs to be cast

            theStack = (Stack) myStream.readObject();
        }
        catch(Exception e)
        {
            System.out.println("Error in loading from file: "
                                 + "Creating new stack");
            theStack = new Stack(10);

        }
    }

    public void saveToFile(String fileName)
    {
        try
        {
            // Lets get a handle on the file to write to
            File myFile = new File (fileName);

            ObjectOutputStream myStream =
                    new ObjectOutputStream(
                    new BufferedOutputStream(
                    new FileOutputStream(myFile)));

            System.out.println("Writing data to the file now....");

            myStream.writeObject(theStack);        // Write object to file
            myStream.close();
        }
        catch(Exception e)
        {
            System.out.println("Error in saving to file");
        }
    }


    public void test() throws TextIOException, Exception
    {

        String studentName;
        Student theStudent;
        
        TextInputReader t = new TextInputReader();

        System.out.println("Let's see if stack is empty - testing files");
        // loop until stack is empty
        while (theStack.getDepth() != 0)
        {
            // we must cast Object into Module
            theStudent = (Student) theStack.pop();
            System.out.println(theStudent.getSurname());
        }

        // Put some data on stack
        for (int i=0; i<5; i++)
        {
            System.out.println("Please type in surname of student");
            studentName = t.readString();
                
            // now add to the stack
            theStudent= new Student();
            theStudent.setSurname(studentName);
            theStack.push(theStudent);
        }

        // loop until stack is empty
        while (theStack.getDepth() != 0)
        {
            // we must cast Object into Module
            theStudent = (Student) theStack.pop();
            System.out.println(theStudent.getSurname());
        }

        // Put more on the stack for later (for testing files)
        for (int i=0; i<3; i++)
        {
            System.out.println("Please type in surname of student");
            studentName = t.readString();

            // now add to the stack
            theStudent= new Student();
            theStudent.setSurname(studentName);
            theStack.push(theStudent);
        }
    }

    public static void main( String [] args) throws TextIOException,
                                                    Exception
    {
        TestStack myTest = new TestStack();
        myTest.initialiseFromFile("StackData");
        myTest.test();
        myTest.saveToFile("StackData");
    }
}

