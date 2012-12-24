import java.io.*;
import java.util.Iterator;

/**
 * Queue provides a full abstraction of the Queue data structure
 * @author Mark Ratcliffe
 * @date 5 February 1998
 *
 * @see QueueExceptionFull
 * @see QueueExceptionEmpty
 */

public class Queue implements Serializable, Iterator
{
    /*
     * currentLength maintains current number of items in array
     */
    protected int currentLength = 0;

    /**
     *  defaultSize defines length of queue if default constructor is used
     * - set to 10
     */
    public final int DEFAULT_SIZE = 10;

    protected Object[] theItems;        // Declare but don't define
    
    /*
     * front and back are used as pointers within the array
     */
    protected int front = 0;
    protected int back = 0;

    /*
     * The following declaration is required by iterator
     */

    protected int currentIteratorPosition;
    protected int currentIteratorCount = 0;

    /**
     * Default constructor - sets queue size to pre-specified value
     */
    public Queue ()
    {
        theItems = new Object[DEFAULT_SIZE];
    }

    /**
     * Queue constructor requires user to specify maximum size of queue
     * @param numOfItems the number of items in the queue
     */
    public Queue (int numOfItems)
    {
        // Define size of array
        theItems = new Object[numOfItems];
    }

    /**
     * Calculates length of queue
     * @return int the length of the queue
     */
    public int length()
    {
        // currentLength always contains the correct number of items
        // don't forget that the array starts at 0
        return currentLength;
    }
    
    /**
     *  Adds a new item at the back of the queue
     *  @exception QueueExceptionFullQueue thrown if the queue
     *  is already full
     */
    public void addItem(Object newItem) throws QueueExceptionFull
    {
        // check that we have space in array
        if (currentLength == theItems.length)
        {
            throw new QueueExceptionFull("Queue too full!");
        }

        // add reference to object in location at back of queue
        theItems[back] = newItem;

        // implement circular array using mod
        back = (back + 1) % theItems.length;


        // increment the index ready for next time
        currentLength++;
    }
    
    /**
     * Passes back object that is at the front of the queue
     * @return Object returned from front of queue
     * @exception QueueExceptionEmpty thrown if queue is empty
     */
    public Object removeItem() throws QueueExceptionEmpty
    {
        Object tmp;

        // first check that there is an Object in the queue
        if (currentLength == 0)
        {
            throw new QueueExceptionEmpty("Tried to remove from empty queue");
        }

        // take a reference to the Object at the front
        tmp = theItems[front];
        
        // remove Object from array
        theItems[front] = null;

        // implement circular array using mod
        front = (front + 1) % theItems.length;

        // decrement the counter 
        currentLength--;

        // now pass the value back to the user
        return tmp;
    }

    /** 
     * Passes back a reference to object at the front of queue
     * - it does not remove the item from the queue.
     * @return Object at the front of the queue
     * @exception QueueExceptionEmpty thrown if queue is empty
     */
    public Object frontOf() throws QueueExceptionEmpty
    {
        if (currentLength == 0)
        {
            throw new QueueExceptionEmpty("accessed front of empty queue");
        }

        // now pass a reference to the object back to the user
        return theItems[front];
    }

    /**
     * Simply calls toString on every object is the queue
     * @return String formateed representation of queue
     */
    public String toString()
    {
        String tempString = "";
        // we start at the front and work to the back of the queue
        // but remember, it is a circular array

        int location = front;
        for (int count = 0; count<currentLength ; count++)
        {
            // calls toString on current item
            tempString += theItems[location] + "\n";
            
            // now move to next location in circular fashion
            location = (location + 1) % theItems.length;
        }

        return tempString;
    }

    /**
     * Returns true if contents of queue (& size) are equal
     * - physical position in array is immaterial but order is important.
     * @param other the queue to be compared with
     * @return boolean indicates whether queues are equal
     */
    public boolean equals(Queue other)
    {
         if (this.currentLength != other.currentLength)
         {
             return false;
         }
         int locationInThis = this.front;
         int locationInOther = other.front;
         for (int i=0; i<this.currentLength; i++)
         {
             if (!(this.theItems[locationInThis].equals
                     (other.theItems[locationInOther])))
             { 
                 return false;
             }
             locationInThis = 
                 (locationInThis + 1) % this.theItems.length;
             locationInOther = 
                 (locationInOther + 1) % other.theItems.length;
         }
         // got this far so queues are equal
         return true;
    }

    /**
     * An Iterator interface is provided over the Queue.
     * User is provided with elements() to generate iterator.
     * next() is used to advance until hasNext() is false
     */

    /**
     * elements is used to initialise the iterator over the Queue
     * @return Iterator used to iterate over Queue
     */

    public Iterator elements()
    {
        currentIteratorCount = 0;
		currentIteratorPosition = this.front;
        return this;
    }

    /**
     * next() obtains next element (starting from front).
     * The iterator is then advanced to next element in list
     * @return Object the object at current location in iteration
     */
    public Object next()
    {
        Object tmp = theItems[currentIteratorPosition];
        currentIteratorCount++;
        currentIteratorPosition =
            (currentIteratorPosition + 1) % theItems.length;
        return tmp;
    }

    /**
     * hasNext returns true unless iterator is complete
     * @return boolean true if more elements, false otherwise
     */
    public boolean hasNext()
    {
        return (currentIteratorCount != currentLength);
    }

    /**
     * remove is NOT provided in this class for integrity reasons
     */
    public void remove() throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    /**
     * writeToFile writes the object to a specified file
     *
     */
    public void writeToFile (String fullPath)
                throws IOException
    {
        File myFile = new File(fullPath);

        ObjectOutputStream myStream = new ObjectOutputStream(
                                      new BufferedOutputStream(
                                      new FileOutputStream(myFile)));
        myStream.writeObject(this);
        myStream.close();
    }

    public static Queue readFromFile (String fullPath)
                throws IOException
    {
        File myFile = new File(fullPath);
        if ((!myFile.isFile()) || (!myFile.canRead()))
        {
            // Let's leave
            throw new IOException("Path is not valid ....");
        }


        ObjectInputStream myStream = new ObjectInputStream(
                                      new BufferedInputStream(
                                      new FileInputStream(myFile)));

        Queue temp;
        // Read the object from file - it needs to be cast
        try
        {
            temp = (Queue)myStream.readObject();
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

