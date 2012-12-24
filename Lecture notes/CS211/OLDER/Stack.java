/* Stack.java - An array implementation of the stack Abstract Data Type
 * Ed Wiles and Fred Long
 * 13 Sep 2000
 */

public class Stack
{
   // Instance variables

   // The stack is represented by an array of objects
   // Because we use the Object class, objects of any class can be stored
	private Object [] list;
   // A constant: the default maximum length of the array
   private static final int MAXIMUM_LENGTH = 10;
   // The first unoccupied element of the array. New elements will be stored
   // here
   private int firstFree = 0;

   // Constructors

   public Stack()
	{
   	// If the class user doesn't specify a maximum length of the stack,
      // set it to MAXIMUM_LENGTH
   	list = new Object[MAXIMUM_LENGTH];
   }

   public Stack(int size)
   {
		// Set the maximum stack length to whatever the user passes in
    	list = new Object[size];
   }

   /* Pushes an object on to the stack - i.e., adds an object to the
    * back of the array (the top of the stack)
    *
    * @param element - the item to push
    */
   public void push(Object element)
   {
		// If the stack is full, throw an exception
      // OutOfMemoryError is in the Java class library
		if (firstFree == list.length)
      {
      	throw new OutOfMemoryError();
      }

   	// Add new object
      list[firstFree] = element;
      firstFree++;
   }

   /* Pops an object from the stack - i.e., removes and returns the object
    * at the back of the array (the top of the stack)
    *
    * @return - the popped Object
    */
   public Object pop()
   {
		// If the stack is empty, throw an exception
      if (isEmpty())
      {
      	throw new java.util.EmptyStackException();
      }

		// Remove object
		firstFree--;
		Object removedObject = list[firstFree];
      list[firstFree] = null; // Allows the garbage collector to remove it
      return removedObject;
   }

   /* Peeks the stack - i.e., returns the object at the back of the array
    * (the top of the stack), without removing it
    *
    * @return - the peeked Object
    */
	public Object peek()
   {
		// If the stack is empty, throw an exception
      if (isEmpty())
      {
      	throw new java.util.EmptyStackException();
      }

   	return list[firstFree-1];
   }

   /* Is the stack empty?
    * @return - true if the stack is empty, false if non-empty
    */
   public boolean isEmpty()
   {
		// This is an abbreviation of:
      // if (firstFree == 0) return true; else return false;
   	return firstFree == 0;
   }

   /* How long is the stack?
    * @return - stack length
    */
   public int depth()
   {
      // Careful: list.length would return the maximum number of elements
      // in the stack! firstFree, however, returns the actual number of
      // occupied spaces in the array, i.e. the number of elements in the
      // stack
   	return firstFree;
   }

   /* Converts the stack to a String
    * @return - string containing TOP on one line, then the stack on
    *           subsequent lines
    */
	public String toString()
	{
		// We will build up our returned string in a variable tempString
		// Why StringBuffer? It's more efficient (see the for loop below)
		StringBuffer tempString = new StringBuffer("TOP\n");

		// The top of the stack is at the end of the array, so we start
      // at the end and work backwards
		for (int i = firstFree - 1; i >= 0; i--)
		{
         // If tempString were a String, we would append by writing:
         //    tempString += list[i] + "\n";
         // but this is less efficient than a StringBuffer when we have
         // many appends to do
         tempString.append(list[i]);
         tempString.append("\n"); // starts a new line
		}

      // Convert the StringBuffer to a String before we return it
		return tempString.toString();
	}

   /* Is this stack equal to this other stack? Overrides equals in class
    * Object, in order to implement "deep equality" for stacks (checking
    * whether the stack's contents are the same, rather than just whether
    * they are the same stack - see the presentation Arrays And Stacks
    * slide 19).
    *
    * @param o - the other stack (of type Object, in order to keep the
    *            method signature the same as that in the Object class)
    */
	public boolean equals(Object o)
	{
		// Java thinks that the parameter o is just an Object, when actually
      // it's also a Stack. We need to let Java know that it's a stack by
      // casting it, otherwise we cannot use the stack's methods (except
      // for those inherited from Object).
      //
      // The easiest way to do this is to define a new variable which Java
      // knows is a Stack, and making it equal to o after casting. Then we
      // will use otherStack instead of o in the rest of the method. (This
      // saves us from casting o every time we want to use it.)
		Stack otherStack = (Stack) o;

      // Quick test: If the stacks' depths are not the same, then the stacks
      // are definitely not equal!
	   if ( depth() != otherStack.depth() )
	   	return false;

		// Iterate over all the elements in the stacks, in order to compare
      // them *pairwise* (is element 0 of this stack equal to element 0 of
      // the other stack? Similarly for element 1, 2, etc.)
		for (int i = 0; i < depth(); i++)
		{
      	// Compare the two elements at i: if not equal, return false
         // For deep equality within the contents of the stack, the content
         // type must have overridden equals. e.g. If we have a stack of
         // Person objects, then Person should override equals - unless we
         // want shallow equality.
			if ( !list[i].equals(otherStack.list[i]) )
				return false;
		}

      // If we've got this far, the stacks are equal, so return true
		return true;
	}
}