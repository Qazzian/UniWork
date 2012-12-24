import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import java.util.Vector;
import java.util.Observer;
import java.util.Observable;

public class PersonListGUI extends JFrame
                           implements Observer
{
   private JList dataList;

   /** @link dependency 
    * @stereotype observes*/
   /*# PersonList lnkPersonList; */

   public PersonListGUI(Observable listData)
   {
      Container pane = getContentPane();

      dataList = new JList();
      JScrollPane scrollPane = new JScrollPane(dataList);
      scrollPane.setPreferredSize(new Dimension(30, 100));
      pane.add(scrollPane, BorderLayout.CENTER);

      pack();
      setVisible(true);

      // Now register myself with the Observable
      listData.addObserver(this);
   }

   // Provide Observer method implementations

   public void update(Observable o, Object arg)
   {
      if (arg instanceof Vector)
      {
         Vector data = (Vector)arg;
         dataList.setListData(data);
      }
   }
}







