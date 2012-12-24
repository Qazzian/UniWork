import java.io.*;

/**
 * A class representing The Answer to a multiple-choise <code>Question</code>
 * Contains a String which displays what the answer is and a bolean value 
 * to say weather the answer is true or not.
 *
 * @see Question
 * @see AnswerException
 *
 * @author Ian Wallis 	
 * @email: ifw9@aber.ac.uk or Qaz_Wallis@yahoo.com
 * @version last updated 31 March 2000
 * @since jdk 1.2
 *
 * This code is the property of me, Ian Wallis.  You are free to use this 
 * code in your own projects but I ask that you aknowledge my part in them  
 * and that you include my original code, or the means to get it, with your 
 * code. I would also be gratefull for any comments or suggestions that you
 * have.
 */

public class Answer implements Serializable
{
	/**
	 * The String to be displayed for the <code>Answer</code>.  
	 * Defult value is <code>""</code>.
	 */
	protected String display = "";
	
	/**
	 * To state weather the <code>Answer</code> is true or not.  
	 * Defult value is <code>false</code>.
	 */
	protected boolean correct = false;
	


// Constructors
	
	/**
	 * Creates an <code>Answer</code> with the defult values.
	 */
	public Answer()
	{
	}
	
	/**
	 * Creats an Answer with a user defined <code>display String</code> 
	 * and the defult value for the correctness.
	 * @param s The String which will be displayed for the Answer.
	 */
	public Answer(String s)
	{
		display = s;
	}
	
	/**
	 * Creates an Answer with a user defined <code>display Sting</code>
	 * and correctness value.
	 * Use this constructor to set the Answer to be true.
	 * @param s The String which will be displayed for the Answer.
	 * @param b The boolean to say weather the Answer is true or false
	 */
	public Answer(String s,boolean b)
	{
		display = s;
		correct = b;
	}
	
	/**
	 * Changes the String which will be Displayed for the Answer.
	 * @param s The String which will be displayed for the Answer. 
	 */
	public void setDisplay(String s)
	{
		display = s;
	}
	
	/**
	 * Returns the String which is to be displayed for the Answer.
	 * @return the display String.
	 */
	public String getDisplay()
	{
		return display;
	}
	
	/**
	 * Sets the Answer to be true.
	 */
	public void setCorrect()
	{
		correct = true;
	}
	
	/**
	 * Sets the correctness of the Answer to true or false.
	 * @param  b true If the answer is to be ture.
	 * @param b false If the answer is to be false
	 */
	public void setCorrect(boolean b)
	{
		correct = b;
	}
	
	/**
	 * Sets the Answer to be false.
	 */
	public void setIncorect()
	{
		correct = false;
	}

	/**
	 * Returns the value of the Truthfullness of the answer.
	 * @return true if the answer is true.
	 * @return false if the Answer is not true.
	 */
	public boolean getCorrect()
	{
		return correct;
	}
	
	/**
	 * Tsets to see if this Answer has the Display and correctness as 
	 * the input Answer. Used by <code>GinericList</code>.
	 * @param obj The Object to test against.
	 * @return true if the Answers both have the same ID code
	 * @return false if The Answers do not have the same ID code or if 
	 * the input Object is not of class <code>Answer</code>.
	 */
	public boolean isEqual(Object obj)
	{
		// see if the classes are the same
		if( !obj.getClass().equals( this.getClass() ) )
		{
			return false;
		}
		Answer other = (Answer)obj;
		// see if the ref's are the same
		if( this.correct != other.correct)
		{
			return false;
		}
		if( this.display != other.display)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Returns a formatted String giving details of the Answers 
	 * Display String, Unique ID code and if it is true or not.
	 * @return The formatted String.
	 */
	public String toString()
	{
		String tmpS = "\n This Answer says; " + display;
		if(correct)
		{
			tmpS += "\n It is correct.";
		}
		if(!correct)
		{
			tmpS += "\n It is not correct.";
		}
		return tmpS;
	}
	
	/**
	 * A simple test harness to test that the Answer class works adequately.
	 */
	public static void main(String[] args)
	{
		// create some instances of Answer
		Answer a1 = new Answer("It's this one!");
		Answer a2 = new Answer("No, it's this one!",true);
		Answer a3 = new Answer("No, No, No,No, it's obviously this one!");
		
		// print out the displays
		System.out.println(a1.getDisplay());
		System.out.println(a2.getDisplay());
		System.out.println(a3.getDisplay());
		
		// Print the toStrings
		System.out.println(a1);
		System.out.println(a2);
		System.out.println(a3);
		
		// change some values
		a1.setDisplay("I quess it wasn't this one after all :( ");
		System.out.println(a1.getDisplay());
		a1.setCorrect();
		a2.setDisplay("or was it?");
		System.out.println (a1 + "\n" + a2);
		
		// check the isEqual method
		Answer check = new Answer("No, No, No,No, it's obviously this one!");
		boolean b1 = a1.isEqual(check);
		boolean b2 = a3.isEqual(check);
		System.out.println(b1 + "\n" + b2);
		
		/**
		
		The results of the tests
		
		C:\Aber_stuff\JAVAWORK\Project3>java Answer
	(Printing out the displays)
		It's this one!
		No, it's this one!
		No, No, No,No, it's obviously this one!
		
	(Now the toString's)
		 This Answer says; It's this one!
		 It is not correct.

		 This Answer says; No, it's this one!
		 It is correct.

		 This Answer says; No, No, No,No, it's obviously this one!
		 It is not correct.
		 
	(Changing some values)
		I quess it wasn't this one after all :(

		 This Answer says; I quess it wasn't this one after all :(
		 It is correct.

		 This Answer says; or was it?
		 It is correct.
		
	(Then the IsEqual method.)
		false
		true

		C:\Aber_stuff\JAVAWORK\Project3>
		
		*/

	}
}