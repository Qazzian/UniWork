
public class ListImplementedLinked {
	 
	protected  Node head;

	public void add(int theItem) {
		Node newOne=new Node(theItem);
		if (head==null){					//empty list
			head=newOne;
		}
		else {
		Node current=head;
		Node next=head;
		while (true) {
			if (current.data>theItem) break; //start - jump out
			next=current.next;
			if (next==null) break;				//end - jump out
			if (next.data>theItem) break;		//middle - jump out
			current = next;
		}

		if (head==current & current==next) {	  //at start
			head=newOne;
			newOne.next=current;
		}
		else if (next==null) {		 //at end
			current.next=newOne;
		}
		else {						 //in middle
			current.next=newOne;
			newOne.next=next;
		}
		}
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
	 
	 
