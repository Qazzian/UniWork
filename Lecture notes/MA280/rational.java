// Java to obtain exact representation of the rational q, 
// where the value of q =  q.num / q.den
import java.applet.Applet;
import java.math.*;
import java.io.*;

public class MyApp extends Applet {
	
    public static void main(String args[]) {
      	MyApp f = new MyApp();
    }

    public MyApp(){  //This routine sets up problem values and calls for the answers
	Rational thisNum;
		thisNum = new Rational( 22, 0 );
		System.out.println( "the rational " + thisNum + " is " + thisNum.giveAnswer());
		thisNum = new Rational( 0, 7 );
		System.out.println( "the rational " + thisNum + " is " + thisNum.giveAnswer());
		thisNum = new Rational( 201, 8 );
		System.out.println( "the rational " + thisNum + " is " + thisNum.giveAnswer());
		thisNum = new Rational( 22, 7 );
		System.out.println( "the rational " + thisNum + " is " + thisNum.giveAnswer());
		thisNum = new Rational( 223, 71 );
		System.out.println( "the rational " + thisNum + " is " + thisNum.giveAnswer());
		thisNum = new Rational( 355, 113 );
		System.out.println( "the rational " + thisNum + " is " + thisNum.giveAnswer());
		thisNum = new Rational( 256, 81 );
		System.out.println( "the rational " + thisNum + " is " + thisNum.giveAnswer());
    } 
}



class Rational extends Object{
// Defines a Rational as the ratio of two integers num and den
// The value of the rational is num/den

   int num;
   int den;
   boolean gotAnswer = false;
   int integerPart = 0;
   int [] digits = new int [200]; // array of ints to hold the solution
   int lastDigit = 0;
   boolean recurring = false;       // becomes true if number recurs
   int firstRepeat = 0;             // where the recurrence starts in digits
   int secondRepeat = 0;            // where the recurrence finishes in digits

   public Rational(int newNum, int newDen ) {
   // Only want to set up the values on construction
      num = newNum;
      den = newDen;      
   }
   
   public String toString(){
   // Returns a String value for the Rational in the form "num/den"
      return( "(" + num + "/" + den + ")");
   }
   
   
   public String concatenateDigits( int first, int last ) {
      // Recursively call to build up the string
      if (last == first) { return( Integer.toString(digits[last]));}
      return( concatenateDigits(first, last-1) + digits[last] );   
   }
   
   public String giveAnswer(){
   String theAnswer;
   String theRecurrence;
      // First check that denominator isn't zero
      if (den == 0) {return("INFINITY");}
      // Now check for answer of zero
      if (num == 0) {return("ZERO");}
       // Make sure we know the answer
      if (gotAnswer == false) { 
          calcAnswer();
      }
      
      // Now turn it into a string
      theAnswer = Integer.toString(integerPart) + "." + concatenateDigits(1,lastDigit);
      if (recurring) {
         theRecurrence = " with repeating digits " 
                          + concatenateDigits( firstRepeat, secondRepeat );
      } else { theRecurrence = " exactly"; }
      
      return( theAnswer + theRecurrence );
   }
   
   public void calcAnswer(){
   //Calculate the value of the rational, storing the answer in array digits
   // Return false if it isn't a rational
   int fractionalNum = num;
   int fractionalDen = den;
   boolean stillRemainder = true;
   int digitIndex = 0; // index into arrays digits and remainder
   int [] remainders = new int [200] ; // Array to hold the remainders of each division
      
      gotAnswer = true; // Only want to calculate this once!!
      // if number is greater than 1, split into integer part and fractional part
      if (num >= den) {
         integerPart = num / den;
         fractionalNum = num - den * integerPart;
      }
      
      // Now just need to work out the fractional part
      remainders[digitIndex] = fractionalNum;
      
      // Loop until either number recurs or run out of number.
      while ( (recurring == false) && (stillRemainder)) {
         digitIndex++;
         // Calculate the next digit and the new remainder from the last remainder
         digits[digitIndex] = (10* remainders[digitIndex-1]) / fractionalDen ;
         remainders[digitIndex] = (10 * remainders[digitIndex-1]) 
                                   - ( digits[digitIndex] * fractionalDen);
         
         // See whether remainder is now zero. If it is, we have the answer
         if (remainders[digitIndex] == 0) {
            stillRemainder = false;
         } else { // Check for repeating digits
            for (int i = 0; i<digitIndex-1; i++){
               if (remainders[i] == remainders[digitIndex-1]) {
                  recurring = true;
                  firstRepeat = i+1;
                  secondRepeat = digitIndex-1;
               }
            } 
         }
         lastDigit = digitIndex;
      }
   }
}