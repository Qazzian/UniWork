import java.io.*;
/**
 * A class that models a sorted list using an array.
 * The data item to be stored is a Sortable.
 * @author John Woodbury
 * @author Mark Ratcliffe
 * @version Last revision date: 23/2/98
 * @exception ListFullException
 * @see GenericList
 * @see Sortable
 */
public class SortedList extends GenericList implements Serializable
{

    /**
     * Default constructor for list
     * Size of list defined by GenericList DEFAULT_LENGTH fixed length
     */
    public SortedList()
    {
        super();
    }

    /**
     * Constructor to define list with a specified maximum size
     * List is modelled on an array - it is of fixed length
     * @param sizeOfList required maximum size of list
     */
    public SortedList(int sizeOfList)
    {
        super(sizeOfList);
    }

    /**
     * We need to overload this to prevent inappropriate use
     * @param anItem the data to be added to the list.
     *
     * @exception ListFullException thrown if the
     * list is already the maximum permissable length
     */
    public void addItem(Listable anItem) throws ListFullException
    {

        addItem ((Sortable) anItem);
    }

    /**
     * Inserts an item to the appropriate location in the list
     * @param anItem the data to be added to the list.
     * @exception ListFullException thrown if list is already
     * the maximum permissable length
     */
    public void addItem(Sortable anItem) throws ListFullException
    {
       int counter = 0;

       if (currentLength == maxSize)
       {
           throw new ListFullException();
       }
      
       // find required location to insert object 
       while ((counter < currentLength) &&
               (((Sortable) list[counter]).lessThan(anItem)))
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
}
