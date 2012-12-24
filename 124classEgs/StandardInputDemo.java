import java.io.*;

public class StandardInputDemo
{
    public static void main(String args[]) throws IOException
    {
        String theString;

        BufferedReader stdin =  new BufferedReader(
                                 new InputStreamReader(System.in));

        System.out.println("Enter your string now please");

        theString = stdin.readLine();  // throws IOException
        System.out.println("You entered ***" + theString + "***");

        System.out.println("Enter you integer now please");
        theString = stdin.readLine();  // throws IOException
        int theNum = Integer.parseInt(theString);// throws NumberFormatException
        System.out.println("You entered ***" + theNum + "***");

        System.out.println("Enter you double now please");
        theString = stdin.readLine();  // throws IOException
        double theDouble = Double.parseDouble(theString);// throws NumberFormatException
        System.out.println("You entered ***" + theDouble + "***");
    }
}
