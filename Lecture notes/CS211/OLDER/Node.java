/* Node.java - a node for a linked list
 * Fred Long and Ed Wiles
 * 13 Sep 2000
 */

class Node
{
	// A linked list node is an object, followed by a pointer (link) to the
   // next object in the list. If the pointer is null, there are no more
   // objects in the list.
   private Object data;
   private Node next; // Yes, it can be the same class as this class!

   public Node()
   {
   	// Both data and next are implicitly set to null
   }

   public Node(Object data)
   {
		// Both the instance variable and the parameter to this constructor
      // are called data (because it looks nice!). this.data is another
      // way of referring to the instance variable, data.
   	this.data = data;
		// next is implicitly set to null
   }

   public Node(Object data, Node next)
   {
      this.data = data;
      this.next = next;
   }

   public void setData(Object data)
   {
      this.data = data;
   }

   public void setNext(Node next)
   {
      this.next = next;
   }

   public Object getData()
   {
      return data;
   }

   public Node getNext()
   {
      return next;
   }

   public String toString()
   {
   	// Our toString method returns the data as a String, by using the data's
      // toString method
   	return data.toString();
   }
}
