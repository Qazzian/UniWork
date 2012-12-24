import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.net.URLConnection;

public class BrowserApplet extends Applet implements ActionListener{
    
    Button loadButton;
    CheckPanel checkPanel;
    MessagePanel messagePanel;
    FilePanel filePanel;
    AppletContext myContext;

    public void init (){

        myContext = getAppletContext();
       
        setLayout(new BorderLayout());
       
        add(loadButton = new Button("Load"),BorderLayout.EAST);
        loadButton.addActionListener(this);
        add(checkPanel = new CheckPanel(),BorderLayout.WEST);
        ScrollPane messagePane = new ScrollPane();
        messagePane.add(messagePanel = new MessagePanel());
        add(messagePane,BorderLayout.SOUTH);
        add(filePanel = new FilePanel(), BorderLayout.NORTH);
        validate();
    }
    public void start(){
        messagePanel.logMessage("Start called");
    }
    public void stop(){
        messagePanel.logMessage("Stop called");
    }
    public void load() {
        String windowWanted;
        if( (windowWanted = checkPanel.windowSelected()) != null) {
            URL documentBase = getDocumentBase();
            try {
                URL u = new URL(documentBase, filePanel.getFilename());
                System.err.println("URL is " + u.toString());
                myContext.showDocument(u, windowWanted);
            } catch (Exception e) {
            }
        }
    }

    public void actionPerformed(ActionEvent ae) {
        load();
    }
}

class FilePanel extends Panel {
    TextField fileField;
    public FilePanel() {
        add(new Label("WebReference:"));
        add(fileField = new TextField(30));
    }
    public String getFilename() {
        return fileField.getText();
    }
}

class MessagePanel extends Panel {
    public MessagePanel() {
        add(new Label("Message Panel"));
    }
    public void logMessage(String message) {
        add( new Label(message));
    }
}

class CheckPanel extends Panel {

    Checkbox selfCheckbox;
    Checkbox parentCheckbox;
    Checkbox topCheckbox;
    Checkbox blankCheckbox;
    CheckboxGroup windowCBG;
    
    public CheckPanel () {

        setLayout(new GridLayout(5,1));

        windowCBG = new CheckboxGroup();

        add( new Label("Select Window for Display"));
        add(selfCheckbox = new Checkbox("self", windowCBG, true));
        add(parentCheckbox = new Checkbox("parent", windowCBG, false));
        add(topCheckbox = new Checkbox("top", windowCBG, false));
        add(blankCheckbox = new Checkbox("blank", windowCBG, false));
        
    }

    String windowSelected() {
        Checkbox selected = windowCBG.getSelectedCheckbox();
        if (selected == selfCheckbox) return "_self";
        else if (selected == parentCheckbox) return "_parent";
        else if (selected == topCheckbox) return "_top";
        else if (selected == blankCheckbox) return "_blank";
        else return null;
    }
    
}
    
