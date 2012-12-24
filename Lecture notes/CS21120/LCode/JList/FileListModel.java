import javax.swing.*;
import java.io.File;

public class FileListModel extends DefaultListModel {
    private File directory;
    public FileListModel(File dir) {
        directory = dir;
        listDirectoryContents();
    }

    public void relistDirectoryContents() {
        clear();
        listDirectoryContents();
    }

    private void listDirectoryContents() {
    	String [] filenames = directory.list();
      for (int i=0; i<filenames.length;i++) {
			addElement(filenames[i]);
		}  
        
    }
    
    public void setDirectory(File dir) {
        directory = dir;
        clear();
        listDirectoryContents();
    }
    
    public File getDirectory() {
        return directory;
    }


}