// package aber.util;

import java.io.*;
import aber.util.TextInputReader;
import aber.util.TextIOException;

/**
 * Stack provides a full abstraction of the Stack data structure
 * @author Mark Ratcliffe
 * @version First written 22 January 1998
 */

public class Stack implements Serializable
{
    /**
     * maxSizeOfStack is prespecified size of Stack
     */
    public int maxSizeOfStack = 10;

    // current number of Objects in array
    private int currentDepth = 0;
    
    private Object[] theItems;        // Declare but don't define

    /**
     * default constructor sets Stack to pre-specified size
     */
    public Stack ()
    {
        // define size of array
        theItems = new Object[maxSizeOfStack];
    }

    /**
     * User is required to specify maximum depth of stack
     * @param numOfItems maximum size of stack
     */
    public Stack (int numOfItems)
    {
        theItems = new Object[numOfItems];
        maxSizeOfStack = numOfItems;
    }

    /**
     * Informs user how many items are on the stack
     * @return int the depth of the stack
     */
    public int getDepth()
    {
        return currentDepth;
    }
    
    /**
     * push enables a user to add a new item on to the stack
     * @param newItem the object to be added to top of stack
     * @exception Exception thrown if stack is
     * already full then an Exception is raised
     */
    public void push(Object newItem) throws Exception
    {
         if (currentDepth == maxSizeOfStack)
         {
             throw new Exception("Stack is already full");
         }
         // add the item into the stack

         theItems[currentDepth] = newItem;
         currentDepth++;
    }
    
    /**
     * pop passes back object at the top of the stack
     * @param Object item removed from the top of the stack
     * @exception java.lang.Exception
     * - if the stack is empty an Exception will be raised
     */
    public Object pop() throws Exception
    {
        Object tmp;
        if (currentDepth == 0)
        {
            throw new Exception("Stack is empty");
        }

        currentDepth--;
        tmp = theItems[currentDepth];
        theItems[currentDepth] = null;
        return tmp;
    }

    /** 
     * Returns a reference to object at top of stack
     * - it does not remove the item from the stack
     * @return Object the item currently at top of stack
     * @exception java.lang.Exception thrown if stack empty
     * an Exception will be raised
     */
    public Object topOf() throws Exception
    {
        if (currentDepth == 0)
        {
            throw new Exception("Stack is empty");
        }
        return theItems[currentDepth-1];
    }
    
    /**
     * Calls toString on every object is the stack
     */
    public String toString()
    {
        String tempString = "Top ";

        for (int i=currentDepth-1; i >= 0; i--)
        {
            tempString = tempString +
			             "\t" +
			             theItems[i] +
						 "\n";
        }
        return tempString;

    }
    
    /**
     * Returns true if contents of stack (& size) are equal.
     * Object references are compared, not their contents
     * @param other the stack to be compared with
     * @return boolean true if stacks contain same objects, in order
     * 
     */
    public boolean equals(Stack other) 
    {
        if (this.currentDepth != other.currentDepth)
        {
            return false;
        }

        for (int i =0;i < this.currentDepth; i++)
        {
            if (!this.theItems[i].equals(other.theItems[i]))
            {
                return false;
            }
        }
        // if we got this far, the stacks are equal

        return true;
    }

    /*
     * writeToFile writes the object to a specified file
     *
     */
    public void writeToFile (String fileLocation)
                throws IOException
    {
        File myFile = new File(fileLocation);

        ObjectOutputStream myStream = new ObjectOutputStream(
                                      new BufferedOutputStream(
                                      new FileOutputStream(myFile)));
        myStream.writeObject(this);
        myStream.close();
    }

    public static Stack readFromFile (String fileLocation)
                throws IOException
    {
        File myFile = new File(fileLocation);
        if ((!myFile.isFile()) || (!myFile.canRead()))
        {
            // Let's leave
            throw new IOException("Path is not valid ....");
        }


        ObjectInputStream myStream = new ObjectInputStream(
                                      new BufferedInputStream(
                                      new FileInputStream(myFile)));

        Stack temp;
        // Read the object from file - it needs to be cast
        try
        {
            temp = (Stack)myStream.readObject();
        }
        catch(ClassNotFoundException e)
        {
            throw new IOException("Object is of incorrect class");
        }
        catch(InvalidClassException e)
        {
            throw new IOException("Class definition changed");
        }

        myStream.close();
        return temp;
    }
}
