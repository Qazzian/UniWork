
public class StudentDemo
{

    public static void main(String [] args)
    {
       Student fred = new Student();  // Calls default constructor

        Student mary = new Student("Mary Bott");

        mary.setName("Mary Jones");
        mary.setAge(20);
        fred.setAge(17);

        System.out.println(mary.getAge());
    }
}
