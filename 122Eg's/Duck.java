/**
 * Duck demonstrates inheritance
 *
 * @author Class of 1999
 * @version 13/12/99
 */

public class Duck extends Animal
{
    protected int lengthOfBeak;
    protected String colourOfFeathers;

    public Duck(int theBeakLength)
    {
        super(2,"wobbles");
        lengthOfBeak= theBeakLength;
    }

    public String makeNoise()
    {
        return "quack";
    }

    public String toString()
    {
        String tempString = "";
        tempString = "The beak is " + lengthOfBeak
                     + "\n The num of legs is " + numOfLegs
                     + " and it " + styleOfWalk + "\n";
        return tempString;
    }


}