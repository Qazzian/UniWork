/**
 * Animal demonstrates inheritance
 *
 * @author Class of 1999
 * @version 13/12/99
 */

public abstract class Animal  // it's abstract - you can't create instance
{
    protected  int numOfLegs = 0;
    protected String styleOfWalk;

    public Animal(int theLegs,
                  String theWalk)
    {
        numOfLegs = theLegs;
        styleOfWalk = theWalk;
    }

    public abstract String makeNoise(); // it's abstract - must be redefined

}