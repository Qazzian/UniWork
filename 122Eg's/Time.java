/**
 * A simple class to represent time
 * @author Class of CS12220
 * @version 29/10/99
 */

public class Time
{
    private int timeInSeconds;

    public Time(int theHours, int theMins, int theSecs)
    {
       timeInSeconds = (theHours * 3600) + 
	                   (theMins * 60) +
                       theSecs;
    }

    public void add(int theHours, int theMins, int theSecs)
    {
        int tempTime = (theHours * 3600) +
		               (theMins * 60) +
					   theSecs; 
		timeInSeconds = timeInSeconds + tempTime;      
	}

    public String toString()
    {
        int hours = 0;
        int mins = 0;
        int secs = 0;

        hours = timeInSeconds / 3600;
        mins = (timeInSeconds % 3600) / 60;
        secs = (timeInSeconds % 3600) % 60;
        // work out values

        return (hours + "hrs " + mins + "mins " + secs + "secs");
    }

    public static void main (String [] args)
    {
        Time startTime = new Time(0,0,0);
        System.out.println("\n\n\nThe start time is \t:" + startTime);

        Time currentTime = new Time(1,0,0);
		currentTime.add(0,63,0);
        System.out.println("The current time is \t:" + currentTime);

        Time stopTime = new Time(16,0,0);
        System.out.println("The end time is \t:" + stopTime);

    }

}