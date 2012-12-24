import java.io.*;

/**
 *  Listable interface defines methods required for an object to be 
 *  embedded in a list
 *  
 * @author Mark Ratcliffe
 * @version Last revision date: 10/2/98
 */

public interface Listable extends Serializable
{
    /**
     * equality is required for removal - public abstract by default
     * @param other the object to be compared against
     */
    boolean isEqual(Object other);
         
}
