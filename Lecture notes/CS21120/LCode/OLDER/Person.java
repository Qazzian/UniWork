class Person implements Comparable
{
   private String name;
   private int age;

   public Person(String name, int age)
   {
      this.name = name;
      this.age = age;
   }

   public int compareTo(Object o)
   {
	   int stringComp = name.compareTo(((Person) o).getName());
	   if (stringComp != 0)
	      return stringComp;
	   return new Integer(age).compareTo
	   	(new Integer(((Person) o).getAge()));
   }

   public boolean equals(Object o)
   {
      Person p = (Person) o;

      return ((name.equals(p.getName())) &&
              (age == p.getAge()));
   }

   public String getName()
   {
      return name;
   }

   public int getAge()
   {
      return age;
   }

   // And all the sets too

   public String toString()
   {
      return "Name: " + name + "\n" +
             "Age:  " + age;
   }

}
