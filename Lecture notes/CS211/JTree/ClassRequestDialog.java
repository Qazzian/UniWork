//package swing.browser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClassRequestDialog extends JDialog {
    JTextField field = new JTextField(10);
    String text;
    
    /**
     * ClassRequestDialog displays a dialog to get a new class 
     * to display.
     */
    public ClassRequestDialog(JFrame frame) {
        super(frame, "Class Selector", true);
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(2, 2));
        pane.add(new JLabel("Please specify a class: "));
        pane.add(field);
        JButton b = new JButton("Select");
        Handler h = new Handler();
        b.addActionListener(h);
        pane.add(b);
        b = new JButton("Cancel");
        b.addActionListener(h);
        pane.add(b);
        
        pack();
    }

    public String getText() {
      return text;
    }
     
    /** 
     * Inner class to handle user interaction
     */
    class Handler implements ActionListener {
    
       public void actionPerformed(ActionEvent event) {
          String string = event.getActionCommand();
          if (string.equals("Select")) {
            text = field.getText();
          } else {
            text = "";  
          }
          ClassRequestDialog.this.setVisible(false);   
       }
    
    }
     

}
