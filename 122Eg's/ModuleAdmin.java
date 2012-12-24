import aber.util.TextInputReader;
import aber.util.TextIOException;

/**
 * ModuleAdmin helps us run a single module
 */

 public class ModuleAdmin
 {
     TextInputReader t = new TextInputReader();

     public ModuleList theCollection = new ModuleList();

     /**
      * getResponseFromMenu displays menu and obtains choice from user
      * @return int the rsponse entered by the user
      */
     public int getResponseFromMenu() throws TextIOException
     {
         for (int i=0; i < 30;i++)
         {
             System.out.println();
         }
         System.out.println("Welcome to the University Module"
                             + " Admin System");
         System.out.println("\t 1. Add a new module to collection ");
         System.out.println("\t 2. Add a student to the module ");
         System.out.println("\t 3. Display all details of all module ");
         System.out.println("\t 4. Exit ");

         int option = t.promptForInt("Enter option of your choice ");
         return option;
	 }

     /**
      *  addNewModule create a completely new Module and adds to collection
      */
     public void addNewModule()
     {
         String theName = t.promptForString("Enter title ");
         String theCoord = t.promptForString("Enter coordinator ");

         // Now create the new module
		 Module theModule = new Module(theName, theCoord);

         int aYear = t.promptForInt("Enter year ");
         theModule.setYear(aYear);

         // Lets now add this new module to the collection
         theCollection.addModule(theModule);
     }

     /**
      * addAStudent adds an existing student to a current module
      * @param Student the student to be added
      */
     public void addAStudent()
     {
     }

     public static void main (String [] args) throws TextIOException
     {
         int choice = 0;
         ModuleAdmin myAdmin = new ModuleAdmin();
         Module theModule;
         do
		 {
             choice = myAdmin.getResponseFromMenu();
             System.out.println("You chose " + choice);

             switch (choice)
             {
                 case 1:
                     myAdmin.addNewModule();
                     break;
                 case 2:
                     myAdmin.addAStudent();
                     break;
                 case 3:
                     System.out.println(theModule);
                     break;
                 case 4:
                     System.out.println("Bye bye");
                 default:
                     System.out.println("Unknown option - try again");
             }
         } while (choice != 4);
     }
 }