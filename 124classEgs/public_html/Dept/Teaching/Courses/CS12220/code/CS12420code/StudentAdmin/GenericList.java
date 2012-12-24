import java.io.*;
import java.util.Iterator;
/**
 * A class that models a list on an array.
 * The data item to be stored is an Listable.
 * @author Mark Ratcliffe
 * @version Last revision date: 23/2/99
 * @see ListEmptyException
 * @see ListFullException
 * @see ListItemNotFoundException
 * @see ListInvalidIndexException
 * @see Listable
 */
public class GenericList implements Serializable, Iterator
{
    /**
     * A list of objects as an array which can be redefined by user for
     * a specific list.
     */
    protected Listable[] list;

    /**
     * DEFAULT_SIZE gives max size of list if using default constructor
     */
    public static final int DEFAULT_SIZE = 50;
    
    /*
     * maxSize gives maxSize of the Queue whichever constructor is used
     */
    protected int maxSize = DEFAULT_SIZE;

    /*
     * currentLength keeps track of the number of items on the list.
     */
    protected int currentLength = 0;

    /*
     * currentIteratorPosition maintains detail on iterator
     */
    protected int currentIteratorPosition = 0;

    /**
     * Default constructor for list 
     * Size of list is defined by DEFAULT_LENGTH - it is of fixed length
     */
    public GenericList()
    {
       list = new Listable[DEFAULT_SIZE];
    }

    /**
     * Constructor to define list with a specified maximum size
     * List is modelled on an array - it is of fixed length
     */
    public GenericList(int sizeOfList)
    {
       list = new Listable[sizeOfList];
       maxSize = sizeOfList;
    }

    /**
     * Adds an item to the end of the list.
     * @param anItem the data to be added to the list.
     *
     * @exception ListFullException thrown if the
     * list is already the maximum permissable length
     */
    public void addItem(Listable anItem) throws ListFullException
    {
       if (currentLength == maxSize)
       {
           throw new ListFullException();
       }

       list[currentLength] = anItem;
       currentLength++;
    }

    /**
     * Returns a reference to a specified item in the list.
     * @param anItem the item to be returned.
     * @exception ListItemNotFoundException thrown if index is out of range
     * @exception ListEmptyException thrown if list is empty
     */
    public Listable getItem(Listable anItem)
        throws ListItemNotFoundException, ListEmptyException
    {
        if (currentLength == 0)
        {
            throw new ListEmptyException("getItem called on empty list");
        }
        for (int i = 0; i < currentLength; i++)
        {
            // this assumes that we have the isEqual available!!!
            if (list[i].isEqual(anItem))
            {
                return list[i];
            }
        }
        throw new ListItemNotFoundException("Exception not found ");
    }

    /**
     * Returns a reference to an indexed item in the list.
     * @param location the item to be returned.
     * @exception ListInvalidIndexException thrown if index is out of range
     * @exception ListEmptyException thrown if list is empty
     * NOTE: Index starts at 0
     */
    public Listable getItem(int location)
        throws ListInvalidIndexException, ListEmptyException
    {
        if (currentLength == 0)
        {
            throw new ListEmptyException("getItem called on empty list");
        }
        if ((location < 0) | (location >= currentLength))
        {
            throw new ListInvalidIndexException("index is out of range");
        }
        return list[location];
    }

    /**
     * Removes the first occurence of an item from the list.  
     * @param anItem The item to be deleted.
     * @exception ListEmptyException thrown if user deletes
     * an item from an empty list.
     * @exception ListItemNotFoundException thrown if item
     * to be deleted is not found on the list.
     */
    public void removeItem(Listable anItem) 
        throws ListEmptyException, ListItemNotFoundException
    {
        boolean found = false;

        if (currentLength == 0)
        {
            throw new ListEmptyException();
        }


        for (int i = 0; i < currentLength; i++)
        {
            // this assumes that we have the isEqual available!!!
            if (list[i].isEqual(anItem))
            {
                found = true;
                // In this case we are shunting the objects
                // They are moved down array to fill blank
                for (int j = i; j < currentLength-1; j++)
                {
                    list[j] = list[j+1];
                }
                // now come out of loop
                break;
            }
        }


        if (!found)
        {
            throw new ListItemNotFoundException();
        }
        // reduce the Object count
        currentLength--;

        // now remove the item duplicated at the end of the list
        list[currentLength] = null;
    }

    /**
     * Displays the entire contents of a list.
     */
    public String toString()
    {
        String tempString = "";

        for(int i = 0; i < currentLength; i++)
        {
            // call toString on each object in list
            tempString += list[i] + "\n";
        }
        return tempString;
    }

    /**
     * Gets the current length of the list.
     */
    public int getListLength()
    {
        return currentLength;
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
        currentIteratorPosition = 0;
        return this;
    }

    /**
     * next() obtains next element (starting from front).
     * The iterator is then advanced to next element in list
     * @return Object the object at current location in iteration
     */
    public Object next()
    {
        Object tmp = list[currentIteratorPosition];
        currentIteratorPosition++;
        return tmp;
    }

    /**
     * hasNext returns true unless iterator is complete
     * @return boolean true if more elements, false otherwise
     */
    public boolean hasNext()
    {
        return (currentIteratorPosition != currentLength);
    }

    /**
     * remove is NOT provided in this class for integrity reasons
     */
    public void remove() throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

}
