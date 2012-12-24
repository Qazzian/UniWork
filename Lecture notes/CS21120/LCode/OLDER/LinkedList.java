/* LinkedList.java - The Linked List Abstract Data Type
 * Ed Wiles and Fred Long
 * 15 Sep 2000
 */

public class LinkedList
{
	// Pointer to the first node in the list
   // null if there are no nodes in the list
   private Node first;

   // Adds an element to the front (start) of the list
   public void addToFront(Object element)
   {
		// Rather sneakily, the empty case does not need to be handled separately
		Node newNode = new Node(element,first);
		first = newNode;
   }

   // Adds an element to the back (end) of the list
   public void addToBack(Object element)
   {
   	// If list is empty...
      if (isEmpty())
      {
			// Let the first node contain the new element
      	first = new Node(element);
      }

      // If list is non-empty...
      else
      {
			// Find the back of the list
      	Node current = first;
         while (current.getNext() != null)
         {
         	current = current.getNext();
         }

         // By this stage, current is the back of the list
         // So create a new node containing the new element, and
         // chain current to the new node
         Node newNode = new Node(element);
         current.setNext(newNode);
      }
   }

   // Removes and returns the element at the front of the list
   public Object removeFromFront()
   {
   	// If list is empty, throw an exception
      if (isEmpty())
      {
      	throw new EmptyListException();
      }

		// Put the first node's data in a temporary variable
		Object removedObject = first.getData();

      // Set the first node to point to the second node (draw this to see
      // what is happening!)
		// If there is only one node, the pointer to the second node will
      // be null, so this works correctly if there is only one node in the list
      // The first node will automatically be thrown away by Java's
      // garbage collector
      first = first.getNext();

      // Return the removed object
      return removedObject;
   }

   // Removes and returns the element at the back of the list
   public Object removeFromBack()
   {
		// If list is empty, throw an exception
		if (isEmpty())
		{
		    throw new EmptyListException();
		}

		// If the list has one node, it must be treated separately
		// (because there is no second-last node)
		else if (first.getNext() == null)
		{
		    Object removedData = first.getData();
		    first = null;
		    return removedData;
		}

      // And if the list has more than one node...
      else
      {
			// Find the second-last node
			Node secondLast = first;
			while (secondLast.getNext().getNext() != null)
				secondLast = secondLast.getNext();

			// Get the data in the last node
			Object removedData = secondLast.getNext().getData();

			// Remove the last node, by making the second-last node
         // the new last node
			secondLast.setNext(null);

	      // And return the removed data
			return removedData;
      }
   }

	// Returns the data in the node at the front of the list
   public Object getFront()
   {
   	// If list is empty, throw an exception
      if (isEmpty())
      {
      	throw new EmptyListException();
      }

      return first.getData();
   }

	// Returns the data in the node at the back of the list
   public Object getBack()
   {
   	// If list is empty, throw an exception
      if (isEmpty())
      {
      	throw new EmptyListException();
      }

		// Otherwise, we first need to find the back of the list
     	Node current = first;
      while (current.getNext() != null)
      {
       	current = current.getNext();
      }

      // By this stage, current is the back of the list
      // So return the data in current
		return current.getData();
	}

   public boolean isEmpty()
   {
   	return first == null;
   }

   public int length()
   {
		// This is what it takes if you don't have a length instance
   	// variable!

		int length = 0;
		for (Node current = first; current != null; current = current.getNext())
      {
			length++;
      }
      return length;
   }

   // Tests two linked lists for (deep) equality
   // Two linked lists are equal if their contents are pairwise equal
   // (first nodes are equal, second nodes are equal, etc.) AND if their
   // lengths are equal

   public boolean equals(Object otherList)
   {
		// We need two pointers, one to point to the first element of this list,
      // one to point to the first element of the other list.
		// Notice current2 has direct access to the other list's first variable,
      // even though first is private! That is because the other list is also
      // of class LinkedList.
   	Node current1 = first;
		Node current2 = ((LinkedList) otherList).first;

		// Keep iterating as long as neither of the lists have ended
      while ((current1 != null) && (current2 != null))
		{
			// If the data in the current node of list 1 is not equal to the
         // data in the current node of list 2, we can return "not equal"
			if (!current1.getData().equals(current2.getData()))
				return false;

			// Advance both pointers
			current1 = current1.getNext();
			current2 = current2.getNext();
		}

      // We don't return true, because if one of the lists ended before
      // the other, the lists are of different lengths. So we return true
      // only if both of the lists have ended.
		return ((current1 == null) && (current2 == null));
   }

   // Converts a linked list into a String. The data in each node of the
   // linked list is returned on separate lines.
   public String toString()
   {
		// Use a StringBuffer because we're doing lots of appending - much
      // more efficient than String
		StringBuffer tempString = new StringBuffer();

		// Nice neat for loop to advance (iterate) through the entire list
		for (Node current = first; current != null; current = current.getNext())
      {
			// Appends (adds to the end) the string version of the current node
         // to tempString. Note that instead of writing "current", we could
         // have written "current.toString()", or "current.getData()", or
         // "current.getData.toString()" ! This illustrates how powerful
         // the toString method is for abbreviating string conversions.
			tempString.append(current);
			tempString.append("\n");
      }

      // Convert the temporary StringBuffer to a String before we return it
      return tempString.toString();
   }

   // Searches for an element in the list. If found, it returns the node
   // which contains that element. If not found, it returns null.
	public Node find(Object element)
	{
		// Iterate through all the elements in the list
		Node current = first;
		while (current != null)
		{
      	// If the data in the current node equals the element, we've found
         // the element in the list, so we can stop the loop using break
			if (current.getData().equals(element))
				break;
         // Otherwise, advance current to the next node
			current = current.getNext( );
		}

		// By this stage, current is either a pointer to a node containing the
      // element, or is null if no element was found (because it will have
      // reached the end of the above while loop). This is exactly what we
      // want to return.
		return current;
	}

	// Reverses a linked list
   // To start the driver method, it needs the full unreversed linked list
	public LinkedList reverse()
	{
	    return reverseDriver(this);
	}

   // This takes a linked list as a parameter, and reverses it into a new
   // linked list, which is returned.

   // It's a recursive method, which means it calls itself. This could be
   // implemented in an iterative way instead (using e.g. a while loop),
   // but the recursive way turns out to be neater.

	private LinkedList reverseDriver(LinkedList list)
	{
		// The base case ("escape route" which doesn't call itself):
      // if the list is empty, then reversing it just produces an empty
      // list. So we return a new linked list with nothing in it.

		if (list.isEmpty())
			return new LinkedList();

      // The recursive case (which calls itself): we remove the front element
      // from the list (calling it "front"), so that the list becomes one
      // element shorter. We then reverse this shortened list (calling it
      // "newList"), and we add "front" to the back of "newList". By this
      // stage, "newList" is the reverse of the original list, so we return it.

      // e.g. Let list = A B C D E
      // Firstly we remove the front, so front = A, list = B C D E
      // Then we reverse list into newList, so newList = E D C B
      // Then we add front to the back of newList, so newList = E D C B A

      // How do we reverse list into newList? By calling reverseDriver again!

		Object front = list.removeFromFront();
		LinkedList newList = reverseDriver(list);
		newList.addToBack(front);
		return newList;
	}

   // Returns an iterator on this linked list, starting at the first element
   // The iterator can be used BY ANOTHER CLASS to advance through each of the
   // nodes in turn (so far, all of our iterators [e.g. find, toString] have
   // been internal to the LinkedList class).

   public LinkedListItr getIterator()
   {
   	return new LinkedListItr(first);
   }
}