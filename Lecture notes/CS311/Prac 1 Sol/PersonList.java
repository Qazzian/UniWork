import java.util.Observable;
import java.util.Vector;
public class PersonList extends Observable
{
   private Vector list = new Vector();

   public void addPerson(String p)
   {
      list.add(p);
      this.setChanged();
      this.notifyObservers(list.clone());
   }

   public void removePerson(String p)
   {
      list.remove(p);
      this.setChanged();
      this.notifyObservers(list.clone());
   }

}






