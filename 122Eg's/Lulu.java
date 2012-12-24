import aber.util.TextInputReader;
import aber.util.TextIOException;

/**
 * A class to solve Lulu's weather dilemma
 * @author Class of CS12320
 * @version Last revision by Mark Ratcliffe 25/10/99
 */
public class Lulu 
{

	public static void main(String [] args) throws TextIOException
    {
	   boolean carAvailable = false;
	   boolean wetWeather = false;
	   boolean windyWeather = false;
	   boolean cloudySkies = false;
       
       TextInputReader t = new TextInputReader();
    
       char tempChar = 'n';     // NOTE: char not character

       tempChar = t.promptForChar("Is the car available ?");
       if ((tempChar == 'y') | (tempChar == 'Y'))    // NOTE: use of ==
       {
          carAvailable = true; 
       }

       tempChar = t.promptForChar("Is it wet?");
       if ((tempChar == 'y') | (tempChar == 'Y'))
       {
          wetWeather = true; 
       }

       tempChar = t.promptForChar("Is it windy?");
       if ((tempChar == 'y') | (tempChar == 'Y'))
       {
          windyWeather = true; 
       }

       tempChar = t.promptForChar("Is it cloudy?");
       if ((tempChar == 'y') | (tempChar == 'Y'))
       {
          cloudySkies= true; 
       }

       // And now for your bits


       if (carAvailable)
       {
           System.out.println("Take the car Lulu");
       }
       else
       {
           if ((wetWeather) | (cloudySkies & windyWeather))
           {
               System.out.println("Take the bus Lulu");
           }
           else
           {
               System.out.println("Take the bike dear");
           }
       }
	}
}
