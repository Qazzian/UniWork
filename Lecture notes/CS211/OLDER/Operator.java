/* Operator.java version 1.1 13 SEP 2000
 * Copyright 2000, Fred Long, all rights reserved
 *
 * Class to represent infix operators and their precedence
 *
 */

public class Operator implements Comparable
{
  private char symbol;
  private int precedence;

  /* Constructor
   *
   * @param theSymbol     - the symbol of the operator
   * @param thePrecedence - the precedence of the operator
   *
   */
  public Operator (char theSymbol, int thePrecedence)
  {
    symbol = theSymbol;
    precedence = thePrecedence;
  }

  public char getSymbol ()
  {
    return symbol;
  }

  public int getPrecedence ()
  {
    return precedence;
  }

  /* method to compare two operators
  *
  * @param otherOperator - the operator being compared with this
  *
  * @return - an int value, -1, 0, or +1 depending on whether the
  *           operators compare less than, equal or greater than
  *
  */
  public int compareTo (Object otherOperator)
  {
    // Create two Integers representing the precedences of the two operators
    // since compareTo requires Objects
    Integer thisPrecedence = new Integer (precedence);
    Integer otherPrecedence =
      new Integer ( ((Operator)otherOperator).getPrecedence () );

    return thisPrecedence.compareTo(otherPrecedence);
  }

  public String toString ()
  {
    // valueOf converts a character to a String
    // precedence, an int, is automatically converted to a String
    return String.valueOf (symbol) + precedence;
  }

}