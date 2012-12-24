import java.io.*;
/**
 *  Sortable interface defines methods required for an object to be 
 *  embedded in a sorted list. Note that Sortable extends Listable.
 *  Listable provides the isEqual method
 *  
 * @author Mark Ratcliffe
 * @version Last revision date: 10/2/98
 * @see Listable
 */

public interface Sortable extends Listable
{
    /**
     * lessThan is required for insertion - public abstract by default
     * @param other the object to be compared against
     * @return boolean true if this is lessThan other
     */
    boolean lessThan(Object other);
}
