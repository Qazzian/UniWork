/**
 * @author Ian Wallis 	
 * @email: ifw9@aber.ac.uk or Qaz_Wallis@yahoo.com
 * @version last updated 12 October 2000
 * @since jdk 1.2
 *
 * This code is the property of me, Ian Wallis.  You are free to use 
 * this code in your own projects but I ask that you aknowledge my  
 * part in them and that you include my original code, or the means 
 * to get it, with your code. I accept no responsibility for any 
 * damage caused in any way by my code and I assure you that if it 
 * does cause damage then it not intensional.  I would also be 
 * gratefull for any comments or suggestions that you have regarding   
 * my code. <BR> Thank you.
 */

// package Qaz.util;

import java.io.*;

/**
 * The <code>LinkedStack</code> uses the principals of a 
 * <code>LinkedList</code> to create a truly dynamic and object 
 * oriented Stack. 
 * Includes all the usual methods commonly used with a Stack such as 
 * <code>push</code>, <code>pop</code>, <code>peek</code> and 
 * <code>isEmpty</code>. 
 * This <code>LinkedStack</code> class also contains methods and 
 * attributes for setting a maximum capacity to the stack. This 
 * allows someone to set a maximum size if the need arises. The 
 * maxsize is inhibited by the MAX_VALUE if an integer anyway because 
 * I used an Int to record the length of the Stack.
 * This <code>LinkedStack</code> class also implements Serializabe so 
 * that it can be saved and manipulated using the java.io.* packages.
 *  
 * @see java.io.Serializable
 * @see java.lang.Integer
 * @see Stack
 * @see LinkedList
 */
public class LinkedStack implements Serializable
{
	/** The data at the top of the stack*/
	protected Node top = null;
	
	/** The data at the bottom of the stack */
	protected Node bottom = null;
	
	/** The length of the Stack **/
	protected int length = 0;
	
	/** The Maximum capacity of the Stack.
	 *  Default value = Integer.MAX_VALUE = 2,147,483,647.**/
	protected int maxCapacity = Integer.MAX_VALUE;
	
	/** The Minimum capacity of a Stack. Set to `0'*/
	private static final int STACK_MIN_SIZE = 0;
	
	// Constructors
	
	/**
	 * Creates an empty <code>LinkedStack</code> with no restrictions.
	 */
	public LinkedStack(){
	}
	
	/**
	 * Creates an empty <code>LinkedStack</code> with a given capacity
	 */
	public LinkedStack(int cap) throws IndexInvalidException{
		this.setCapacity(cap);
	}
	
	/**
	 * Creates a <code>LinkedStack</code> placing the given object 
	 * at the bottom of the Stack. Has no extra restrictions.
	 */
	public LinkedStack(Object obj)throws ListFullException{
		this.push(obj);
	}
	
	/**
	 * Creates a <code>LinkedStack</code> placing the given Object 
	 * at the bottom of the Stack and making it to the given capacity.
	 * @param Object obj The Object to place at the bottom of the 
	 * Stack.
	 * @param int cap The capacity to be given to the stack.
	 * @exception IndexInvalidException If the Capacity is out of 
	 * the possible range.
	 */
	public LinkedStack(Object obj,int cap) 
						throws IndexInvalidException,
								ListFullException{
		this.push(obj);
		this.setCapacity(cap);
	}
	
	/**
	 * Will at some stage put in a constructor accepting an array 
	 * of Objects.
	 */
	
	// Methods
	
	/**
	 * Pushes an object to the top of the stack.
	 * @param Object o The Object to put on the stack.
	 * @exception ListFullException If the Stack is full.
	 */
	public void push(Object o)throws ListFullException{
		if(this.isFull()){
			throw new ListFullException("The Stack is full to " +
			"capacity!");
		}
		Node nd = new Node();
		nd.data = o;
		if(this.isEmpty()){
			top = bottom = nd;
		}
		else{
			nd.prev = this.top;
			this.top = nd;
		}
		length++;
	}
	
