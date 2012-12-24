/**
 * A simpe program to demonstrate for loops
 * @author  class of CS12220
 * @version 09/11/99
 */

public class TimesBlock
{
    public static void main (String [] args)
    {

        for (int outer=1;outer <= 5; outer++)
        {
            for (int count=1; count <=5; count++)
            {
                 System.out.print((count*outer) + "\t");
            }
            System.out.println();
        }
    }
}