/* Queue.java - An circular array implementation of the queue Abstract Data Type
 * Ed Wiles and Fred Long
 * 15 Sep 2000
 */

// The Stack class is best studied first, as the Queue class is very similar
// (I have omitted many of the comments that are in the Stack class, and in
// particular I have been lazy with the javadoc comments before each method.
// Don't follow my example! :-) - Ed)

public class Queue
{
	private Object [] list;
   private static final int MAXIMUM_LENGTH = 10;
	// The first unoccupied element of the circular array
   private int firstFree = 0;
   // The first element of the circular array
   // This will be changed whenever an object is removed
   private int first = 0;
	// The length of the circular array
   // More convenient than trying to calculate length using first and firstFree
   private int length = 0;

   public Queue()
	{
   	list = new Object[MAXIMUM_LENGTH];
   }

   public Queue(int size)
   {
    	list = new Object[size];
   }

	/* Inserts an object into the queue - i.e., adds an object to the back
    * of the array. Circular implementation.
    *
    * @param element - the item to add
    */
   public void insert(Object element)
   {
		// If the queue is full, throw an exception
      // FullQueueException is a new exception which we have defined
		if (length == list.length)
      {
      	throw new FullQueueException();
      }

   	// Add new object to the back of the queue
      list[firstFree] = element;

		// Adding one to firstFree works fine, until we get to the end of
      // the array. To implement a circular array, firstFree should return
      // to 0 when it "falls off the end" of the array.
      firstFree++;
      if (firstFree == list.length)
      	firstFree = 0;
      // An alternative to the previous three lines is the following line,
      // where % is the modulo operator (the remainder after a division):
      // firstFree = (firstFree + 1) % list.length;
      // It's only one line, but it may be more difficult to understand
 
 		// And the queue has become one item longer, so let's update length to
      // reflect this
      length++;
   }

	/* Removes an object from the queue - i.e., removes an object from the
    * front of the array. It also returns the object. Circular implementation.
    *
    * @return element - the item to remove (and return)
    */
   public Object remove()
   {
		// If the queue is empty, throw an exception
		// EmptyQueueException is an exception which we have defined
      if (isEmpty())
      {
      	throw new EmptyQueueException();
      }

		// Remove object
      Object removedObject = list[first];
		list[first] = null; // Allows the garbage collector to remove it

      // Add one to first, in a circular way (see the insert method)
      // Alternative to these three lines: first = (first+1) % list.length;
		first++;
      if (first == list.length)
      	first = 0;

		// And the queue has become one item shorter, so reduce length by 1
      length--;

      // Finally, return the removed element
      return removedObject;
   }

   /* Returns the element at the front of the queue, without removing it
    *
    * @return - the removed Object
    */
	public Object getFront()
   {
		// If the queue is empty, throw an exception
      if (isEmpty())
      {
      	throw new EmptyQueueException();
      }

		// Return the first item in the queue (not list[0], because the array
      // is circular, so the front could be anywhere in the array)
   	return list[first];
   }

   public boolean isEmpty()
   {
   	return length == 0;
   }

   public int length()
   {
		// We have a length instance variable, so we just return it!
   	return length;
   }

   /* Converts the queue to a String
    * @return - queue containing FRONT on one line, then the queue on
    *           subsequent lines
    */
   public String toString()
   {
		// For comments on the use of StringBuffer, see toString in Stack.java
		StringBuffer tempString = new StringBuffer("FRONT\n");

		// This contains the current position in the queue
      int position = first;

		// We don't actually use the value i within the loop; it is used
      // to repeat the loop as many times as there are elements in the queue
      for (int i = 0; i < length; i++)
      {
			// Append, to tempString, the string version of the queue element
         // at "position"
			tempString.append(list[position]);
         tempString.append("\n");

         // Update position in a circular way
			position++;
         if (position == list.length)
         	position = 0;
      }

      return tempString.toString();
   }

   public boolean equals(Object o)
   {
		// See comments on the equals method in Stack.java
		Queue otherQueue = (Queue) o;

      // We need two location counters, because the first element in each queue
      // might be in different locations. For example, this queue might start
      // at array element 0, while the other queue might start at element 2.
      // This is because the arrays are circular: the front of the queue is
      // not necessarily at element 0 (see the remove method above).
		//
		// Notice that otherQueuePos has direct access to the other queue's first
      // variable, even though first is private! That is because the other
      // queue is also of class Queue. (If otherQueue was of another class,
      // e.g. LinkedList, then we could not access its first variable directly.)
      int thisQueuePos = first;
      int otherQueuePos = otherQueue.first;

      // Quick test: If the queues' lengths are not the same, then the queues
      // are definitely not equal!
	   if ( length() != otherQueue.length() )
	   	return false;

		// We don't actually use the value i within the loop; it is used
      // to repeat the loop as many times as there are elements in the queue
      for (int i = 0; i < length; i++)
      {
//			System.out.println("i: " + i + "; " +
//                            "thisQueuePos: " + thisQueuePos + "; " +
//                            "otherQueuePos: " + otherQueuePos);
			// If the elements at the two current positions are not equal, then
         // the queues are not equal, so return false
			if ( !list[thisQueuePos].equals(otherQueue.list[otherQueuePos]) )
				return false;

         // Update the positions in a circular way
         // We'll use the "one line" way (see the insert method)
         // Be careful to use the lengths of the arrays, not length() !
         thisQueuePos = (thisQueuePos + 1) % list.length;
         otherQueuePos = (otherQueuePos + 1) % otherQueue.list.length;
      }

		// If we have reached this point, the two queues are equal
      return true;
   }
}