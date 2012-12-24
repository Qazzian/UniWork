/**
 * Zoo demonstrates polymorphism
 * @author class of CS12220
 * @version 13/12/99
 */
public class Zoo
{
    private Animal [] theList;
    private int animalCount = 0;

    public Zoo(int numOfAnimals)
    {
        theList = new Animal[numOfAnimals];
    }

    public void add(Animal theBeast)
    {
        theList[animalCount] = theBeast;
        animalCount++;
    }

    public String toString()
    {
        String tempString = "";

        for (int i=0; i < animalCount; i++)
        {
            tempString = tempString + theList[i];
            System.out.println(theList[i].makeNoise());
        }
        return tempString;
    }

    public static void main (String [] args)
    {
        Zoo chester = new Zoo(10);

        Dog fluf = new Dog(3);

        chester.add(fluf);

        Duck daffy = new Duck(2);

        chester.add(daffy);

        chester.add(new Duck(3));

        System.out.println(chester);

 
    }
}