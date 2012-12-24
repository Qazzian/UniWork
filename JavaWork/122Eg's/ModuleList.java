/**
 * ModuleList maintains a collection of modules
 * @author Class of CS12220 (evening)
 * @version 22/11/99
 */
public class ModuleList
{
     private Module [] theList = new Module[10];

     private int moduleCount = 0;

     /**
      * addModule adds an existing module to list
      */
	 public void addModule(Module theModule)
     {
         if (moduleCount < theList.length)
         {
             theList[moduleCount] = theModule;
             moduleCount++;
         }
         else
         {
             System.out.println("Too many modules - module not added");
         }
     }

     /**
      * removeModule removes a module from a list
      * @param String theCode the code of the module to be removed
      */
     public void removeModule(String theCode)
     {
         int locationToDelete;
         boolean found = false;
         // first locate item in array
         for (locationToDelete =0;
		      locationToDelete < moduleCount;
			  locationToDelete++)
         {
             if (theList[locationToDelete].getCode().equals(theCode))
             {
                 found = true;
                 break;
             }
         }

         // test to see if searched item is present or not
         if (!found)
         {
             System.out.println("Didn't find module name in list" +
			                    " - nothing removed");
             return;  // ie item was not found
         }

         // reduce moduleCount to reflect removal
         moduleCount--;
         // copy last entry into current position
         theList[locationToDelete] = theList[moduleCount];
         // set last entry (now a copy) to null
         theList[moduleCount] = null;
     }

     /**
      * getModule retrieves a module from list
      * @param String theName the name of the module to be found
      * @return Module the module reference found in the list
      */
     public Module getModule(String theCode)
     {
         for (int i = 0; i < moduleCount; i++)
         {
             // if we find what we are looking for, return module
             if (theList[i].getCode().equals(theCode))
             {
                 return theList[i];
             }
		 }
         return null;
     }

     /**
      * toString returns a formatted representation of list
      * @return String the formatted list
      */
     public String toString()
     {
         String tempString = "\n";

         for (int i = 0; i < moduleCount; i++)
         {
             tempString = tempString 
			              + theList[i].toString()
						  + "\n";
         }
         return tempString;
     }

     /**
      * getModuleCount returns the number of modules in list
      * @return int the number of modules in list
      */
     public int getModuleCount()
     {
         return moduleCount;
     }

     /**
      * main - NOT FOR GENERAL USE
      * This is for test purposes only
      */
     public static void main(String [] args)
     {
         ModuleList collection = new ModuleList();
         Module m1 = new Module("Intro to Programming", "CS12220");

         Module m2 = new Module("Concepts in Programming", "CS12320");

         Student s1 = new Student ("Mr.", "Bill", "Bloggs",
                                     "", "", "");
         Student s2 = new Student ("Mr.", "Tom", "Davies",
                                     "", "", "");

         m1.addStudent(s1);
         m2.addStudent(s2);
         collection.addModule(m1);
         collection.addModule(m2);

         m1 = new Module("More programming", "CS12420");
         collection.addModule(m1);

         Module findIt;
         findIt = collection.getModule("CS12220");

         System.out.println("\nDetails on CS12220 are " + findIt);
         System.out.println("\n The list of modules is now: " + collection);

         System.out.println("Removing CS12220");
         collection.removeModule("CS12220");
         System.out.println("\n The list of modules is now: " + collection);

         System.out.println("Removing CS12220");
         collection.removeModule("CS12220");
         System.out.println("\n The list of modules is now: " + collection);

         System.out.println("Removing CS12420");
         collection.removeModule("CS12420");
         System.out.println("\n The list of modules is now: " + collection);

     }
}