	/**
	 * Returns and removes the Object at the top of the stack.
	 * @return The object at the top of the Stack.
	 * @exception ListEmptyException If the Stack is empty.
	 */
	public Object pop() throws ListEmptyException{
		if(this.isEmpty()){
			throw new ListEmptyException("The Stack is Empty. "+
						"Cannot pop!");
		}
		Node nd = top;		// handle on top Node holding Object.
		Object obj = nd.data;	// handle on Object in Node.
		top = nd.prev;		// Put next node on top.
							// If no others then will be null
		length--;			// reduce the length of the stack.
		if(isEmpty()){ 		// ie. if just popped last object.
			bottom = null;
		}
		return obj;
	}
	
	/**
	 * Returns a handle to the Object at the top of the stack
	 * without removing it.  If the stack is empty will return null.
	 * @return The Top Object
	 */
	public Object peek() throws ListEmptyException{
		if(this.isEmpty()){
			throw new ListEmptyException("The Stack is Empty. "+
					"You cannot peek at it.");
		}
		return top.data;

	}
	
	/**
	 * Lookes to see if the Stack has at least 1 object in it.
	 * @return true If the stack is empty.
	 * @return false If there is 1 or more objects in the Stack.
	 */
	public boolean isEmpty(){
		return (length == 0);
	}
	
	/**
	 * Lookes to see if the Stack has reached its capacity.
	 * @return true if the Stack is full.
	 * @return false if the Stack has room for one or more objects.
	 */
	public boolean isFull(){
		return (length == maxCapacity);
	}
	
	/**
	 * Due to the nature of a linked Stack it is possible to change 
	 * the capacity without many problems, quickly.
	 * The capacity cannot be smaller than 0 or smaller than the 
	 * number of objects already in the Stack. Also it cannot exceed
	 * the Maximum value of an Integer 
	 * 	(Integer.MAX_VALUE = 2,147,483,647)
	 * @param int cap The new capacity of the LinkedStack.
	 * @exception IndexInvalidException If the given capacity is not 
	 * in a valid range.
	 */
	public void setCapacity(int cap) throws IndexInvalidException{
		if( (cap <= STACK_MIN_SIZE) || (cap > Integer.MAX_VALUE) ){
			throw new IndexInvalidException("You cannot make a " +
			"stack with " + cap + " object(s)! That's just silly!");
		}
		if(cap < this.length){
			throw new IndexInvalidException("You cannot make the " +
			"stack have a capacity of " + cap + ". You have " +
			length + " objects in it already!");
		}
		maxCapacity = cap;
	}
	
	/**
	 * Returns a String value of the Stack and its contents
	 * usful for debuging and printing data.
	 * @return String The details and contents of the stack in 
	 * String form.
	 */
	public String toString(){
		String tmpS = "The Stack has a depth of " + length;
		tmpS += "\n The capacity of the stack is " + maxCapacity;
		tmpS += "\n The contents of the Stack:";
		Node nd = top;
		for(int i=0; i<length; i++){
			tmpS += "\n " + nd.toString();
			
			nd = nd.prev;
		}
		return tmpS;
	}
	
	// /////// //
 	// Testing //
	// /////// //
	
