import aber.util.TextInputReader;
import aber.util.TextIOException;

public class Landings
{
    private Airport myAirport = new Airport();

    public static void main (String [] args)
        throws Exception
    {

        TextInputReader t = new TextInputReader();

        Landings laurasLandings = new Landings();

        // Lets create a few planes and add to airport

        for (int i=0; i<5; i++)
        {
            Plane myPlane = new Plane();

            myPlane.setFlightNo(
                    t.promptForString("Enter flight number "));
            myPlane.setFuelInTank(
                    t.promptForInt("Enter fuel left in tank "));
            myPlane.setFuelConsumptionPerMinute(
                    t.promptForDouble("Enter fuel consumption rate "));

            laurasLandings.myAirport.addPlane(myPlane);
        }

        System.out.println("\n\nWelcome to Laura's Landings \n" +
                           laurasLandings.myAirport);

    }


}