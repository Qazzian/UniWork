import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ListManagerGUI extends JFrame 
                            implements ActionListener
{
   private JButton addB;
   private JButton removeB;
   private JTextField nameT;
   private PersonList listData;

   public ListManagerGUI(PersonList listData)
   {
      this.listData = listData;

      Container pane = getContentPane();
      JPanel buttonP = new JPanel();
      pane.add(buttonP, BorderLayout.CENTER);

      addB = new JButton("Add");
      addB.addActionListener(this);
      buttonP.add(addB);

      removeB = new JButton("Remove");
      removeB.addActionListener(this);
      buttonP.add(removeB);

      nameT = new JTextField(30);
      pane.add(nameT, BorderLayout.SOUTH);

      pack();
      setVisible(true);
   }

   // Method definitions for ActionListener interface

   public void actionPerformed(ActionEvent event)
   {
      // Update the PersonList data structure
      JButton source = (JButton)event.getSource();
      if (source.getText().equals("Add"))
      {
         listData.addPerson(nameT.getText());
      }
      else
      {
         listData.removePerson(nameT.getText());
      }
   }
}

