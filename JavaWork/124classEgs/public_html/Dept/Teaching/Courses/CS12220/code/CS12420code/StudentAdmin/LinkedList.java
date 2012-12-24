// package aber.util;

import java.io.Serializable;

/**
 * A class that provides a linked list of Node
 * @author Mark Ratcliffe
 * @version Last revision date: 10/3/98
 * @see ListEmptyException
 * @see ListItemNotFoundException
 * @see ListInvalidIndexException
 * @see Node
 * @see Listable
 */
public class LinkedList implements Serializable
{
    /**
     * A list of Nodes using a linked list
     * a specific list.
     */

    protected Node head;
    protected Node tail;

    /**
     * Default constructor sets head & tail of list to null
     */
    public LinkedList()
    {
        head = null;
        tail = null;
    }

    /**
     * Resets head & tail of list to null - ie deletes list
     */
    public void initialise()
    {
        head = null;
        tail = null;
    }

    /**
     * Provides  a method to add an object to end of list
     * @param theItem the item to be added to the end of the list.
     */
    public void addToTail(Listable theItem)
    {
        //First create a new node in which to store the object
        Node newNode = new Node(theItem);

        // now implement the situation where the list is empty
        if (head == null)
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            // set the node pointed to tail, to point to new node
            tail.setNext(newNode);
            // reset tail to point to new node
            tail = newNode;
        }
    }
    
    /**
     * Returns a reference to an indexed item in the list.  
     * @param location the item to be returned.
     * @exception ListInvalidIndexException thrown if index is out of range
     * @exception ListEmptyException thrown if list is empty
     *
     * NOTE: Index starts at 0
     */
    public Listable getItem(int location) 
        throws ListInvalidIndexException, ListEmptyException
    {
        // temp is a temporary pointer for traversing the list
        Node temp = head;
        int index = 0;

        if (head == null)
        {
            throw new ListEmptyException("getItem from an empty list");
        }

        if (location < 0) 
        {
            throw new ListInvalidIndexException("getItem index is negative");
        }
        
        while ((temp != null) && (index < location))
        {
            temp = temp.getNext();
            index++;
        }
        
        if (temp == null)
        {
            throw new ListInvalidIndexException("index out of range");
        }

        return (Listable)temp.getData();
    }
    

    /**
     * Finds and returns the first occurence of an item from the list.  
     * @param anItem the item to be obtained.
     *
     * @exception ListEmptyException thrown if user tries to
     * locate an item in an empty list.
     *
     * @exception ListItemNotFoundException thrown if item is not
     * found on the list.
     */
    public Listable getItem(Listable anItem) 
        throws ListEmptyException, ListItemNotFoundException
    {
        Node current = head;

        if (head == null)
        {
            throw new ListEmptyException();
        }

        while ((current != null) &&
                (!((Listable)current.getData()).isEqual(anItem)))
        {
            current = current.getNext();
        }

        if (current == null)
        {
            throw new ListItemNotFoundException();
        }
        
        // Have found the item
        return (Listable) current.getData();
    }

    /**
     * Removes first occurence of item from the list.  
     * @param anItem The item to be deleted.
     *
     * @exception ListEmptyException thrown if user deletes
     * an item from an empty list.
     *
     * @exception ListItemNotFoundException thrown if item to
     * be deleted is not found on the list.
     */
    public void removeItem(Listable anItem) 
        throws ListEmptyException, ListItemNotFoundException
    {
        Node current = head;
        Node previous = null;

        if (head == null)
        {
            throw new ListEmptyException();
        }


        while ((current != null) &&
              (!((Listable)current.getData()).isEqual(
                                             anItem)))
        {
            // Keep record of node pointing to current
            previous = current;
            current = current.getNext();
        }


        if (current == null)
        {
            throw new ListItemNotFoundException();
        }
        
        // If removing first item, then we reset head
        if (previous == null)
        {
            head = current.getNext();

        } 
        else
        {
            previous.setNext(current.getNext());
        }
        // But what if we are removing last node?
        if (current == tail)
        {
            tail = previous;
        }
    }

    /**
     * provides a method to print out complete list starting at head
     * @return String formatted representation of list
     */
    public String toString()
    {
        Node temp = head;
        String tempString = "";
        while (temp != null)
        {
            tempString = tempString +
                         "\n" +
                         temp;    // which will call toString

            // Now lets move to the next item in the list
            temp = temp.getNext();
        }
        return tempString;
    }
    

    /**
     * Gets the current length of the list.
     */
    public int getListLength()
    {
        int currentLength = 0;
        Node temp = head;
        
        while (temp != null)
        {
            currentLength++;
            temp = temp.getNext();
        }
        return currentLength;
    }
    
}
        
