// In this test class, whenever we think our test case is going to result
// in an exception being thrown, we catch it, so that the test class can
// continue running.

// The test cases are probably more thorough than those in StackTest.java

public class QueueTest
{
	public static void main(String [] args)
   {
   	Person p1 = new Person("p1: Joe Soap",35);
      Person p2 = new Person("p2: Cliff Richard",60);
      Person p3 = new Person("p3: Jane Smith", 4);

      System.out.println("// Constructor (one parameter)");
      Queue q = new Queue(3);

      System.out.println("// isEmpty (when queue is empty, after constructing)");
      System.out.println(q.isEmpty());
      // OUTPUT: true

      System.out.println("// length (when queue is empty)");
      System.out.println(q.length());
      // OUTPUT: 0

      System.out.println("// insert (when queue is empty)");
		q.insert(p1);

      System.out.println("// insert (when queue is neither empty nor full)");
		q.insert(p2);
      q.insert(p3);

      System.out.println("// insert (when queue is full)");
      try {
      q.insert(p2);
      } catch (Exception e) { System.out.println(e); }
      // OUTPUT: EmptyQueueException

      System.out.println("// toString");
      System.out.println(q);
      // OUTPUT: p1, p2, p3

      System.out.println("// length (when queue is full)");
      System.out.println(q.length());
      // OUTPUT: 3

		System.out.println("// isEmpty (when queue is non-empty)");
      System.out.println(q.isEmpty());
      // OUTPUT: false

      System.out.println("// remove (when queue is non-empty)");
      System.out.println(q.remove());
      // OUTPUT: p1
      System.out.println(q);
      // OUTPUT: p2, p3

      System.out.println("// getFront (when queue is non-empty)");
		System.out.println(q.getFront());
      // OUTPUT: p2

      System.out.println("// remove (when queue is non-empty, after a getFront)");
      System.out.println(q.remove());
      // OUTPUT: p2

      System.out.println("// insert (after a remove)");
      System.out.println(q);
		q.insert(p2);
      System.out.println(q);
      // OUTPUT: p3, then p3, p2

      System.out.println("// length (when queue is neither empty nor full)");
      System.out.println(q.length());
      // OUTPUT: 2

		System.out.println("// Constructor (no parameters)");
      Queue q2 = new Queue();

      System.out.println("// equals (when one queue is empty, one is non-empty)");
      System.out.println(q.equals(q2));
      System.out.println(q2.equals(q));
      // OUTPUT: false, false

      System.out.println("equals (different lengths, and equal elements until the shorter queue ends)");
      q2.insert(p3);
      System.out.println(q.equals(q2));
      System.out.println(q2.equals(q));
      // OUTPUT: false, false

      System.out.println("// equals (when queues are equal)");
      q2.insert(p2);
      System.out.println(q.equals(q2));
      System.out.println(q2.equals(q));
      // OUTPUT: true, true

      System.out.println("// equals (when queues are of the same length, and are not equal)");
      q.remove(); // Note that we can ignore the returned value
		q.insert(p3);
      System.out.println(q.equals(q2));
      System.out.println(q2.equals(q));
      // OUTPUT: false, false

      q.remove();
      q.remove();

      System.out.println("// remove (when queue is empty)");
      try {
      System.out.println(q.remove());
      } catch (Exception e) { System.out.println(e); }
      // OUTPUT: EmptyQueueException

      System.out.println("// getFront (when queue is empty)");
      try {
      System.out.println(q.getFront());
      } catch (Exception e) { System.out.println(e); }
      // OUTPUT: EmptyQueueException

      System.out.println("// isEmpty (when queue is empty, after inserts/removes)");
      System.out.println(q.isEmpty());

      q2.remove();
      q2.remove();

      System.out.println("// equals (when queues are both empty)");
      System.out.println(q.equals(q2));
      System.out.println(q2.equals(q));
      // OUTPUT: true, true
   }
}