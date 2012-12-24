// package Qaz.util;

/** 
 * @author Ian Wallis</A>
 * @email: <A HREF="mailTo:ifw9@aber.ac.uk>ifw9@aber.ac.uk</A>
 * or <A HREF="mailTo:ifw9@aber.ac.uk>Qaz_Wallis@yahoo.com</A>
 * @version last updated 20 November 2000
 * @since jdk 1.3
 *
 * This code is the property of me, Ian Wallis.  You are free to use 
 * this code in your own projects but I ask that you aknowledge my  
 * part in them and that you include my original code, or the means 
 * to get it, with your distribution. I accept no responsibility for  
 * any damage caused in any way by my code and I assure you that if it 
 * does cause damage then it not intensional.  I would also be 
 * gratefull for any comments, suggestions or bug reports that you 
 * have regarding my code. <BR> Thank you.
 */

import java.io.*;

/**
 * A standard List class. Simply Extend to specialise it for your purpose. 
 * Can only be used with Listable Objects.
 *
 * @see Listable
 */
public class List implements Serializable, Iterator{

	// /////////////// //
	// Class Constants //
	// /////////////// //
	/** the default capacity of the List. Set to 30 **/
	public final static int DEFULT_CAPACITY = 30;

	// ///////// //
	// Variables //
	// ///////// //
	/** The array of the items in the List **/
	protected Listable[] theItems;

	/** The number of items in the List **/
	protected int currentLength = 0;
    
	/** The current position of the Iterator **/
	protected int currentIterPos = -1;
    
	// //////////// //
	// CONSTRUCTORS //
	// //////////// //
	/**
	 * Creates a List with the default capacity of 30 items.
	 */
	public List()
	{
		theItems = new Listable[DEFULT_CAPACITY];
	}

	/**
	 * Creates a List with a user defined capacity
	 * @param theSize The Capacity of the List
	 */
	public List(int theSize)
	{
		theItems = new Listable[theSize];
	}
	
	/**
	 * Creates a List from an existing array of Listables.
	 * Assumes that the Array is full of data members and copies it exactly.
	 * @param Listable[] The array of Listables to convert to a List
	 * @exception ListFullException Only occures if there is an Internal error
	 */
	public List(Listable[] theObjects)throws ListFullException{
		int theLength = theObjects.length;	// Find length of array
		theItems = new Listable[theLength]; // create new list
		for(int i=0; i<=theLength; i++){ 	// add the objects to List
			if(theObjects[i] != null){ 		// but only if index not null
				this.addItem(theObjects[i]);
			} // ind if
		} 	  // end for loop
	}
	
	// /////// //
	// METHODS //
	// /////// //
	/**
	 * Adds a listable Object to to the List.
	 * @param newItem The Listable Object to be added.
	 * @exception ListFullException if the List is already full.
	 */
	public void addItem(Listable newItem)throws ListFullException{
		// Check List is not full
		if( this.isFull() )
		{
			throw new ListFullException();
		}
		theItems[currentLength] = newItem;
		currentLength++;
	}

	/**
	 * Returns a link to a Listable Object in the List by comparing the given Listable 
	 * to all the Listables in the List using the isEqual method.
	 * @param other The give Listable to compare to
	 * @return The Listable which matches the given Listable
	 * @exception ListEmptyException If the List is empty
	 * @exception ListItemInvalidException If the given Listable does not match any in the List.
	 */
	public Listable getItem(Listable other) 
							throws ListItemInvalidException,
								   ListEmptyException{
		if( currentLength == 0 ){ 				// Check List is not empty
			throw new ListEmptyException();
		}
		for(int i=0; i<currentLength; i++){ 	// now search for the item
			if( theItems[i].isEqual(other) ){
				return theItems[i]; 			// once found return it
			}
		}					// If the Item is not found then it is not there
		throw new ListItemInvalidException("The item you are looking for is not in the List!");
	}

	/**
	 * Returns the index value of a Listable Object in the List by comparing the given Listable 
	 * to all the Listables in the List using the isEqual method.
	 * @param other The give Listable to compare to
	 * @return The index of the  Listable which matches the given Listable
	 * @exception ListEmptyException If the List is empty
	 * @exception ListItemInvalidException If the given Listable does not match any in the List.
	 */
	public int getItemIndex(Listable other)throws ListItemInvalidException,
								   				  ListEmptyException{
		if( currentLength == 0 ){
			throw new ListEmptyException();
		}
		for(int i=0; i<currentLength; i++){
			if( theItems[i].isEqual(other) ){
				return i;
			}
		}
		throw new ListItemInvalidException("The Item is not in the List!");
	}

