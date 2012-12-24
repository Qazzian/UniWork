import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.io.File;

public class Lister extends JFrame {

	public Lister() {
		super("List Demonstration");
		Container pane = getContentPane();
      setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
  
      // Provide an event handler for the window
      addWindowListener(new WindowHandler());
      
      FileList fl = new FileList(new File("."));
      pane.add(new JScrollPane(fl));
           
      pack();
      setVisible(true);
		
	}
	
	public void close() {
    	int response = JOptionPane.showConfirmDialog(this, 
                                                     "Do you wish to exit", 
                                                     "Confirm Exit", 
                                                     JOptionPane.YES_NO_OPTION);
         if (response == JOptionPane.YES_OPTION) {
             System.exit(0);
         }
    }
    
    class WindowHandler extends WindowAdapter {
       public void windowClosing(WindowEvent e) {close();}
    }   

	public static void main(String [] args) {
		new Lister();
	}
}	