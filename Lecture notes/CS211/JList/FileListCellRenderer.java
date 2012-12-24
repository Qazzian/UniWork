import java.io.File;
import java.awt.Component;
import java.awt.Color;
import javax.swing.*;

class FileListCellRenderer extends JLabel implements ListCellRenderer {
     final static ImageIcon directoryIcon = new ImageIcon("dirIcon.gif");
     final static ImageIcon fileIcon = new ImageIcon("fileIcon.gif");
     
     public FileListCellRenderer() {
         super();
         setHorizontalAlignment(LEFT);
         setOpaque(true);
     }

     // This is the only method defined by ListCellRenderer.  We just
     // reconfigure the JLabel each time we're called.

     public Component getListCellRendererComponent(JList list,
                                                    Object value,            // value to display
                                                    int index,               // cell index
                                                    boolean isSelected,      // is the cell selected
                                                    boolean cellHasFocus) {  // the list and the cell have the focus
         if (value instanceof String) {
             String name = (String)value;
             File dir = new File(name);
     
             if (dir.isDirectory()) {
                setIcon(directoryIcon);
             } else {
                setIcon(fileIcon);
             }
             setText(name);
             
	        	 if (isSelected) {
	        	    setBackground(Color.red);
	        	    setForeground(Color.white);
	        	 }
	        	 else {
	        	    setBackground(list.getBackground());
	        	    setForeground(list.getForeground());
	        	 }
        	 
        	 	setEnabled(list.isEnabled());
	       	setFont(list.getFont());

	      } else {
	      		System.out.println("You have a " + value);
      	}
                 
         return this;
     }
 }