// In this test class, whenever we think our test case is going to result
// in an exception being thrown, we catch it, in order that the test class
// continues running.

public class LinkedListTest
{
	public static void main(String [] args)
   {
   	Person p1 = new Person("p1: Joe Soap",35);
      Person p2 = new Person("p2: Cliff Richard",60);
      Person p3 = new Person("p3: Jane Smith", 4);

   	System.out.println("// Constructor (arguments)");
		LinkedList people = new LinkedList();

      System.out.println("// removeFromFront (when empty)");
		try {
      System.out.println(people.removeFromFront());
      } catch (Exception e) { System.out.println(e); }
      // OUTPUT: EmptyListException

      System.out.println("// removeFromBack (when empty)");
		try {
      System.out.println(people.removeFromBack());
      } catch (Exception e) { System.out.println(e); }
      // OUTPUT: EmptyListException

      System.out.println("// getFront (when empty)");
		try {
      System.out.println(people.getFront());
      } catch (Exception e) { System.out.println(e); }
      // OUTPUT: EmptyListException

      System.out.println("// getBack (when empty)");
		try {
      System.out.println(people.getBack());
      } catch (Exception e) { System.out.println(e); }
      // OUTPUT: EmptyListException

      System.out.println("// find (when empty)");
      System.out.println(people.find(p2));
   	// OUTPUT: null

      System.out.println("// isEmpty (when empty)");
      System.out.println(people.isEmpty());
   	// OUTPUT: true

      System.out.println("// addToFront (when empty)");
      people.addToFront(p1);

      System.out.println("// addToFront (with one)");
      people.addToFront(p2);

      System.out.println("// addToBack (with one or more)");
      people.addToBack(p3);

		System.out.println("// length");
      System.out.println(people.length());
		// OUTPUT: 3

      System.out.println("// toString");
      System.out.println(people); 
		// OUTPUT: p2,p1,p3

      System.out.println("// isEmpty (when non-empty)");
      System.out.println(people.isEmpty());
   	// OUTPUT: false

      System.out.println("// reverse");
      people = people.reverse();
      System.out.println(people);
      // OUTPUT: p3,p1,p2
      people = people.reverse();
      System.out.println(people);
      // OUTPUT: p2,p1,p3

      System.out.println("// getFront (when non-empty: check that object still present)");
      System.out.println(people.getFront());
		// OUTPUT: p2

      System.out.println("// removeFromFront (when more than one node)");
      System.out.println(people.removeFromFront());
		// OUTPUT: p2

      System.out.println("// getBack (when non-empty: check that object still present)");
      System.out.println(people.getBack());
		// OUTPUT: p3

      System.out.println("// removeFromBack (when more than one node)");
      System.out.println(people.removeFromBack());
		// OUTPUT: p3

      System.out.println("// find (with object present: check that object still present)");
      System.out.println(people.find(p1)); // Uses Node's toString
		// OUTPUT: p1

      System.out.println("// removeFromBack (one item)");
      System.out.println(people.removeFromBack());
      // OUTPUT: p1
      System.out.println(people.length());
		// OUTPUT: 0

      System.out.println("// addToBack (when empty)");
      people.addToBack(p2);
      System.out.println(people);
      // OUTPUT: p2

      System.out.println("// find (with object absent)");
      System.out.println(people.find(p1));
      // OUTPUT: null

   	// Constructor (no arguments)

      // iterator

      people.addToBack(p1);
      people.addToFront(p3);

      LinkedListItr iterator = people.getIterator();

      while (iterator.hasNext())
      {
			System.out.println(iterator.next());
      }
      // OUTPUT: p3,p2,p1

		try {
      iterator.remove();
      // OUTPUT: UnsupportedOperationException: The linked list has no remove
      // method.
      } catch (Exception e) { System.out.println(e); }
  }
}