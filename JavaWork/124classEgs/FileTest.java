import java.io.*;       // gives access to general io facilities

/**
 * A class to demonstrate simple file IO
 * @author Mark Ratcliffe
 * @version Last revision by Mark Ratcliffe 18/06/98
 */
class FileTest
{                                               
    public static void main (String [] args) throws IOException 
    {
        // Lets get a handle on a directory
        File myDir = new File ("D:\\Javawork");

        if (!myDir.isDirectory())
        {
            System.out.println("This is not a directory");
            return;
        }
        System.out.print("The directory " + myDir);
        if (myDir.exists())
        {
            System.out.println(" exists\n");
        }
        else
        {
            System.out.println(" does not exist\n");
            return;
        }

        // And now lets get a handle on a file
        File myFile = new File (myDir, "Test.txt");

        System.out.print("Within the directory, the file " 
                         + myFile);
        if (myFile.exists())
        {
            System.out.println(" exists\n");
        }
        else
        {
            System.out.println(" does not exist\n");
        }
    }
}

