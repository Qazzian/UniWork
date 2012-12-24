/* LinkedListItr.java - An iterator for a linked list
 * Fred Long and Ed Wiles
 * 13 Sep 2000
 */

// java.util.Iterator is an interface which forces this class to include
// the three methods below (the constructor isn't a method!)
public class LinkedListItr implements java.util.Iterator
{
	// The current position in the list
	private Node currentPosition;

   public LinkedListItr(Node firstNode)
	{
		// To start with, the current position is the first node in the list
      // (In fact, any node can be passed into the constructor)
   	currentPosition = firstNode;
	}   	

	// Returns true if there are more elements in the linked list, or false
   // if the linked list has ended
	public boolean hasNext()
   {
		// If the current position is null, the linked list has ended, because
      // the last node in the list has null as the next pointer
   	return currentPosition != null;
   }

	// Returns the next element in the list
   public Object next()
   {
   	// Gets the data out of the current object
   	Object returnedObject = currentPosition.getData();
      // Advances the current position by one node
      currentPosition = currentPosition.getNext();
      return returnedObject;
   }

	// This method could remove the node at the current position, but we won't
   // support it, so we throw the appropriate exception (this is specified
   // in the API documentation for java.util.Iterator).
   public void remove()
   {
   	throw new UnsupportedOperationException
      ("The linked list has no remove method.");
   }
}