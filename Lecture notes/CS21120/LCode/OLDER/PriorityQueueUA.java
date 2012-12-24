/* PriorityQueueUA.java - An unsorted array implementation of the priority queue
 * abstract data type. No need for circular arrays! (see the remove method)
 * Ed Wiles and Fred Long
 * 16 Sep 2000
 */

public class PriorityQueueUA
{
	// These instance variables and constructors are very much like the ones
   // we used in Stack.java, but notice that we have a list of PriorityNodes
   // (objects with their priorities), rather than a list of Objects.
   // See PriorityNode.java

	private PriorityNode [] list;
   private static final int MAXIMUM_LENGTH = 10;
   private int firstFree = 0;
 
   public PriorityQueueUA()
	{
   	list = new PriorityNode[MAXIMUM_LENGTH];
   }

   public PriorityQueueUA(int size)
   {
    	list = new PriorityNode[size];
   }

	public void insert(Object element, int priority)
   {
		// A convenient way of inserting an element with an integer priority
		// Just uses the method below
		insert(element, new Integer(priority));
   }

   /* Inserts an item into the priority queue
    *
    * @param element - the item to insert
    * @param priority - the priority of the element (higher numbers give higher
    *						   priority)
    */
   public void insert(Object element, Comparable priority)
   {
		// In this implementation, the order in which elements are inserted
      // does not matter. So, as adding to the back of a (non-circular) array
      // is quickest, let's do that. It's very similar to push in Stack.java.

      PriorityNode newNode = new PriorityNode(element, priority);

		// If the priority queue is full, throw an exception
		// We'll reuse the exceptions we created for queues
		if (firstFree == list.length)
      {
      	throw new FullQueueException();
      }

   	// Add new node
      list[firstFree] = newNode;
      firstFree++;
   }

   /* Returns the element which has highest priority, in the priority queue
    *
    * @return - the element with highest priority (its priority is not returned)
    */
   public Object remove()
   {
		// If the priority queue is empty, throw an exception
		if (isEmpty())
      {
      	throw new EmptyQueueException();
      }

      // Find the element with highest priority
      // Variable "highest" contains the index of the element which, so far,
      // has highest priority. To begin with, it's the first element. The for
      // loop goes through all of the other nodes; if a node is found which has
      // higher priority than "highest", "highest" is given its index.

      int highest = 0;
      for (int i = 1; i < firstFree; i++)
      {
      	if (list[highest].compareTo(list[i]) < 0)
         	highest = i;
      }

      // Remove the element with highest priority
		Object removedElement = list[highest].getData();

      // Replace it with the back of the queue. The alternative is to take
      // each element after the removed element, and shuffle them to the left
      // one place, in order to cover the "hole" left by removing the element.
      // But the element order does not matter, so this is a much quicker way
      // of doing this.

      firstFree--;
      list[highest] = list[firstFree];
      list[firstFree] = null;     // The last node can now be garbage collected
      return removedElement;
   }

   /* Returns the element with highest priority, without removing it
    * @return - element with highest priority
    */
   public Object getHighestPriority()
   {
		// Much like the remove method above
      int highest = 0;
      for (int i = 1; i < firstFree; i++)
      {
      	if (list[highest].compareTo(list[i]) < 0)
         	highest = i;
      }

      return list[highest].getData();
   }

   /* Is the priority queue empty?
    * @return - true if the priority queue is empty, false if non-empty
    */
   public boolean isEmpty()
   {
		// Exactly the same as for stacks
   	return firstFree == 0;
   }

   /* How long is the priority queue?
    * @return - priority queue length
    */
   public int length()
   {
   	return firstFree;
   }

   public String toString()
   {
		// If PriorityQueueUA is to have the same interface (inputs and outputs)
      // as PriorityQueueSLL, we need to return the results sorted in order
      // of priority!

		// So we find the node with highest priority, and output it. Then we look
      // for the node with second-highest priority. To do that, we take the
      // node with highest priority, and swap it with the first node. Then our
      // search for the next node starts with the second node, rather than the
      // first node (which has already been output).

      // The index (position) of the node from which we search is i, and the
      // node which we are currently examining is j.

      // As a result of this outputting, by the end of the method the queue
      // is sorted!

      StringBuffer tempString = new StringBuffer("HIGHEST PRIORITY\n");

      for (int i = 0; i < firstFree; i++)
      {
	      int highestIndex = i;
	      for (int j = i+1; j < firstFree; j++)
	      {
	      	if (list[highestIndex].compareTo(list[j]) < 0)
	         	highestIndex = j;
	      }

         tempString.append(list[highestIndex]);
         tempString.append("\n");

         // This swaps even if it's the same element - it should be made
         // more efficient
         PriorityNode tempNode = list[i];
			list[i] = list[highestIndex];
			list[highestIndex] = tempNode;
      }

 		// And return the complete string
      return tempString.toString();
   }
}