import javax.swing.*;
import java.io.File;
import javax.swing.event.*;

public class FileList extends JList {
   private DefaultListModel files;
   // private FileListCellRenderer renderer = new FileListCellRenderer();
    
	public FileList(File dir) {
		files = new FileListModel(dir);
		// files is the list model of this list object
		setModel(files);
      setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      setSelectedIndex(0);
      //setFixedCellWidth(20);
      setCellRenderer(new FileListCellRenderer());
      setVisibleRowCount(12);
      addListSelectionListener(new FileListHandler());     
	}

   // inner class
   class FileListHandler implements ListSelectionListener{
	    public void valueChanged(ListSelectionEvent e) {
	    	  // Check the user isn't selecting more than one item
	    	  // they shouldn't as we have specified single selection above
	    	  // but other examples may allow multiple selections
	        if (!e.getValueIsAdjusting()){
		        JList theList = (JList)e.getSource();
		        if (theList.isSelectionEmpty()) {
		            System.out.println("Nothing selected");
		        } else {
		            int index = theList.getSelectedIndex();
		            System.out.println(files.elementAt(index));
		        }
		      }
	    }
   }

}