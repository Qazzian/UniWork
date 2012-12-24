import aber.util.TextInputReader;
import aber.util.TextIOException;

/**
* Details of a person
* @author Mark Ratcliffe
* @version first written 19/10/99
*
*/

//define a new class called student
public class Student
{
    //declare attributes
    private String title;       // the title of a person e.g. Mr
    private String surname;     // surname of the person
    private String forename;    // first name of the person
    private String address;     // house number and road
    private String phoneNum;    // the phone number of the person
    private String eMail;       // the e_mail address

    /**
     * Default constructor - initialises attributes
     */
    public Student()
    {
        title = "Unknown";
        surname = "Unknown";
        forename = "Unknown";
        address = "Unknown";
        phoneNum = "Unknown";
        eMail = "Unknown";
    }

    /**
     * Alternative constructor - initialises attributes as provided
     * @param theTitle String representing title of person
     * @param theSurname String representing surname
     * @param theForename String representing forename
     * @param theAddress String representing address
     * @param thePhoneNum String representing phone number
     * @param theEmail String representing email address
     */
    public Student(String theTitle,
                  String theSurname,
                  String theForename,
                  String theAddress,
                  String thePhoneNum,
                  String theEmail)

    {   //set the instance variables for the object
        title = theTitle;
        surname = theSurname;
        forename = theForename;
        address = theAddress;
        phoneNum = thePhoneNum;
        eMail = theEmail;
    }

    /**
     * setTitle allows user to change title.
     * @param theTitle String representing new title
     */
    public void setTitle(String theTitle)
    {
        title = theTitle;
    }

    /**
     * setSurname allows user to change surname.
     * @param theSurname String representing new surname
     */
    public void setSurname(String theSurname)
    {
        surname = theSurname;
    }

    /**
     * setForename allows user to change forename.
     * @param theForename String representing new forename
     */
    public void setForename(String theForename)
    {
        forename = theForename;
    }

    /**
     * setAddress allows user to change address.
     * @param theAddress String representing new address
     */
    public void setAddress(String theAddress)
    {
        address = theAddress;
    }

    /**
     * setPhoneNum allows user to change phoneNum.
     * @param thePhoneNum String representing new phoneNum
     */
    public void setPhoneNum(String thePhoneNum)
    {
        phoneNum = thePhoneNum;
    }

    /**
     * setEmail allows user to change theEmail.
     * @param theEmail String representing new theEmail
     */
    public void setEmail(String theEmail)
    {
        eMail = theEmail;
    }

    /**
     * getTitle allows user to obtain title.
     * @return String the value of title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * getSurname allows user to obtain surname.
     * @return String the value of surname
     */
    public String getSurname()
    {
        return surname;
    }

    /**
     * getForename allows user to obtain forename.
     * @return String the value of forename
     */
    public String getForename()
    {
        return forename;
    }

    /**
     * getAddress allows user to obtain address.
     * @return String the value of address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * getPhoneNum allows user to obtain phoneNum.
     * @return String the value of phoneNum
     */
    public String getPhoneNum()
    {
        return phoneNum;
    }

    /**
     * getEmail allows user to obtain the Email address.
     * @return String the value of the Email address
     */
    public String getEmail()
    {
        return eMail;
    }

    //to convert all attributes of the class into one string
    public String toString()
    {
        String tempString;
        tempString = "Name of student: " + title +" "
                   + forename + " " + surname + "\n"
                   + "Address of student: " + address + "\n"
                   + "Phone Number: "  + phoneNum + "\n"
                   + "e-mail address: " + eMail +"\n";
        return tempString;
    }


    public static void main(String [] args) throws TextIOException
    {
        Student temp1 = new Student();
        // temp1.setDetails(); // Haven't done this yet!!!!
    }
}
