import java.util.*;
import aber.util.*;

class Binary_Search_Test {
    private MyPerson people[];
    private int num;
    private Comparator c;
    
    public Binary_Search_Test(int theNum) {
        num=theNum;
        people=new MyPerson[num];
        c=new MyPerson();
    }
    
    public void fillArray() throws TextIOException {
        String first,second;
       TextInputReader t=new TextInputReader();
       for (int i=0;i<num;i++) {
          first=t.promptForString("enter first name: ");
          second= t.promptForString("enter second name: "); 
          people[i]=new MyPerson(first,second,30,Person.UNKNOWN);
       }
    }
    
    public void display() {
        System.out.println("==========================");
        for (int i=0;i<num;i++) 
            System.out.println(people[i].toString());
    }
    
    public void sortPeople() {
        Arrays.sort(people,c);
    }

    public int findSomeone(Object find) {
        return (Arrays.binarySearch(people,find,c));
    }
                
 
public static void main (String args[]) throws TextIOException{
    Binary_Search_Test myTest=new Binary_Search_Test(5);
    myTest.fillArray();
    myTest.display();
    myTest.sortPeople();
    myTest.display();
    
    int where; String first,second; MyPerson find;
    TextInputReader t=new TextInputReader();
    System.out.println("Enter details about person to search for  XXX to end");
    first=t.promptForString("enter first name: ");
    while (!first.equals("XXX") ) {
        second= t.promptForString("enter second name: ");
        find=new MyPerson(first,second,23,Person.MALE);
        where = myTest.findSomeone(find);
        System.out.println("found at position "+where);
        System.out.println("Enter details - XXX to end");
        first=t.promptForString("enter first name: ");  
    }  
} 
    
}



class MyPerson extends Person implements Comparator{
    public MyPerson() {
        super();
    }
    public MyPerson(String f,String s,int a,int g) {
        super(f,s,a,g);
    }
    
  public int compare(Object f,Object s) {
    Person first=(Person) f; Person second = (Person) s;
    if (first.getSurname().compareTo(second.getSurname())<0)
        return -1;
    else if (first.getSurname().compareTo(second.getSurname())>0)
        return 1;
    else return (first.getForename().compareTo(second.getForename()));   
  }
  
}

    
