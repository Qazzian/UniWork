import aber.util.TextInputReader;
import aber.util.TextIOException;

/**
 * CharTest demonstrates loops
 * @author Class of Cs12220
 * @version 12/11/99
 */
public class CharTest
{
    
	public static void main(String [] args) throws TextIOException
    {
         TextInputReader t = new TextInputReader();

         char myChar = ' ';

         while (myChar != '?')
         {

             myChar = t.promptForChar("Please enter your character ");

             if ((myChar >= 'a' ) & (myChar <= 'z'))
             {
                 System.out.println("You typed in a lower case letter");
             }
         }


    }
}