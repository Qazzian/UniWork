import aber.util.TextInputReader;
import aber.util.TextIOException;

public class ModuleDemo
{
    public void test() throws TextIOException
    {
        TextInputReader t = new TextInputReader();

        Module cs12320 = new Module("Concepts in programming",
                                    "Fred");

        String temp = t.promptForString("Enter name of coordinator ");

        cs12320.setCoordinator(temp);

        int theYear  = t.promptForInt("Please enter year of study ");

        cs12320.setYear(theYear);

        cs12320.addNewStudent();
        cs12320.addNewStudent();

        System.out.println(cs12320);

        System.out.println("Are you happy with this?");

        char myChar = t.promptForChar("Please enter \"y\" or \"n\"");

        if (myChar == 'y')
        {
            System.out.println("I\'m really happy with that");
        }
        else
        {
            System.out.println("Please yourself");
        }
    }

    public static void main(String [] args) throws TextIOException
    {
        ModuleDemo myDemo = new ModuleDemo();
        myDemo.test();
    }
}
