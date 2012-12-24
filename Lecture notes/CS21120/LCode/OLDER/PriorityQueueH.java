public class PriorityQueueH
{
	private PriorityNode [] list;
   private static final int MAXIMUM_LENGTH = 10;
   private static final int INCREASE_SIZE_BY = 10;
   private int firstFree = 1;

	public PriorityQueueH()
   {
   	list = new PriorityNode[MAXIMUM_LENGTH+1];
   }

   public PriorityQueueH(int size)
   {
    	list = new PriorityNode[size+1];
   }

   public void insert(Object data, Comparable priority)
   {
		// If the priority queue is full, throw an exception
		if (firstFree == list.length)
      	throw new FullQueueException();

   	// Add new priority queue node, containing the object with its priority
      list[firstFree] = new PriorityNode(data, priority);

		// The new node might be too big for its position
      // Restore heap order by swapping the new node with its parent, until
      // the new node is greater than both of its children

      for (int i = firstFree; i != 1; i /= 2)
		{
	      if (list[i/2].compareTo(list[i]) < 0)
	      {
	      	// Swap parent and child
	         PriorityNode temp = list[i/2];
	         list[i/2] = list[i];
	         list[i] = temp;
			}
         else
         {
         	break;
         }
      }

      firstFree++;
   }

   public Object remove()
   {
		// If the queue is empty, throw an exception
      if (firstFree == 1)
      	throw new EmptyQueueException();

		// The removed object is the root
		Object removedObject = list[1];

      // The final node in the heap becomes the new root
		firstFree--;
      list[1] = list[firstFree];
      list[firstFree] = null;

		// The new root might be too small for its position in the heap
      // Restore heap order by swapping the node with the larger of its two
      // children, until both children are smaller than the node

		int i = 1;
      while (i*2 < firstFree)
      {
			int greaterChild;
			if (list[i*2+1] == null)
         	greaterChild = i*2;

         else if (list[i*2].compareTo(list[i*2+1]) > 0)
         	greaterChild = i*2;
			else
         	greaterChild = i*2+1;

      	if (list[i].compareTo(list[greaterChild]) < 0)
         {
         	PriorityNode temp = list[greaterChild];
				list[greaterChild] = list[i];
            list[i] = temp;
            i = greaterChild;
         }

         else
         {
         	break;
         }
      }

      return removedObject;
   }

	private PriorityNode[] increaseSizeBy(int size)
   {
		// whatever
		return list;
   }

   public String toString()
   {
   	StringBuffer tempString = new StringBuffer("");
      for (int i = 1; i < firstFree; i++)
      {
      	tempString.append(list[i]);
      	tempString.append("\n");
      }
      return tempString.toString();
   }
}