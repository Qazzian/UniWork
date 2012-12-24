/**
 * FanHeater
 * @author  Class of CS12220
 * @version 05/11/99
 */

public class FanHeater
{

     private int fanStatus = 0;

     /**
      * setStatus allows a user to change the status of a fan
      * @param theFanStatus the new value to be set
      */
     public void setStatus(int theFanStatus)
     {
         fanStatus = theFanStatus;
     }

     /**
      * getStatus allows a user to get the status of a fan
      * @return int the current status of the fan
	  */
     public int getStatus()
     {
         return fanStatus;
     }

     /**
      * toString returns a formatted string indicating status
      * @return String representing current status of fan
      */
     public String toString()
     {
         String tempString;
         tempString = "The fan status is ";
         switch (fanStatus)
         {
             case 0:
                 return tempString + "off";
             case 1:
                 return tempString + "low";
             case 2:
                 return tempString + "medium";
             case 3:
                 return tempString + "high";
             default:
                 return "The fan is broken";
         }
     }

     /**
      * main program is just used for simple test purposes
      */
     public static void main(String [] args)
     {
         FanHeater fan1;
		 fan1 = new FanHeater();

         FanHeater fan2;
		 fan2 = new FanHeater();

         FanHeater temp;
         temp = fan1;

         fan1.setStatus(2);  // turn fan1 on medium
         fan2.setStatus(4);  // erroneous value

         int tempNum  = fan1.getStatus();

         System.out.println("\nFan 1: " + fan1);
         System.out.println("Fan 2: " + fan2);

         fan1 = fan2;

         System.out.println("\nFan 1: " + fan1);
         System.out.println("Fan 2: " + fan2);

         fan1.setStatus(0); // turn fan1 off

         System.out.println("\nFan 1: " + fan1);
         System.out.println("Fan 2: " + fan2);
     }

}












