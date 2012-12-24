// Here is one way to write a fairly thorough test program
//
// 1) A separate comment for each test case for each method in the
//		class, e.g.		// pop (when empty)
//							// pop (when non-empty)
// 2) As you implement each test case, change the comment as follows:
//		System.out.println("// Put the comment here");
//		When you run the test program, this allows you to follow its progress
// 3) Put any expected output after the test case, in the form:
//			// OUTPUT: expected output
// 4) If you run this program in a DOS window, the window's history may be
//    too small for the output of the test program. If so, redirect it into
//    e.g. results.txt as follows:		java StackTest > results.txt

public class StackTest
{
	public static void main(String [] args)
   {
		// Create some new people
   	Person p1 = new Person("Joe Soap",35);
      Person p2 = new Person("Cliff Richard",60);
      Person p3 = new Person("Jane Smith", 4);

      // And here we go with the test cases...
      // I firstly wrote them in order of appearance of the Stack class's
      // methods, then I rearranged them to form a coherent sequence


      // Constructor (size specified)

		Stack s = new Stack(3);

      // push

      s.push(p1);
      s.push(p2);
      s.push(p3);

		// push (full)
//      s.push(p1); // OUTPUT: OutOfMemory error, program terminates
      				// (after making sure this happens, comment out the
                  // test by putting "//" before "s.push(p1)")

      // isEmpty (false)

      System.out.println(s.isEmpty()); // OUTPUT: false

      // depth (greater than zero)

		System.out.println(s.depth()); // OUTPUT: 3

      // toString

      System.out.println(s); // OUTPUT: TOP p3,p2,p1

      // pop

      System.out.println(s.pop()); // OUTPUT: p3
      System.out.println(s.pop()); // OUTPUT: p2
      System.out.println(s.pop()); // OUTPUT: p1

      // peek

//	   System.out.println(s.peek()); // OUTPUT: EmptyStackException
												// (comment out when tested)
      s.push(p2);
      s.push(p3);
      System.out.println(s.peek()); // OUTPUT: p3
      System.out.println(s.pop());  // OUTPUT: p3
      System.out.println(s.pop());  // OUTPUT: p2

      // isEmpty (true)

      System.out.println(s.isEmpty()); // OUTPUT: true

      // toString (empty)

      System.out.println(s); // OUTPUT: TOP

      // Constructor (no size specified)

		Stack s2 = new Stack();

      // Stack equality (true)

      s2.push(p1);
      s2.push(p2);
      s2.push(p3);

      s.push(p1);
      s.push(p2);
      s.push(p3);

      System.out.println(s.equals(s2)); // OUTPUT: true

      // Stack equality (false: different lengths)

      s.pop();

      System.out.println(s.equals(s2)); // OUTPUT: false

      // Stack equality (false: same lengths, different contents)

      s.push(p2);
      System.out.println(s.equals(s2)); // OUTPUT: false
   }
}