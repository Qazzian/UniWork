import java.awt.*;        // Bring in the necessary classes from the AWT
import java.awt.event.*;  // Bring in the event handling classes
import java.io.*;         // Used for serializable
import javax.swing.*;     // Bring in the Swing classes

public class GraphicExample5 extends JFrame implements Serializable
{
    // This is where we link in our main class e.g. our Diary
    // private VideoLinkedList theCollection = new VideoLinkedList();

    // Define components which need to be accessed in inner classes
    private Choice videoCategoryChoice = new Choice();
    private JTextArea videoNameField
                      = new JTextArea("", 1,20);
    private JTextField videoDateField = new JTextField(8);

    private List videoShowList;

    /**
     * Constructor creates all the buttons within the window
     * @param theFrameName title bar for frame
     */
    public GraphicExample5(String theFrameName)
    {
        super(theFrameName);

        // Set bounds x,y,width, height all in pixels
        this.setBounds(100,50,600,300);

        getContentPane().setBackground(Color.blue);

        // add a window listener for window events
        this.addWindowListener (new WindowHandler());

        // We are using BorderLayout as the manager of our layout
        getContentPane().setLayout(new BorderLayout());

        // Now we define our various components

        /**
         * First row Name: <name>  Category: <category> Date: <date>
         */

        // currentPanel will hold an entire row
        // Within the panel we will use flowlayout (which is the default)
        JPanel currentPanel = new JPanel(new FlowLayout());

        JLabel videoNameLabel = new JLabel("Name:",
                                           SwingConstants.CENTER);
        currentPanel.add(videoNameLabel);

        // videoNameField defined as new TextArea
        currentPanel.add(videoNameField);

        JLabel videoCategoryLabel = new JLabel("Category:", 
                                               SwingConstants.CENTER);
        currentPanel.add(videoCategoryLabel);

        // videoCategoryChoice defined as new Choice()
        videoCategoryChoice.add("Horror");
        videoCategoryChoice.add("Romance");
        currentPanel.add(videoCategoryChoice);

        JLabel videoDateLabel = new JLabel("Date Acquired:", 
                                           SwingConstants.CENTER);
        currentPanel.add(videoDateLabel);

        // videoDateField defined as new TextField(8)
        currentPanel.add(videoDateField);

        // Now add currentPanel to frame
        getContentPane().add(currentPanel,BorderLayout.NORTH);

        /**
         * Second row   [Add Button] [Remove Button] [Show Button]
         */
        // Lets create another panel
        currentPanel = new JPanel();

        JButton videoAddButton = new JButton("Add Video");
        videoAddButton.addActionListener(new ButtonListener());
        currentPanel.add(videoAddButton);

        JButton videoRemoveButton = new JButton("Remove Video");
        videoRemoveButton.addActionListener(new ButtonListener());
        currentPanel.add(videoRemoveButton);

        JButton videoShowButton = new JButton("Show Videos");
        videoShowButton.addActionListener(new ButtonListener());
        currentPanel.add(videoShowButton);

        // Now add currentPanel to frame
        getContentPane().add(currentPanel,BorderLayout.SOUTH);

        /**
         * Final row    <List of vidoes>
         */
        videoShowList = new List(10);    // look at JList
        videoShowList.setBackground(Color.blue);
        getContentPane().add(videoShowList, BorderLayout.CENTER);
    }

    /** Exit the Application */
    private void exitTool()
    {
        System.exit (0);
    } // end of event_exitTool


    /**
     * Simple test harness
     */
    public static void main (String [] args)
    {
        GraphicExample5 theWindow =
            new GraphicExample5("Welcome to Mark's Videos");

        theWindow.setVisible(true);
    }

    /*
     *                  Inner Class Definition - WindowHandler
     */

    /**
     * WindowHandler - listens out for user operations on the frame
     */
    public class WindowHandler extends WindowAdapter
    {
        public void windowClosing(WindowEvent event)
        {
            exitTool();
        }
    }

    /**
     * Inner class definition *
     */

    /**
     * Listen for button pressed
     */
    public class ButtonListener implements ActionListener, Serializable
    {

        /**
         * Depending on which button is pressed carry out action
         */
        public void actionPerformed(ActionEvent event)
        {
            String buttonString = event.getActionCommand();

            String enteredName = videoNameField.getText();
            String enteredDate = videoDateField.getText();
            String enteredCategory = videoCategoryChoice.getSelectedItem();

            if (buttonString.equals("Add Video"))
            {
                System.out.println("Adding a video");
                System.out.println("Name " + enteredName);
                System.out.println("Date " + enteredDate);
                System.out.println("Category " + enteredCategory);
            }
            if (buttonString.equals("Remove Video"))
            {
                System.out.println("Removing a video");
            }
            if (buttonString.equals("Show Videos"))
            {
                System.out.println("Showing all videos");
            }
        }
    }
}
