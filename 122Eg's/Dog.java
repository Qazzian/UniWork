/**
 * Dog demonstrates inheritance
 *
 * @author Class of 1999
 * @version 13/12/99
 */

public class Dog extends Animal
{
    protected int sizeOfMuzzle;
    protected String colourOfFur;

    public Dog(int theMuzzle)
    {
        super(4,"lopes");
        sizeOfMuzzle= theMuzzle;
    }

    public String makeNoise()
    {
        return "woof";
    }

    public String toString()
    {
        String tempString = "";
        tempString = "The muzzle is " + sizeOfMuzzle
                     + "\n The num of legs is " + numOfLegs
                     + " and it " + styleOfWalk + "\n";
        return tempString;
    }


}