import java.io.*;

/**
 * A class which stores a list of multiple choise Questions for use in an 
 * exam.
 *
 * @see Question
 * 
 * @author Ian Wallis 	
 * @email: ifw9@aber.ac.uk or Qaz_Wallis@yahoo.com
 * @version last updated 23 April 2000
 * @since jdk 1.2
 *
 * This code is the property of me, Ian Wallis.  You are free to use this 
 * code in your own projects but I ask that you acknowledge my part in them  
 * and that you include my original code, or the means to get it, with your 
 * code. I accept no responsibility for any damage caused in any way by my 
 * code and I assure you that if it does cause damage then it not 
 * intensional.  I would also be gratefull for any comments or suggestions
 * that you have regarding my code.  <BR>
 * Thank you.
 */

public class Exam implements Serializable
{
	/**
	 * Tha array of Questions for the Exam.
	 */
	protected Question[] bank;
	
	/**
	 * The number of Questions currently in the Exam.
	 */
	protected int qCount = 0;
	
	/**
	 * The Defult Question capacity for the Exam.
	 */
	public static final int DEF_Q_NUM = 30;
	
	
	/**
	 * Creates an Exam with a defult Question capacity.
	 */
	public Exam()
	{
		bank = new Question[DEF_Q_NUM];
	}
	
	/**
	 * Creates an Exam with a user defined Question capacity.
	 * @param i The Question capacity of the Exam.
	 */
	public Exam(int i)
	{
		bank = new Question[i];
	}
	
	/**
	 * Adds a Question to the Question bank.
	 * @param q The Question to add.
	 * @exception QuestionBankFullException if the Bank of Questions is full
	 */
	public void addQuestion(Question q) throws QuestionBankFullException
	{
		if(qCount == bank.length)
		{
			throw new QuestionBankFullException();
		}
		bank[qCount] = q;
		qCount++;
	}
	
	/**
	 * Returns the Question at the given index.
	 * @param theIndex The index of the Question.
	 * @return The Question at the given index.
	 * @exception IndexOutOfBoundsException if the index is out of range.
	 */
	public Question getQuestion(int theIndex) 
						throws IndexOutOfBoundsException
	{
		if( (theIndex < 0) || (theIndex > qCount) )
		{
			throw new IndexOutOfBoundsException
							("The Index is out of range!");
		}
		return bank[theIndex];
	}
	
	/**
	 * Removes the Question at the given index and returns a handle on it.
	 * @param theIndex The index of the Question.
	 * @return The Question which was removed from the given index.
	 * @exception IndexOutOfBoundsException if the index is out of range.
	 */
	public Question removeQuestion(int theIndex) 
						throws IndexOutOfBoundsException
	{
		if( (theIndex > qCount) || (theIndex < 0) )
		{
			throw new IndexOutOfBoundsException
							("The Index is out of range!");
		}
		Question tmpQ = bank[theIndex];
		// this keeps the Questions in the same order in the Array
		// insted of moving the last one to the gap.
		for(int i=theIndex; i<qCount; i++)
		{
			bank[i] = bank[ (i+1) ];
		}
		qCount--;
		return tmpQ;
	}
	
	/**
	 * Inserts a Question in the specified index moving the rest forward
	 * @param q The Question to be inserted
	 * @exception QuestionBankFullException if the Bank of Questions is full 
	 */
	public void insertQuestion(Question q,int theIndex) 
						throws QuestionBankFullException
	{
		if(qCount == bank.length)
		{
			throw new QuestionBankFullException();
		}
		// create a gap for the Question
		for(int i=qCount; i>theIndex; i--)
		{
			bank[i] = bank[ (i-1) ];
		}
		// now put it in the gap
		bank[theIndex] = q;
		qCount++;
	}
	
	public int getQCount()
	{
		return qCount;
	}
	
	public String getFullDisplay()
	{
		String tmpS = "";
		for(int i=0; i<qCount; i++)
		{
			tmpS += i + ".    " + bank[i].getDisplay() + "\n\n";
		}
		return tmpS;
	}
	
}