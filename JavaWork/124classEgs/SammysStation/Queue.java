import java.io.Serializable;
import java.util.Enumeration;

/**
 * Queue provides a full abstraction of the Queue data structure
 * @author Mark Ratcliffe
 * @date 5 February 1998
 */

public class Queue implements Serializable, Enumeration
{
    /*
     * currentLength maintains current number of items in array
     */
    private int currentLength = 0;
    
    /**
     *  defaultSize defines length of queue if default constructor is used
     * - set to 10
     */
    public final int DEFAULT_SIZE = 10;

    private Object[] theItems;        // Declare but don't define
    
    /*
     * front and back are used as pointers within the array
     */
    private int front = 0;
    private int back = 0;

    /*
     * The following declaration is required by enumeration
     */

    int currentEnumerationPosition;


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
     *  @exception java.lang.Exception thrown if the queue
     *  is already full
     */
    public void add(Object newItem) throws Exception
    {
        // check that we have space in array
        if (currentLength == theItems.length)
        {
            throw new Exception("Queue too full!");
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
     * @exception java.lang.Exception thrown if queue is empty
     */
    public Object remove() throws Exception
    {
        Object tmp;

        // first check that there is an Object in the queue
        if (currentLength == 0)
        {
            throw new Exception("Tried to remove from an empty queue");
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
     * @exception java.lang.Exception thrown if queue is empty
     */
    public Object frontOf() throws Exception
    {
        if (currentLength == 0)
        {
            throw new Exception("Tried to get front of an empty queue");
        }

        // now pass a reference to the object back to the user
        return theItems[front];
    }
    
    /**
     *  Simply calls toString on every object is the queue
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
             if (this.theItems[locationInThis] 
                     != other.theItems[locationInOther])
             { 
                 return false;
             }
             locationInThis = 
                 (locationInThis + 1) % this.currentLength;
             locationInOther = 
                 (locationInOther + 1) % other.currentLength;
         }
         // got this far so queues are equal
         return true;
    }

    /**
     * An enumeration interface is provided over the Queue.
     * User is provided with elements() to generate iterator.
     * nextElement() is used to advance until hasMoreElements() is false
     */

    /**
     * Elements is used to initialise the iterator over the Queue
     * @return Enumeration used to iterate over Queue
     */

    public Enumeration elements()
    {
        currentEnumerationPosition = this.front;
        return this;
    }

    /**
     * nextElement() is used to obtain next element (starting from front).
     * The enumerator is then advanced to next element in list
     * @return Object the obejct at current location in enumeration
     */
    public Object nextElement()
    {
        Object tmp = theItems[currentEnumerationPosition];
        currentEnumerationPosition =
            (currentEnumerationPosition + 1) % theItems.length;
        return tmp;
    }

    /**
     * hasMoreElements returns true unless enumerator is complete
     * @return boolean true if more elements, false otherwise
     */
    public boolean hasMoreElements()
    {
        return (currentEnumerationPosition != this.back);
    }
}

