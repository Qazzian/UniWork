/* Postfix.java version 1.0 12 SEP 2000
 * Copyright 2000, Fred Long, all rights reserved
 *
 * Program to convert infix expressions into postfix
 * It uses the Stack and Operator classes
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Postfix
{

  /* Main method reads input infix expressions and outputs
   * the corresponding postfix expression
   *
   * @param args - command line arguments (not used)
   *
   */
  public static void main (String [] args) throws IOException
  {

    // Create the stack
    Stack theStack = new Stack ();

    // Set up the input reader
    BufferedReader reader = new BufferedReader
      (new InputStreamReader (System.in));

    // Process the input - read a line at a time
    // Terminate on an empty line
    while (true)
    {
      
      System.out.print ("Infix expression: ");
      String input = reader.readLine ();

      if (input.length () == 0) break;

      // Process each character in the input
      for (int i=0; i < input.length (); i++)
      {

        char ch = input.charAt (i);

        switch (ch)
        {

          // '+' or '-' - deal with an operator of precedence 1
          case '+' :
          case '-' :
            process (theStack, ch, 1);
            break;

          // '*' or '/' - deal with an operator of precedence 2
          case '*' :
          case '/' :
            process (theStack, ch, 2);
            break;

          // '(' - just push the open bracket onto the stack
          case '(' :
            theStack.push (new Operator (ch, 0));
            break;

          // ')' - while the top of the stack is not an open bracket,
          // pop and output the stack
          // pop, but do not output, the open bracket
          case ')' :
            char thisToken = ' ';
            do {
              thisToken = ((Operator)theStack.pop ()).getSymbol ();
              if (thisToken != '(')
              {
                System.out.print (thisToken);
              }
            } while (thisToken != '(');
            break;

          // anything else - just output it
          default :
            System.out.print (ch);
        }
      }

      // At end of line - empty the stack and end the line
      while (!theStack.isEmpty ())
      {
        System.out.print ( ((Operator)theStack.pop ()).getSymbol () );
      }
      System.out.println ();
    }
  }

  /* Auxiliary method to deal with operator tokens
   *
   * @param theStack   - the operator stack
   * @param token      - the operator symbol
   * @param precedence - the operator precedence
   *
   */
  private static void process (Stack theStack, char token, int precedence)
  {

    // Create a new operator
    Operator theOperator = new Operator (token, precedence);

    // While the stack is not empty, check for operators of
    // higher or equal precedence, and pop and output them
    while ( (!theStack.isEmpty ()) &&
            (( ((Operator)theStack.peek ()).compareTo (theOperator) ) >= 0) )
    {
      System.out.print ( ((Operator)theStack.pop ()).getSymbol () );
    }

    // Now push the new operator onto the stack
    theStack.push (theOperator);

  }

}