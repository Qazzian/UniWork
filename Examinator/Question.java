import java.io.*;

/**
 * Quetion represents a multiple choise Question. It contains a String 
 * display which is the Actual written Question, a correct Answer for the 
 * Question and a list of incorrect Answers from which to choose from.
 *
 * @see Answer
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

public class Question implements Serializable
{
	/**
	 * The String which displays the actual Question.
	 */
	protected String display;
	
	/**
	 * The correct Answer.
	 */
	protected Answer correctA;
	
	/**
	 * A List of incorrect Answers.
	 */
	protected Answer[] wrongAs;
	
	/**
	 * A count of the number of wrong Answers
	 */
	protected int answerCount = 0;
	
	
				      /****************
				       * Constructors *
				       ****************/
	
	/**
	 * Defult constructor. Creates an Empty Question.
	 */
	public Question()
	{
	}
	
	/**
	 * Creates a Question with a User defined Display String.
	 * @param s The display String.
	 */
	public Question(String s)
	{
		display = s;
		wrongAs = new Answer[5];
	}
	
	/**
	 * Creates a Question with a user defined display String and a List of 
	 * Wrong answers with a specified Size.
	 * @param s The display String.
	 * @param theSize The Size for the List of Wrong Answers
	 */
	public Question(String s,int theSize)
	{
		display = s;
		wrongAs = new Answer[theSize];
	}
	
	
				      /***********
				       * Methods *
				       ***********/
	
	/**
	 * Sets the Display Sting for the Question.
	 * @param s The Display String.
	 */
	public void setDisplay(String s)
	{
		display = s;
	}
	
	/**
	 * Returns The Display String for the Question.
	 * @return The display String.
	 */
	public String getDisplay()
	{
		return display;
	}
	
	/**
	 * Sets the Correct Answer for the Question.
	 * @param a the correct answer for this question.  Make sure the answer 
	 * is correct other wise it will not be accepted.
	 * @exception AnswerInvalidException if the Answer is not correct.
	 */
	public void setCorrectAns(Answer a) throws AnswerInvalidException
	{
		// check that the answer is correct
		if( !a.getCorrect() )
		{
			throw new AnswerInvalidException("The Answer is not correct!");
		}
		// now set it as the correct answer
		correctA = a;
	}
	
	/**
	 * Returns the correct Answer set for this Question.
	 * @return The correct Answer.
	 */
	public Answer getCorrectAns()
	{
		return correctA;
	}
	
	/**
	 * Adds a wrong Answer to the List.
	 * @param a The wrong answer
	 * @exception AnswerInvalidException if the Answer is correct
	 * @exception ListOverflowExceptiion if the max number of Answers has 
	 * been reached
	 */
	public void addWrongAns(Answer a) throws AnswerInvalidException
	{
		// check that the Answer list is not full
		if( answerCount == wrongAs.length )
		{
			throw new AnswerInvalidException("There are too many answers " +
									"to this question!");
		}
		// Check that the Answer is false.
		if( a.getCorrect() )
		{
			throw new AnswerInvalidException("The answer is not false!");
		}
		wrongAs[answerCount] = a;
		answerCount++;
	}
	
	/**
	 * Returns the wrong Answer at the given index.
	 * @param theIndex The index of the Answer.
	 * @return The answer at that index.
	 * @exception IndexOutOfBoundsException if the index is not valid.
	 */
	public Answer getWrongAns(int theIndex) throws IndexOutOfBoundsException
	{
		// check that the index is valid
		if( (theIndex < 0) || (theIndex > answerCount) )
		{
			throw new IndexOutOfBoundsException("There is not an answer " + 
							"at that index!");
		}
		return wrongAs[theIndex];
	}
	
	/**
	 * Returns the Array of wrong answers.
	 * @return The Array of all the Wrong answers.
	 */
	public Answer[] getAllWrongAns()
	{
		return wrongAs;
	}
	
	/**
	 * Returns and removes the answer at the given index.
	 * @param indx The index of the Answer to remove.
	 * @return the Answer that was at that index.
	 * @exception IndexOutOfBoundsException if the index is not valid.
	 */
	public Answer removeWrongAns(int indx) throws IndexOutOfBoundsException
	{
		// check that the index is valid
		if( (indx < 0) || (indx > answerCount) )
		{
			throw new IndexOutOfBoundsException("There is not an answer " + 
							"at that index!");
		}
		Answer tmpA = wrongAs[indx];
		int i = indx;
		// this keeps the Answers in the same order
		while(i < answerCount)
		{
			wrongAs[i] = wrongAs[i++];
		}
		answerCount--;
		return tmpA;
	}
	
	public int getAnswerCount()
	{
		return answerCount;
	}
	
	public String toString()
	{
		String tmpS = "\n Question = " + display + 
					"\n The correct Answer is; " + correctA.getDisplay() +
					"\n This question has " + answerCount + 
					" wrong Answers.  \n They are;";
		for(int i=0; i < answerCount; i++)
		{
			tmpS += "\n" + wrongAs[i].getDisplay();
		}

		return tmpS;
	}
	
	public static void main(String[] args) throws Exception
	{
		Question[] bank = new Question[5];
		int i = 0;
		while(i < 5)
		{
			bank[i] = new Question("This is Question " + i);
			Answer ac = new Answer("This is the correct one.",true);
			bank[i].setCorrectAns(ac);
			int j = 0;
			while(j < 5)
			{
				Answer tmpA = new Answer("This is Answer " + i + j);
				bank[i].addWrongAns(tmpA);
				j++;
			}
		System.out.println( bank[i].toString() );
		i++;
		}
		
		/**
		
		The results of the test
		^^^^^^^^^^^^^^^^^^^^^^^
		
		 The correct Answer is; This is the correct one.
		 This question has 5 wrong Answers.
		 They are;
		This is Answer 00
		This is Answer 01
		This is Answer 02
		This is Answer 03
		This is Answer 04

		 Question = This is Question 1
		 The correct Answer is; This is the correct one.
		 This question has 5 wrong Answers.
		 They are;
		This is Answer 10
		This is Answer 11
		This is Answer 12
		This is Answer 13
		This is Answer 14

		 Question = This is Question 2
		 The correct Answer is; This is the correct one.
		 This question has 5 wrong Answers.
		 They are;
		This is Answer 20
		This is Answer 21
		This is Answer 22
		This is Answer 23
		This is Answer 24

		 Question = This is Question 3
		 The correct Answer is; This is the correct one.
		 This question has 5 wrong Answers.
		 They are;
		This is Answer 30
		This is Answer 31
		This is Answer 32
		This is Answer 33
		This is Answer 34

		 Question = This is Question 4
		 The correct Answer is; This is the correct one.
		 This question has 5 wrong Answers.
		 They are;
		This is Answer 40
		This is Answer 41
		This is Answer 42
		This is Answer 43
		This is Answer 44

		C:\Aber_stuff\JAVAWORK\Project3>

		*/
	}
}