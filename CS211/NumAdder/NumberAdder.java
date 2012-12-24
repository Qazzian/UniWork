import java.util.*;
import java.io.*;

/**
 * Number adder takes a LinkedList of numbers and adds them all to a total.
 *
 * @author <A HREF=mailto:"ifw9@aber.ac.uk"?subject=NumberAdder">Ian Wallis</A>
 * @version 1.1  Initial development.
 */

public class NumberAdder{
	
   // ////////////// //
   // Class methods. //
   // ////////////// //

	/**
	 * Finds the total of all the integers within the give LinkedList.
	 * This class wide method creates an instance of this class and calls the local
	 * <code>performSum</code> method, returning the answer.
	 * @param aList The LinkedList to perform the sum on.
	 * @return the total of the sum.
	 */
	public static int performSum(LinkedList aList){
		NumberAdder anAdder = new NumberAdder(aList);
		return anAdder.addNext(aList);
	}
	
	// /////////////////// //
	// Instance variables. //
	// /////////////////// //
	
	/** The LinkedList of numbers to be added together. **/
	protected LinkedList numList;
	
	/** The Answer of the last summing. **/
	protected int theSum = 0;
	
	protected int currentPos = 0;
	
	// ///////////// //
   // Constructors. //
   // ///////////// //

	/**
	 * Creates an empty NumberAdder.
	 */
	public NumberAdder(){
	}
	
	/**
	 * Creates a NumberAdder with a specified LinkedList.
	 * @param aList The LinkedList which will be added together.
	 */
	public NumberAdder(LinkedList aList){
		numList = aList;
	}

   // ////////////////////// //
   // Read/Write properties. //
   // ////////////////////// //

	/**
	 * Changes the list to be added together.
	 * @param aList the new List to be added together.
	 */
	public void setList(LinkedList aList){
		numList = aList;
		theSum = 0;
	}
	
	/**
	 * Returns the current list stored in the NumberAdder. Usefull for editing the List.
	 * @return The list which is currently stored in the NumberAdder.
	 */
	public LinkedList getList(){
		return numList;
	}
	
   // ///////////////////// //
   // Read-only properties. //
   // ///////////////////// //

	/**
	 * Returns the total of the last performed sum, 0 if no sum has been performed.
	 * @return The total of the last sum.
	 */
	public int getSum(){
		return theSum;
	}
   
   // //////// //
   // Methods. //
   // //////// //

	/**
	 * Adds all the numbers in the stored LinkedList
	 */
	public int addAll(){
		return addNext(numList);
	}
	
	/**
	 * Adds all the numbers of a LinkedList starting with the given node.
	 * If the data Object is not a Number then method will goon to the next Node.
	 * @param theList The LinkedList to sum up.
	 * @return
	 */ 
	public int addNext(LinkedList theList){
		Object tmpO = theList.get(currentPos);
		Number num = null;
		if(num.getClass().isInstance(tmpO)){ // Check that the data is a Number.
			num = (Number)tmpO;		// Then cast it to a Number
			theSum += num.intValue();		//	Add the int value of the Number to the total.
		} 											// Is this the last Object in the list?
		if(tmpO != theList.getLast()){ 	// If not
			currentPos++;						// Go to the next
			addNext(theList);
		}
		currentPos = 0;	// reset the position counter
		return theSum;		// and Exit the method
	}


   // ///////////// //
   // Test methods. //
   // ///////////// //

	
   public static void main( String argv[] ) {
      try {
			LinkedList aList = new LinkedList();
			for(int i=0; i<6; i++){
				aList.add( new Integer(i) );
				System.out.println("putting "+i+" in the List.");
			}
			NumberAdder na = new NumberAdder(aList);
			System.out.println("Now lets add them together.");
			System.out.println(na.addAll());
			
			
      } catch( Exception e ) {
         e.printStackTrace( );
      }

   }
}