/**
 * Martian class demonstrates use of class wide variables
 * @author class of CS12220
 * @version 7/12/99
 */

public class Martian
{

    public String name = "Unassigned";

    private static int sizeOfForce = 0;

    public Martian(String theName)
    {
        name = theName;
        sizeOfForce++;
    }

    private void finalise()
    {
        sizeOfForce--;   // reduce size of martian presence
    }


    public String getFeeling()
    {
        if (sizeOfForce <= 50)
        {
            if (sizeOfForce <=5)
            {
                return "friendly";
            }
            else
            {
                return "aggresive";
            }
        }
        return "extremely violent";
    }

    public static void main(String [] args) throws Exception
    {
        Martian [] theMartians = new Martian[52];

        Martian Billy = new Martian ("Billy");

        System.out.println("Billy "
		                   + " is feeling "
						   + Billy.getFeeling());

        for (int i=0; i < 5; i++)
        {
            theMartians[i] = new Martian("Bert");
        }

        System.out.println("After 5 more creations Billy "
		                   + " is feeling "
						   + Billy.getFeeling());

        for (int i=5; i < 52; i++)
        {
            theMartians[i] = new Martian("Bert");
        }

        System.out.println("After 52 more creations Billy "
		                   + " is feeling "
						   + Billy.getFeeling());

        System.out.println("Wiping out the Martians....");

        theMartians = null;

        Thread.sleep (50000L);

        System.out.println("After a massive wipe out Billy "
		                   + " is feeling "
						   + Billy.getFeeling());

    }
}