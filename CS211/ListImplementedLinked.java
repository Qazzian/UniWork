
public class LinkedListSorted {
	 
	// /////////////////// //
	// Instance variables. //
	// /////////////////// //


	/** The item of data at the start of the LinkedList. **/
	protected ListNode 	head 		= null;

	/** The item of data at the end of the LinkedList. **/
	protected ListNode 	tail 		= null;
	
	/** The item of data currently selected. **/
	protected ListNode 	current 	= null;
	
	/** The item of data previously looked at by the Iterator. **/
	protected ListNode 	Previous	= null;
	
	/** The current length of the LinkedList. **/
	protected int 		length 	= 0;
	
	/** The capcity set for this List. By default it is set to <CODE>LinkedList.MAX_CAPACITY</CODE>. **/
	protected int 		capacity = MAX_CAPACITY;
	
	// //////////// //
	// Constructors //
	// //////////// //

	public LinkedListSorted(){
	}
	
	public LinkedListSorted(Sortable theItem){
		this.addItem(theItem);
	}

	// /////// //
	// Methods //
	// /////// //
	
	/**
	 * Adds a Listable Object to the end of the List. <BR> NB. Must implement Listable.
	 * @param Listable theItem The Object to add at the end of the List.
	 */
	public void addItem(Sortable theItem){
		if(isFull()){
			throw new ListFullException("The List is full, cannot add another Object!");
		}
		ListNode newNode = new ListNode(theItem);
		if(isEmpty()){
			head = tail = newNode;
		}
		else{
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		length++;
	}
	
	public void removeItem(Sortable theItem)
	
	public void setCapacity(int cap){
		capacity = cap;
	}
	
	public boolean isEmpty(){
		if(length == 0){
			return true;
		}
		else return false;
	}
	
	public boolean isFull(){
		if(length == capacity){
			return true;
		}
		else return false;
	}
	
	public Iterator iterator(){
		return this;
	}
	
	public boolean hasNext(){
	}
	
	public Object next(){
	return;
	}
	
	public void remove(){
	}
	 
	public void display() {
		Node current=head;
		System.out.println("=========");
		while (current!=null) {
			System.out.println(current.data);
			current=current.next;
		}
	}
	 
	public static void main(String args[]) {
		ListImplementedLinked l = new ListImplementedLinked();
		l.add(3); l.add(7); l.add(5); l.add(1);
		l.add(0);l.add(6);l.add(21);
		l.display();
		
	}
}
	 
	 
