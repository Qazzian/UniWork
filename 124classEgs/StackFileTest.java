import java.io.*;

public class StackFileTest
{
    public static void main (String [] args) 
	    throws IOException, Exception
    {
         String theString;

         BufferedReader stdin =  new BufferedReader(
                                 new InputStreamReader(System.in));
         Stack temp;

         try
         {
             temp = Stack.readFromFile("temp");
         }
         catch (IOException e)
         {
             temp = new Stack(15);
         }

         for (int i=0; i<3; i++)
         {
             System.out.println("Enter a string ");
             theString = stdin.readLine();
             temp.push(theString);
         }

         temp.writeToFile("temp");

    }
}