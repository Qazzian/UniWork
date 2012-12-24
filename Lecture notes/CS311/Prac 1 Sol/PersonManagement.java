public class PersonManagement
{
   public static void main(String [] args)
   {
      PersonList dataList = new PersonList();
      PersonListGUI listView = new PersonListGUI(dataList);
      ListManagerGUI managerView = new ListManagerGUI(dataList);
   }

   /** @link dependency 
    * @stereotype instantiate*/
   /*# PersonListGUI lnkPersonListGUI; */

   /** @link dependency 
    * @stereotype instantiate*/
   /*# PersonList lnkPersonList; */

   /** @link dependency 
    * @stereotype instantiate*/
   /*# ListManagerGUI lnkListManagerGUI; */
}