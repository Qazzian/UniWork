import java.io.*;       // gives access to general io facilities

/**
 * A class to demonstrate use of file IO
 * @author Mark Ratcliffe
 * @version Last revision by Mark Ratcliffe 18/06/98
 */
class AFileReader
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

        if (!myFile.exists())
        {
            System.out.println("Missing file - abandoning...\n");
            // Let's leave
            System.exit(-1);
        }
        
        // Now create the byte output stream
        DataInputStream myStream = new DataInputStream(
                                     new BufferedInputStream(
                                     new FileInputStream(myFile)));
        
        System.out.println("Reading the file now....");
        
        System.out.println(myStream.readUTF());
        System.out.println(myStream.readInt());
        System.out.println(myStream.readDouble());
        
        myStream.close();

    }
}

