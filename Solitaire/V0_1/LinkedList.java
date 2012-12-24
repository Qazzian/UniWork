// package Qaz.util; // Only when instaled in my Qaz.Util package

import java.io.*;

public class LinkedList implements Serializable, Iterator{

	// ////////// //
    // Constants. //
    // ////////// //
	/**
	 * The maximum Capacity of all LinkedList. By default it is set
	 * to Integer.MAX_VALUE.
	 * @see Integer.MAX_VALUE.
	 */
	protected int maxCapacity	= Integer.MAX_VALUE;

	// /////////////////// //
	// Instance variables. //
	// /////////////////// //

	/** The item of data at the start of the LinkedList. **/
	protected ListNode start 	= null;
	
	/** The item of data at the end of the LinkedList. **/
	protected ListNode end 		= null;

	/** The current length of the LinkedList. **/
	protected int length 		= 0;
	
	/** The item of data currently selected by the Iterator. **/
	protected ListNode current 	= null;
	
	/** The item of data previously looked at by the Iterator. **/
	protected ListNode previous = null;
	
	/** The number of objects the Itrator has parsed through. **/
	protected int currentPos	= 0;
		

    // //////////// //
	// Constructors //
	// //////////// //

	/**
	 * Constructs a new empty Linked List with no restrictions
	 */
	public LinkedList(){
	}
	
	/**
	 * Constructs a new Linked List with one Object in it and 
	 * no restrictions.
	 * @param Object obj The first item to be added to the List
	 */
	public LinkedList(Object obj){
		if(obj != null){
			this.addObject(obj);
		}
	}
	
	/**
	 * Constructs a new Linked List from an exsiting array of Objects 
	 * with no restrictions
	 */
	public LinkedList(Object[] objs) throws ListFullException
	{
		for(int i=0; i<objs.length; i++){	// for all the objects in the array
			if(this.length == this.maxCapacity)	// if List not full
				throw new ListFullException();
			if(objs[i] != null) {	// and the index is not empty
				this.addObject(objs[i]);	// add them to the list
			}
		}
	}
	
	/**
	 * Constructs a new empty Linked List with a given capacity
	 * @param int cap The given capacity of the List. Must be larger 
	 * tham 0, else will use the default capacity.
	 */
	public LinkedList(int cap){
		if( cap > 0)
			maxCapacity = size;
	}
	
	/**
	 * Constructs a new Linked List with an Object and a given 
	 * capacity
	 * @param Object The first object to be placed in the List
	 * @param int The total capacity of the LikedList
	 */
	public LinkedList(Object obj,int cap){
		if(obj != null){
			this.addObject(obj);
		}
		if( cap > 0)
			maxCapacity = size;
	}
	
	/**
	 * Constructs a new Linked List from an exsiting array of Objects 
	 * with a given capacity. Sets the Capacity first so if there is 
	 * more objects in the array then the capcity specified, only the
	 * number of Objects up to the given value are added.
	 * @param Object[] The Array of Objects to put in the new List.
	 * @param int The total capacity of the List.
	 */
	public LinkedList(Object[] obj,int cap){
		this.setCapacity(cap);
		for(int i=0; i<objs.length; i++){	// for all the objects in the array
			if(this.length == this.maxCapacity)	// if List not full
				throw new ListFullException();
			if(objs[i] != null) {	// and the index is not empty
				this.addObject(objs[i]);	// add them to the list
			}
		}
	}
	
    // /////// //
	// Methods //
    // /////// //
	
	public void addObject(Object obj){
		ListNode newNode = new ListNode(obj);
		if(start == null){
			start = end = newNode;
		}
		else{
			end.next = newNode;
			newNode.prev = end;
			end = newNode;
		}
		length++;
	}
	
	public void setCapacity(int cap){
		maxCapacity = cap;
	}
	
	public Iterator iterator(){
		current = start;
		previous = null;
		currentPos = 0;
		return this;
	}
	
	public boolean hasNext(){
		if (currentPos < length){
			return true;
		}
		else {
			return false;
		}
	}
	
	public Object next(){
		previous = current;
		current = curent.getNext(); 
		currentPos++;
		return current.getData();
	}
	
	public void remove(){
        Node tmpNode = current; //put the current node in a temp node
        tmpNode.setNext(null); // clears the next and prev node from 
        tmpNode.setPrev(null); // the removed Node (keeps tidy)
        current = current.getNext(); // change current
        previous.setNext(current);
        current.setPrev(previous);
        length--;
 	}

	public String toString(){
	return "";
	}
}