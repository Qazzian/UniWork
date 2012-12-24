import Qaz.util.*;

/**
 * A list class to store an Array of Answers.
 * 
 * @see Answer
 * @see GinericList
 *
 * @author Ian Wallis 	
 * @email: ifw9@aber.ac.uk or Qaz_Wallis@yahoo.com
 * @version last updated 4 April 2000
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

public class AnswerList extends List
{
	/**
	 * Calls the defult Constuctor of <code>GenericList</code> creating  
	 * a list of defult size.
	 */
	public AnswerList()
	{
		super();
	}
	
	/**
	 * Calls the Constructor of <code>GenericList</code> creating a List 
	 * with the specified size.
	 * @param theSize The size of the List.
	 */
	public AnswerList(int theSize)
	{
		super(theSize);
	}
	
	/**
	 * Adds an Answer to the list by calling the addItem in GenericList.
	 * @param newAns the Answer to be added.
	 * @exception ListOverflowException If the list is full.
	 */
	public void addItem(Answer newAns) throws ListOverflowException
	{
		super.addItem(newAns);
	}
	
	/**
	 * Overides the addItem method in GenericList so only Answers can be 
	 * added to the list
	 * @param newItem The Item which you are trying to add to the List 
	 * but will not succed because it's not an Answer!
	 * @excepton ListItemInvalidException the reson you can't add a non - 
	 * Ansewr.
	 */
	public void addItem(Listable newItem) throws ListItemInvalidException
	{
			throw new ListItemInvalidException("That Object is not an " +
							"instance of Answer!");
	}
	
	/**
	 * Overides the toString in <code>GenericList</code> to make it apply
	 * to Answers.
	 * @return details of the answers in the List and how many are in the 
	 * List.
	 */
	public String toString()
	{
		String tmpS = "";
		for (int i=0; i < currentLength; i++)
		{
			tmpS += theItems[i] + "\n";
		}
		tmpS += "There are " + currentLength + " Answers in the List.";
	}
	
	public static void main(String[] args)
	{
		// create an AnswerList
		AnswerList al1 = new AnswerList(4);
		
		// create some Answers
		Answer a1 = new Answer("Help!");
		Answer a2 = new Answer(" I need someboby,");
		Answer a3 = new Answer(" and not just anybody.");
		Answer a4 = new Answer(" I need You",true);
		
		// Add them to the List
		al1.addItem(a1);
		al1.addItem(a2);
		al1.addItem(a3);
		al1.addItem(a4);		
		
		// print a toString + some other stuff
		System.out.println (al1);
		Answer tmpA = new Answer(0);
		System.out.println (al1.getItem(tmpA) );
	}
}