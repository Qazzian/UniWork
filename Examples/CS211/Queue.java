import java.util.*; //gets Vector and Iterator

public class Queue implements Iterator {

	/* The Vector storing the elements of the Queue */
	protected Vector theQueue = new Vector();
	/* The current Index of the current Iteration */
	protected int curInd = 0;

    /* And now to the operations */
	
	/**
	 * Adds an element to the back of the Queue
	 * @param Object The Element to add
	 */
    public void addTo (Object item) {
		theQueue.addElement(item);
	}

	/**
	 * Removes and returns the Object at the start of the Queue.
	 * @return The Object at the start of the Queue
	 * @exception Exception If the Queue is Empty
	 */
    public Object removeFrom() throws Exception{
    	if(theQueue.isEmpty()){
			throw new Exception("The Queue is Empty! Cannot remove!");
		}
		Object temp = theQueue.firstElement();
        theQueue.removeElementAt(0);
    	return temp;
    	}

    /**
	 * Returns the Length of the Queue
	 * @return The size of the the Queue as an int
	 */
	public int lengthOf() {
    	return theQueue.size();
    }

	/*******************************
	 * Implementation for Iterator *
	 ******************************/

	/**
	 * Call this method to set up the Queue as an Iteration
	 * @return This Queue set up for a new Iteration
	 */
	public Iterator iterator(){
		curInd = 0;
		return this;
	}
	
	/**
	 * Looks to see if there is another element after the current one 
	 * in the Queue
	 * @return True if there is another element. /p
	 *			False if the next index of the Queue is empty.
	 */
	public boolean hasNext(){
		if(curInd < theQueue.size()){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * Returns the next element along in the Queue and increments the 
	 * current position
	 * @return The Object which is next in the Queue.
	 */
	public Object next(){
		return theQueue.elementAt(curInd++);
	}

	/**
	 * this method is not supported and will throw an 
	 * <code>UnsupportedOperationException</code>
	 * @exception UnsupportedOperationException This Method is not 
	 * supported.
	 */
	public void remove() throws UnsupportedOperationException{
		throw new UnsupportedOperationException
		    ("Cannot remove from the middle of the Queue!");
	}

        
    //Could be used like this!

    public static void main (String args[])throws Exception {

    Queue q = new Queue();

    q.addTo("mary");
	q.addTo("bill");
	q.addTo("sue");

    String first=(String)q.removeFrom();
	System.out.println(first);
	System.out.println((String)q.removeFrom());

	q.addTo("Ian");
	q.addTo("Mike");
	q.addTo("Mikiey");
	q.addTo("James");
	q.addTo("John");
	q.addTo("Peter");
    q.addTo("Emyr");

	System.out.println("Added lots more names");

	Iterator iter = q.iterator();
		
	while(iter.hasNext() == true){
		Boolean b = new Boolean(iter.hasNext());
		System.out.println( "Iter.hasNext = " + b );
		System.out.println( (String)iter.next() );
		b = new Boolean(iter.hasNext());
		System.out.println( "Iter.hasNext = " + b );
	}

	
    }

}