	/**
	 * main is a Test method for the LinkedStack class.
	 * @exception Exception if an exception is thrown and not caught.
	 */
	public static void main(String[] args) throws Exception{
		/* Quick Test harnes */
		LinkedStack sk = new LinkedStack();
		/**
C:\My Documents\Java\Qaz\\util>javac LinkedStack.java
		NB: there is a double slash befor util cos it comes up with 
			a compile error if I dont
C:\My Documents\Java\Qaz\\util>java LinkedStack
		*/

		String s1 = "\nThis is string 1.";
		String s2 = "\nand this is string 2.";
		String s3 = "\nThis is the 3rd String.";
		String s4 = "\nand this the 4th.";
		System.out.println(s1+s2+s3+s4);
		/**
This is string 1.
and this is string 2.
This is the 3rd String.
and this the 4th.
		*/

		sk.push(s1);
		sk.push(s2);
		sk.push(s3);
		sk.push(s4);
		
		String tmpS = "\nNow to do some poping;";
		tmpS += (String) sk.pop();
		tmpS += (String) sk.pop();
		tmpS += "\nLets try a peek.";
		tmpS += (String) sk.peek();
		tmpS += (String) sk.pop();
		/**
Now to do some poping;
and this the 4th.
This is the 3rd String.
Lets try a peek.
and this is string 2.
and this is string 2.
		 */
		
		tmpS += "\ndo you think the Stack is empty now?";
		System.out.println(tmpS);
		System.out.println(sk.isEmpty());
		System.out.println("I guess not then.");
		/**
do you think the Stack is empty now?
false
I guess not then. 
		 */

		System.out.println("\n\n Shall we see the toString?");
		System.out.println(sk.toString());
		/**
 Shall we see the toString?
The Stack has a depth of 1
 The capacity of the stack is 2147483647
 The contents of the Stack:

This is string 1.
		*/
		
		System.out.println("\n\n Now to change the capacity to 4");
		sk.setCapacity(4);
		System.out.println(sk.toString());
		/**		
 Now to change the capacity to 4
The Stack has a depth of 1
 The capacity of the stack is 4
 The contents of the Stack:

This is string 1.

C:\My Documents\Java\Qaz\\util>

		* As you can see, the basic operations work
		* Now I need to test that Exceptions are caught
		*/
		
		try{
			System.out.println("\n\nLets try to fill up the stack.");
			sk.push("Here is another string");
			sk.push("and another");
			sk.push("This should be the top One");
			sk.push("This one should cause a ListFullException!");
		}
		catch (ListFullException e){
			System.out.println(e);
			System.out.println("I guess it worked then.");
		}
		catch (Exception e){
			System.out.println("I guess there was another Exception"+
			"\n" + e);
		}
		finally{
			System.out.println("This should get printed after the "+
			"Exception(s)");
			System.out.println(sk.toString());
		}
		/**
Lets try to fill up the stack.
ListFullException: The Stack is full to capacity!
I guess it worked then.
This should get printed after the Exception(s)
The Stack has a depth of 4
 The capacity of the stack is 4
 The contents of the Stack:
 This should be the top One
 and another
 Here is another string

This is string 1.
		 */
		
		try{
			System.out.println("Lets try to make it too small.");
			sk.setCapacity(3);
			System.out.println( sk.toString() + "\nRahhh, WORK!");
		}
		catch (Exception e){
			System.out.println( e +"\n Yeay! It worked!");
		}
		finally{
			System.out.println("Now even smaller, Negative in fact");
		}
		/**
Lets try to make it too small.
IndexInvalidException: You cannot make the stack have a capacity of 3. You have
4 objects in it already!
 Yeay! It worked!
		 */
		
		try{
			sk.setCapacity(-4);
			System.out.println( sk +"\nWORK! WORK! WORK! WORK!");
		}
		catch (Exception e){
			System.out.println( e +"\nYEAY! YEAY! YEAY! YEAY!");
		}
		finally{
			System.out.println("As this is a test session " +
					" i suppose I'd better put something in here"+
					".\nI know!\n\n" + sk.toString());
		}
		/**
Now even smaller, Negative in fact
IndexInvalidException: You cannot make a stack with -4 object(s)! That's just si
lly!
YEAY! YEAY! YEAY! YEAY!
As this is a test session  i suppose I'd better put something in here.
I know!

The Stack has a depth of 4
 The capacity of the stack is 4
 The contents of the Stack:
 This should be the top One
 and another
 Here is another string

This is string 1.
		*/
		
		try{
			System.out.println("Now lets see what happens when we "+
			"pop too much.");
			System.out.println((String)sk.pop());
			System.out.println((String)sk.pop());
			System.out.println((String)sk.pop());
			System.out.println((String)sk.pop());
			System.out.println((String)sk.pop());
		}
		catch (Exception e){
			System.out.println( e);
		}
		finally{
			System.out.println("Did it work again?");
		}
		/**
Now lets see what happens when we pop too much.
This should be the top One
and another
Here is another string

This is string 1.
ListEmptyException: The Stack is Empty. Cannot pop!
Did it work again?

C:\My Documents\Java\Qaz\\util>
		*/
		
		try{
			System.out.println("\n\nSo what happens when I peek "+
					"at nothing? Lets find out.");
			System.out.println( sk.peek().toString());
		}
		catch (Exception e){
			System.out.println( e + "\nWell, we learn something "+
						"new every day don't we.");
		}
		finally{
			System.out.println("Well That's It. See you in the "+
					"next class. Bye Bye ");
		}
		/**
So what happens when I peek at nothing? Lets find out.
ListEmptyException: The Stack is Empty. You cannot peek at it.
Well, we learn something new every day don't we.

C:\My Documents\Java\Qaz\\util>
		 */

	}
}