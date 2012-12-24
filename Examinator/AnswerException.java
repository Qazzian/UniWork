/**
 * An exception for when ther is a problem with a particular Answer.
 * 
 * @see Answer
 * @see Question
 *
 * @author Ian Wallis 	
 * @email: ifw9@aber.ac.uk or Qaz_Wallis@yahoo.com
 * @version last updated 31 March 2000
 * @since jdk 1.2
 *
 * This code is the property of me, Ian Wallis.  You are free to use this 
 * code in your own projects but I ask that you aknowledge my part in them  
 * and that you include my original code, or the means to get it, with your 
 * code. I accept no responsibility for any damage caused in any way by my 
 * code and I assure you that if it does cause damage then it not 
 * intensional.  I would also be gratefull for any comments or suggestions
 * that you have regarding my code.  <BR>
 * Thank you.
 */

public class AnswerException extends Exception
{
	public AnswerException()
	{
		super("There is a problem with the answer!");
	}
	
	public AnswerException(String s)
	{
		super(s);
	}
}