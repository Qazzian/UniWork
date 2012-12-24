/**
 * StudentList maintains a collection of students
 * @author Class of CS12220 (evening)
 * @version 22/11/99
 */
public class StudentList
{
     private Student [] theList = new Student[10];

     private int studentCount = 0;

     /**
      * addStudent adds an existing student to list
      */
	 public void addStudent(Student theStudent)
     {
         if (studentCount < theList.length)
         {
             theList[studentCount] = theStudent;
             studentCount++;
         }
         else
         {
             System.out.println("Too many students - student not added");
         }
     }

     /**
      * removeStudent removes a student from a list
      * @param String forename the forename of the student to be removed
      * @param String surname the surname of the student to be removed
      */
     public void removeStudent(String forename, String surname)
     {
         int locationToDelete;
         boolean found = false;
         // first locate item in array
         for (locationToDelete =0;
		      locationToDelete < studentCount;
			  locationToDelete++)
         {
             if (theList[locationToDelete].getForename().equals(forename) &&
			     theList[locationToDelete].getSurname().equals(surname))
             {
                 found = true;
                 break;
             }
         }

         // test to see if searched item is present or not
         if (!found)
         {
             System.out.println("Didn't find student name in list" +
			                    " - nothing removed");
             return;  // ie item was not found
         }

         // reduce studentCount to reflect removal
         studentCount--;
         // copy last entry into current position
         theList[locationToDelete] = theList[studentCount];
         // set last entry (now a copy) to null
         theList[studentCount] = null;
     }

     /**
      * getStudent retrieves a student from list
      * @param String forename the forename of the student to be located
      * @param String surname the surname of the student to be located
      * @return Student the student reference found in the list
      */
     public Student getStudent(String forename, String surname)
     {
         for (int i = 0; i < studentCount; i++)
         {
             // if we find what we are looking for, return student
             if (theList[i].getForename().equals(forename) &&
			     theList[i].getSurname().equals(surname))
             {
                 return theList[i];
             }
		 }
         return null;
     }
    /** 
	 * sortBySurname  sort names ascending by surname
     */
    public void sortBySurname()
    {
        boolean exchange;
        Student temp;
        for (int outer = 0; outer < studentCount; outer++)
        {
            exchange = false;
            for (int inner = 0; inner < studentCount-1; inner++)
            {
                if (theList[inner].getSurname().compareTo(
                    theList[inner+1].getSurname()) > 0)
                {
                    // i.e. if (name.compareTo(othername) > 0)
                    // now swap
                    temp = theList[inner];
                    theList[inner] = theList[inner+1];
                    theList[inner+1] = temp;
                    exchange = true;
                }
            }
            if (!exchange)
            {
                break;
            }
        }
    }

     /**
      * toString returns a formatted representation of list
      * @return String the formatted list
      */
     public String toString()
     {
         String tempString = "\n";

         for (int i = 0; i < studentCount; i++)
         {
             tempString = tempString
			              + theList[i].toString()
						  + "\n";
         }
         return tempString;
     }

     /**
      * getStudentCount returns the number of students in list
      * @return int the number of students in list
      */
     public int getStudentCount()
     {
         return studentCount;
     }

     /**
      * main - NOT FOR GENERAL USE
      * This is for test purposes only
      */
     public static void main(String [] args)
     {
         StudentList collection = new StudentList();

         Student s1 = new Student ("Mr.", "Bloggs", "Bill",
                                     "", "", "");
         Student s2 = new Student ("Mr.", "Davies", "Tom",
                                     "", "", "");

         collection.addStudent(s1);
         collection.addStudent(s2);



         s2 = new Student ("Mr.", "Brown", "Ted",
                           "", "", "");

         collection.addStudent(s2);
         System.out.println("\n The list of students is now: " + collection);
         collection.sortBySurname();
         System.out.println("Sorting now.....");
         System.out.println("\n The list of students is now: " + collection);


         Student findIt;
         findIt = collection.getStudent("Bill", "Bloggs");

         System.out.println("\nDetails on Bill Bloggs are " + findIt);
         System.out.println("\n The list of students is now: " + collection);

         System.out.println("Removing Bill Bloggs");
         collection.removeStudent("Bill", "Bloggs");
         System.out.println("\n The list of students is now: " + collection);

         System.out.println("Removing Bill Bloggs");
         collection.removeStudent("Bill", "Bloggs");
         System.out.println("\n The list of students is now: " + collection);

     }
}