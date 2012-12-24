package aber.util;

import java.io.*;
/**
 * A class that models a sorted list using an array.
 * The data item to be stored is a Sortable.
 * @author John Woodbury
 * @author Mark Ratcliffe
 * @version Last revision date: 23/2/98
 * @exception ListEmptyException
 * @exception ListFullException
 * @exception ListItemNotFoundException
 * @see Sortable
 */
public class SortedList implements Serializable
{
    /**
     * A list of objects as an array which can be redefined by user for
     * a specific list.
     */
    private Sortable[] list;

    /**
     * DEFAULT_SIZE gives max size of list if using default constructor
     */
    public static final int DEFAULT_SIZE = 50;
    
    /*
     * maxSize gives maxSize of the Queue whichever constructor is used
     */
    private static int maxSize = DEFAULT_SIZE;

    /*
     * currentLength keeps track of the number of items on the list.
     */
    private int currentLength = 0;

    /**
     * Default constructor for list 
     * Size of list defined by DEFAULT_LENGTH - it is of fixed length
     */
    public SortedList()
    {
       list = new Sortable[DEFAULT_SIZE];
    }

    /**
     * Constructor to define list with a specified maximum size
     * List is modelled on an array - it is of fixed length
     * @param sizeOfList required maximum size of list
     */
    public SortedList(int sizeOfList)
    {
       list = new Sortable[sizeOfList];
       maxSize = sizeOfList;
    }

    /**
     * Inserts an item to the appropriate location in the list
     * @param anItem the data to be added to the list.
     * @exception ListFullException thrown if list is already
     * the maximum permissable length
     */
    public void insertItem(Sortable anItem) throws ListFullException
    {
       int counter = 0;

       if (currentLength == maxSize)
       {
           throw new ListFullException();
       }
      
       // find required location to insert object 
       while ((counter < currentLength) &&
               (list[counter].lessThan(anItem)))
       {
           counter++;
       }
       
       // At this point we have located required position
       for (int i = currentLength; i>counter; i--)
       {
           list[i] = list[i-1];
       }

       // Insert new object at this point
       list[counter] = anItem;
       
       // Increment currentLength
       currentLength++;
    }

    /**
     * Removes the first occurence of an item from list given an object
     * Uses IsEqual defined for Listable (via Sortable)
     * @param anItem the item to be deleted.
     * @exception EmptyListException thrown if user deletes 
     * an item from an empty list.
     * @exception ListItemNotFoundException thrown if item to
     * be deleted is not found on the list.
     */
    public void removeItem(Sortable anItem) 
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
     * Removes an item from the list by position 
     * NOTE: Location starts at 0
     * @param location - the position of item to be removed.
     * @exception ListEmptyException thrown if user deletes
     * an item from an empty list.
     * @exception ListInvalidIndexException thrown if location
     * to be deleted is not in range of the list.
     */
    public void removeItem(int location) 
        throws ListEmptyException, ListInvalidIndexException
    {

        // test to see if list is empty
        if (currentLength == 0)
        {
            throw new ListEmptyException();
        }

        // test to see if location is in range
        if ((location < 0) | (location >= currentLength))
        {
            throw new ListInvalidIndexException();
        }

        // lets now shunt the array back down 
        for (int i = location; i < currentLength; i++)
        {
            list[i] = list[i+1];
        }
        
        // reduce the count of objects in the array
        currentLength--;
        
        // now remove the item at the end of the list
        list[currentLength] = null;
    }


    /**
     * Displays the entire contents of a list.
     * @exception ListEmptyException - thrown when the list is empty
     */
    public void displayList() throws ListEmptyException
    {
        if (getListLength() == 0)
        {
            throw new ListEmptyException("raised in displayList");
        }

        for(int i = 0; i < currentLength; i++)
        {
            // call toString on each object in list
            System.out.println(list[i]);
        }
    }

    /**
     * Gets the current length of the list.
     * @return int the length of the list
     */
    public int getListLength()
    {
        return currentLength;
    }


    /**
     * Gets reference to item in numbered location in the list.
     * @param location the position in the list (starts at 0)
     * @param Sortable the value found at location
     * @exception ListInvalidIndexException - thrown when location
     * is out of range
     * @exception ListEmptyException - thrown when the list
     * is empty
     * NOTE: Count starts at 0
     */
    public Sortable getItem(int location) 
        throws ListEmptyException, ListInvalidIndexException
    {
        // test to see if list is empty
        if (currentLength == 0)
        {
            throw new ListEmptyException("getItem called on empty list");
        }

        // test to see if location is in range
        if ((location < 0) | (location >= currentLength))
        {
            throw new ListInvalidIndexException("getItem index out of range");
        }

        return list[location];
    }
    
    /**
     * toString method returns a representation of the List as a string. This is done
     * by calling the toString methods in each of the items in the list.
     *
     * @returns String  the list represented as a String
     */
    public String toString()
    {
        String toReturn = "";
        for(int i = 0 ; i < currentLength; i++)
        {
            toReturn = toReturn + list[i].toString() + "\n";
        }
        
        return toReturn;
    }
}
