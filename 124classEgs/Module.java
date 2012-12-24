import java.io.Serializable;

/**
 * Author: Class of CS12220 1999/2000
 * Date:   7/10/99
 *
 * Purpose: First java program
 */

public class Module implements Serializable
{
    // The main class attributes used to represent a module
	private String title;
    private String code;
    private String coordinator;
    private int numOfLectures = 0;

    // attributes required for supporting implementation
    private int studentCount = 0;

    /**
     * Constructor sets up parameters as provided
     * @param String code of module
     * @param String title of module
     * @param String name of coordinator
	 */
	public Module(String theCode,
                  String theTitle,
	              String theCoordinator)
    {
        code = theCode;
        title = theTitle;
        coordinator = theCoordinator;
    }

    /**
     * setName allows user to set a title
     * @param String the title of the module
     */
    public void setTitle(String theTitle)
    {
        title = theTitle;
    }

    /**
     * getName returns title of module to user
     * @return String representing title of module
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * setCode allows user to set the module code
     * @param int the code e.g. CS12220
     */
    public void setCode(String theCode)
    {
        code = theCode;
    }

    /**
     * getCode returns the module code
     * @return String representing the code e.g. CS12220
     */
    public String getCode()
    {
        return code;
    }
    /**
     * setCoordinator allows user to set a Coordinator
     * @param String the Coordinator of the module
     */
    public void setCoordinator(String theCoordinator)
    {
        coordinator = theCoordinator;
    }

    /**
     * getCoordinator returns Coordinator of module to user
     * @return String representing Coordinator of module
     */
    public String getCoordinator()
    {
        return coordinator;
    }



    /**
     * setNumOfLectures allows user to set number of lectures for a module
     * @param int theNum the number of lectures given
     */
    public void setNumOfLectures(int theNum)
    {
        numOfLectures = theNum;
    }

    /**
     * getNumOfLectures returns number of lectures for a module
     * @return int the number of lectures
     */
    public int getNumOfLectures()
    {
        return numOfLectures;
    }

    /**
	 * addStudent adds a new student to the module
     */
    public void addStudent(Student theStudent)
    {
        // First check to see if student is on module
        boolean found = false;
        for (int i =0; i < studentCount; i++)
        {
            if (students[i] == theStudent)
            {
                found = true;
                break; // no need to look further
            }
        }
        if (!found)
        {
            // studentCount tells me the next free location
            students[studentCount] = theStudent;
		    studentCount = studentCount + 1;
        }
    }

    public int getStudentCount()
    {
        return studentCount;
    }

    /**
     * toString produces a formatted string containing module details
     * @return String representing formatted instance
     */
    public String toString()
    {
        String tempString;

        tempString = "\n\t The title is " + title
                     + "\n\t Code is " + code
                     + "\n\t Module coordinator is " + coordinator
					 + "\n\t Num of students on module is " + studentCount
					 + "\n";

        String studentString = "";

        for (int i=0; i < studentCount; i++)
        {
             studentString = studentString +
                             students[i].toString();
        }
        return tempString + studentString;
    }

    /**
     * main is a simple test harness for white box testing
     */
    public static void main (String [] args)
    {
        Module cs12320 = new Module("CS12320",
                                    "Concepts in programming",
                                    "Fred");

        Student first = new Student();
        first.setEmail("mbr@aber.ac.uk");
        Student second = new Student();
        second.setEmail("fwl@aber.ac.uk");
        cs12320.setCoordinator("John B.");

        int numOfLectures = 22;
        cs12320.setNumOfLectures(numOfLectures);

        cs12320.addStudent(first);
        cs12320.addStudent(second);

        System.out.println(cs12320);
    }
}

