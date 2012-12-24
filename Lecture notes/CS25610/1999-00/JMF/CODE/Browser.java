import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.net.URLConnection;

public class Browser extends Frame implements ActionListener{
    
    Button loadButton;
    FilePanel filePanel;
    SimpleControls simpleControls;

    public static void main(String argv [] ) {
        Browser browser = new Browser();
    }

    public Browser (){
        
        super("File Selector");

        setLayout(new BorderLayout());
       
        /* add a Button and the File Selection Panel */

        add(loadButton = new Button("Load"),BorderLayout.EAST);
        loadButton.addActionListener(this);
        add(filePanel = new FilePanel(), BorderLayout.NORTH);
        pack();
        setVisible(true);
    }

    public void load() {
        /* create a SimpleControls and give it file name of media */

        try {
            simpleControls = new SimpleControls(filePanel.getFilename());
            if (simpleControls != null ) {
                simpleControls.commence();
            } else System.err.println("SimpleControls returned null for " + filePanel.getFilename());
        } catch (Exception e) {
            System.err.println("Exception " + e);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        load();
    }
}

class FilePanel extends Panel {
    TextField fileField;
    public FilePanel() {
        add(new Label("FileName:"));
        add(fileField = new TextField(30));
    }
    public String getFilename() {
        return fileField.getText();
    }
}

