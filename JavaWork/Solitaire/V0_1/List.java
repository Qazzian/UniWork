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
 * A standard List class. Simply Extend to specialise it for your
 * purpose. Can only be used with Listable Objects.
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
	protected int currentIterPos = 0;
    
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
	 * Assumes that the Array is full of data members and copies it 
	 * exactly.
	 * @param Listable[] The array of Listables to convert to a List
	 */
	public List(Listable[] theObjects){
		int theLength = theObjects.length; // Find length of array
		theItems = new Listable[theLength]; // create new list
		for(int i=0; i<=theLength; i++){ // add the objects to List
			if(theObjects[i] != null){ // but only if index not null
				this.addItem(theObjects[i])
			} // ind if
		} // end for loop
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
		if( currentLength == theItems.length )
		{
			throw new ListFullException();
		}
		theItems[currentLength] = newItem;
		currentLength++;
	}

	/**
	 * Returns a link to a Listable Object in the List by
	 * comparing the given Listable to all the Listables in the List
	 * using the isEqual method.
	 * @param other The give Listable to compare to
	 * @return The Listable which matches the given Listable
	 * @exception ListEmptyException If the List is empty
	 * @exception ListItemInvalidException If the given Listable does
	 * not match any in the List.
	 */
	public Listable getItem(Listable other) 
							throws ListItemInvalidException,
								   ListEmptyException{
		// Check List is not empty
		if( currentLength == 0 ){
			throw new ListEmptyException();
		}
		// now search for the item
		for(int i=0; i<currentLength; i++){
			if( theItems[i].isEqual(other) ){
				return theItems[i]; // once found return it
			}
		}
		// If the Item is not found then it is not there
		throw new ListItemInvalidException
					(other + " is not in the List!");
	}

	/**
	 * Returns the index value of a Listable Object in the List by
	 * comparing the given Listable to all the Listables in the List
	 * using the isEqual method.
	 * @param other The give Listable to compare to
	 * @return The index of the  Listable which matches the given 
	 * Listable
	 * @exception ListEmptyException If the List is empty
	 * @exception ListItemInvalidException If the given Listable does
	 * not match any in the List.
	 */
	public int getItemIndex(Listable other) 
							throws ListItemInvalidException,
								   ListEmptyException{
		// Check List is not empty
		if( currentLength == 0 ){
			throw new ListEmptyException();
		}
		for(int i=0; i<currentLength; i++){
			if( theItems[i].isEqual(other) ){
				return i;
			}
		}
		throw new ListItemInvalidException
					("The Item is not in the List!");
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
	 * Removes an item from the List and returns a link to it. Uses 
	 * the getItemIndex method.
	 * If you no longer need the removed item, simply discard it.
	 * @param other The object to compare to using the isEqual method.
	 * @return The object which was removed.
	 * @exception ListItemInvalidException If the given Listable does
	 * not match any in the List.
	 * @exception ListEmptyException If the List is empty.
	 */
	public Listable removeItem(Listable other) 
							throws ListItemInvalidException,
								   ListEmptyException{
		// Check List is not empty
		if( currentLength == 0 ){
			throw new ListEmptyException();
		}
		int tmpI = this.getItemIndex(other);
		Listable lbl = theItems[tmpI];
		theItems[tmpI] = theItems[currentLength--];
		return lbl;
	}

	/**
	 * Returns the number of objects in the List
	 * @return The current length of the list
	 */
	public int getLength(){
		return currentLength;
	}

	/**
	 * Returns the total capacity of the List
	 * @return The Total number of items which the list can hold
	 */
	public int getCapacity(){
		return theItems.length;
	}

	public Iterator iterator(){
		currentIterPos = 0;
		return this;
	}
	
	public boolean hasNext(){
		if(currentIterPos >= currentLength){
			return false;
		}
		else{
			return true;
		}
	}
	
	public Object next(){
		return theItems[currentIterPos++];
	}
	
	public void remove(){
	}

	/**
	 * Returns a String value of the List for Debugging and 
	 * Information purposes
	 * @return The String value
	 */
	public String toString(){
		String tmpString = "";
		tmpString += "\nThere are " + currentLength + 
		" items in the List. \nThe capacity of the list is " + 
		this.getCapacity();
		return tmpString;
	}
	
	public static void main(String[] args) throws Exception{
	
	}
}