import java.util.Vector;

/**
 * A class to demonstrate a queue using vectors
 * @author Mark Ratcliffe
 * @version Last revision by Mark Ratcliffe 18/06/98
 * The class provides a fully operational Queue
 * with facilities for adding to the back,
 * removing from the front, calculating the length of the queue,
 * and tests for an empty queue
 */

public class QueueVector 
{
   private Vector theQueue;
  
   /**
    * Constructor creates a new instance of the class
    */
   public QueueVector()
   {
       theQueue = new Vector();
   }
 
   /* And now to the other methods */

   /**
    * Add a new object to the back of the Queue
    * @param item the object to be added at the back
    */
   public void addTo(Object item) 
   {
      theQueue.addElement(item);
   }

   /**
    * Remove an object from the front of the Queue
    * @return the object at the front of the queue
    * @exception java.lang.Exception thrown if queue is empty
    */
   public Object removeFrom() throws Exception 
   {
      if (theQueue.size() == 0)
      {
          throw new Exception("Queue is empty ");
      }

      Object temp = theQueue.firstElement();
      theQueue.removeElementAt(0);
      return temp;
   }

   /**
    * Calculate the length of the queue
    * @return the number of items in the queue
    */ 
   public int lengthOf() 
   {
      return theQueue.size();
   }
}
 
