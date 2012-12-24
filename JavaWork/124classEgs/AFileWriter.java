import java.io.*;       // gives access to general io facilities

/**
 * A class to demonstrate use of file IO
 * @author Mark Ratcliffe
 * @version Last revision by Mark Ratcliffe 18/06/98
 */
class AFileWriter
{                                               

    public static void main (String [] args) throws IOException 
    {
        // Lets get a handle on a directory
        File myDir = new File ("D:\\Javawork");
        
        if (!myDir.exists())
        {
            System.out.println("Missing directory - abandoning...\n");
            // Let's leave
            System.exit(-1);
        }

        // And now lets get a handle on a file
        File myFile = new File (myDir, "MBRTest.txt");
        
        // Now create the byte output stream
        DataOutputStream myStream = new DataOutputStream(
                                    new BufferedOutputStream(
                                    new FileOutputStream(myFile)));
        
        System.out.println("Writing to the file now....");
        
        myStream.writeUTF("The secret code is");
        myStream.writeInt(5);
        myStream.writeDouble(27.5);
        
        myStream.flush();       // make sure that all is written
        myStream.close();

    }
}

