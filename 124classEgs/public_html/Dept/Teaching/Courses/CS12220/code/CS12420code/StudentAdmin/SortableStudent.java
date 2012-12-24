import java.io.Serializable;
/**
* Details of a person
* @author Mark Ratcliffe
* @version first written 19/10/99
*
*/

//define a new class called student
public class SortableStudent extends Student implements Sortable, Serializable
{
    public boolean lessThan(Object other)
    {
        int result =
		   this.getSurname().compareTo(((SortableStudent)other).getSurname());
        if (result < 0) // current less than other
        {
            return true;
        }
        if (result == 0) // they are equal
        {
            return false;
        }
        // current greater than other
        return false;
    }

}