	/**
	 * Returns the Item at the given index value
	 * @param x The Index value of the Item
	 * @return The item at the given index#
	 * @exception ListEmptyException If the List is empty
	 * @exception IndexInvalidException if the index does not point to an item
	 */
	public Listable getItemAt(int x) throws ListEmptyException,
											IndexInvalidException{
		// Check List is not empty
		if( currentLength == 0 ){
			throw new ListEmptyException();
		}
		if( ( x<0 ) || ( x>currentLength ) ){
			throw new IndexInvalidException
						("The index is out of range!");
		}
		return theItems[x];
	}

	/**
	 * Removes an item from the List and returns a link to it. 
	 * Uses the <CODE>getItemIndex()</CODE> method to find the Objects index and then the <
	 * CODE>removeItemAt()</CODE> method to remove it 
	 * (hence the <CODE>IndexInvalidException</CODE>).
	 * If you no longer need the removed item, simply discard it.
	 * @param other The object to compare to using the isEqual method.
	 * @return The object which was removed.
	 * @exception ListItemInvalidException If the given Listable does not match any in the List.
	 * @exception ListEmptyException If the List is empty.
	 * @exception IndexInvalidException This could only happen with an internal error.
	 */
	public Listable removeItem(Listable other)throws ListItemInvalidException,
								   					 ListEmptyException,
								   					 IndexInvalidException{
		if(this.isEmpty()){	// Check List is not empty
			throw new ListEmptyException("The List is empty, cannot remove anything!");
		}
		int tmpI = this.getItemIndex(other);
		return removeItemAt(tmpI);
	}
	
	/**
	 * Removes the Item At the give Index and replaces it with the Object at the end of the List 
	 * (Keeps all the Objects at the front of the array).
	 * @param int theIndex The Index of the Item to remove
	 * @return Listable a Handle of the Object which was removed.
	 * @exception ListEmptyException If the List Is empty.
	 * @exception IndexInvalidException If the Index does not point to an Object in the List.
	 */
	public Listable removeItemAt(int theIndex)throws ListEmptyException,
											  IndexInvalidException{
 		if(this.isEmpty()){
			throw new ListEmptyException("The List is empty, cannot remove anything!");
		}
		Listable lbl = theItems[theIndex];
		theItems[theIndex] = theItems[--currentLength];
		return lbl;
	}
	

	/**
	 * Returns the number of objects in the List
	 * @return int The current length of the list
	 */
	public int getLength(){
		return currentLength;
	}

	/**
	 * Returns the total capacity of the List
	 * @return int The Total number of items which the list can hold
	 */
	public int getCapacity(){
		return theItems.length;
	}
	
	/**
	 * Tests to see if the List is empty.
	 * @return true if the List has no Objects stores in it.
	 * @return false if there is at least one Object in the List.
	 */
	public boolean isEmpty(){
		if(currentLength == 0){
			return true;
		}
		else return false;
	}
	
	/**
	 * Tests to see if the List is full to capacity.
	 * @return true If the List cannot contain any more Objects.
	 * @return false If the List can have at least one more Object in it.
	 */
	public boolean isFull(){
		if(currentLength == this.getCapacity()){
			return true;
		}
		else return false;
	}

	// ///////////////// //
	// Interface Methods //
	// ///////////////// //
	
	/**
	 * Returns this List as an Iterator.
	 * @return Iterator This List ready as an Iterator.
	 */
	public Iterator iterator(){
		currentIterPos = -1;
		return this;
	}
	
	/**
	 * Tests to see if the iteration has another object to look at.
	 * @return true if the is another Object in the List
	 * @return false if the Iteration has reached the end of the List.
	 */
	public boolean hasNext(){
		if(currentIterPos >= currentLength){
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * Returns the next Object along in the List
	 * @return Object The Next item in the Iteration of this List.
	 */
	public Object next(){
		return theItems[currentIterPos++];
	}
	
	/**
	 * Throws an UnsupportedOperationException
	 */
	public void remove()throws UnsupportedOperationException{
		throw new UnsupportedOperationException("This method is not supported!");
	}

	/**
	 * Returns a String value of the List for Debugging and Information purposes
	 * @return The String value
	 */
	public String toString(){
		String tmpString = "";
		tmpString += "\nThere are " + currentLength + " items in the List. \nThe capacity of the list is " + 
		this.getCapacity();
		return tmpString;
	}
}