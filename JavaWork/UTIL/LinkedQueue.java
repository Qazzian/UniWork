import java.io.*;

public class LinkedQueue implements Serializable{
		// Attributes
	/** The Node at the front of the Queue (First in, First out). **/
	protected Node front;
	
	/** The Node at the back of the Queue (Last in, Last out). **/
	protected Node back;
	
	protected Node cuurent;
	
	protected int length = 0;
	
	protected int maxCapacity = Integer.MAX_VALUE;
	
	protected static final int QUEUE_MIN_SIZE = 0;
		
		// Constructors
		
	public Queue(){
	}
	
		// Methods

		// Inner Classes
		
	/**
	 * Node contains a piece of data stored in the Queue
	 * and a pointer to the next Node in the Queue.
	 * Esentially, The Linked Queue is just a link of Nodes.
	 */
	private class Node{
	
			// Attributes
			
		/** Holds the data Object contaned within the Node**/
		private Object data;
		
		/** Points to the Next node in the LinkedQueue**/
		private Node next;
		
			// Constructors
		
		/**
		 * Creates an empty Node.
		 */
		private Node(){
		}
		
		/**
		 * Ceates a Node containing a data object within it.
		 * @param Object obj The data object.
		 */
		private Node(Object obj){
			data = obj;
		}
		
		/**
		 * Calls the toString method for the Object contained 
		 * within the Node.
		 */
		private String toString(){
			return data.toString();
		}
	
	}

}