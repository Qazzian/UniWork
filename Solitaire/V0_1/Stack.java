//package Qaz.util;

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

import java.io.Serializable;
import java.util.Iterator;

/**
 * Class stack is a standard Stack implemented with an array for 
 * storing Objects. It uses push, pop and peek methods to push onto 
 * the top of the stack, pop off the top and to look at the top
 * object. It also implements the Iterator and Serializable interfaces.
 * 
 * @see java.util.Iterator
 * @see java.io.Serializable
 * @see ListException
 */
public class Stack implements Iterator,Serializable{

		// Attributes
	/** The array storing Objects in the Stack. **/
    protected Object[] data;
	/** The index of the top object. **/
	protected int top = -1;
	/** **/
	protected int current;
	/** The default length of Stacks (currently = 30). **/
	protected static final int DEFAULT_LENGTH = 30;
	/** The maximum length the Stack can be. **/
	protected static final int MAX_LENGTH = Integer.MAX_VALUE;
	
	// Constructors
	
	/**
	 * Creates an empty stack of default length
	 */
    public Stack(){
    	data = new Object[DEFAULT_LENGTH];
    }

	/**
	 * Creates a stack of the given size.
	 * @param int size The capacity to make the Stack.
	 * @exception IndexInvalidException If the size is out of range.
	 */
    public Stack(int size) throws IndexInvalidException{
        if( (size > Integer.MAX_VALUE) || (size < 0) ){
			throw new IndexInvalidException("Cannot make the stack "+
			  "with " + size + " positions!");
		}
		else{
			data = new Object[size];
		}
    }

	/**
	 * Pushes an object onto the top of the Stack.
	 * @param Object o The Object to add to the Stack.
	 * @exception ListFullException If the Stack is full.
	 */
    public void push(Object o) throws ListFullException{
        if(this.isFull()){	// Check stack not full
			throw new ListFullException("The stack is Full!");
		}
		top++;	// Incrament top position
		data[top] = o;	//  Put object in top position
    	return;
    }

	/**
	 * Returns and removes the Object at the top of the Stack.
	 * @return Object The Object at the top of the Stack.
	 * @exception ListEmptyException If the Stack is empty.
	 */
    public Object pop() throws ListEmptyException{
        if(this.isEmpty()){	// check stack not empty
			throw new ListEmptyException("The Stack is Empty, " +
					"can't pop!");
		}
		Object obj = data[top];
		data[top] = null;
		top--;
		return obj;
    }
	
	/**
	 * Returns the object at the top of the Stack without removing it.
	 * @return Object the Object at the top.
	 * @exception ListEmptyException If the Stack is empty.
	 */
	public Object peek() throws ListEmptyException{
        if(this.isEmpty()){	// check stack not empty
			throw new ListEmptyException("The Stack is Empty, " +
					"can not peek!");
		}
		return data[top];	// return top Object nut no dec this time
	}
	
	/**
	 * Tests to see if the Stack is empty.
	 * @true If the Stack is empty.
	 * @false If the Stack has one or more items stored in it.
	 */
	public boolean isEmpty(){
		if(top <= -1)
			return true;
		else
			return false;
	}
	
	/**
	 * Tests to see if the Stack is full
	 * @true If the stack is full to capaxcity.
	 * @false If the Stack can fit at least one object in it.
	 */
	public boolean isFull(){
		if(top > data.length)
			return true;
		else
			return false;
	}
	
	/**
	 * Returns the number of objects currently stored in the Stack.
	 * @return int The number of objects in the Stack.
	 */
	public int depth(){
		return top + 1;
	}
	
	/**
	 * Tests to see if this stack is equal to the given stack.
	 * @param Stack other The Stack to compare to.
	 * @return true If the Stacks are equal.
	 * @return false If the stacks are not equall.
	 */
	public boolean isEqual(Stack other){
		if(this.top != other.top){
			return false;
		}
		for(int i=top;i > -1; i--){
			if(!data[i].equals(other.data[i]))
				return false;
		}
		return true;
	}
	
	/**
	 * 
	 */
	public Iterator iterator(){
		current = (top + 1);
		return (Iterator)this;
	}
	
	public boolean hasNext(){
		if(current <= 0){
			return false;
		}
		else{
			return true;
		}
	}
	
	public Object next(){ //throws ListEmptyException{
		/*if(!hasNext()){
			throw new ListEmptyException("There is no next!");
		}*/
		current--;
		return data[current];
	}
	
	public void remove() throws UnsupportedOperationException{
		throw new UnsupportedOperationException
		("You cannot remove from the middle of this stack!");
	}

	/**
	 * Returns a String value of the stack and its contents.
	 * @return String The Stack expressed as a Satring.
	 */	
	public String toString(){
		String tmpS = "\nNumber of objects in stack: " + top;
		tmpS += "\nTotal Capacity of stack: " + data.length;
		tmpS += "\nThe contents of the Stack;";
		for(int i=0; i<=this.depth(); i++){
			tmpS += "\n" + data[i].toString();
		}
		return tmpS;
	}
}