import java.awt.*;        // Bring in the necessary classes from the AWT
import java.awt.event.*;
import java.io.*;         // Used for serializable
import javax.swing.*;

public class GraphicExample6 extends JFrame implements Serializable
{
    // This is where we link in our main class e.g. our Diary
    private Stack myStack = new Stack();

    // Define components which need to be accessed in inner classes
  
    private JTextField pushText = new JTextField(8);

    private JTextField topText = new JTextField(8);

    private JTextField depthText = new JTextField(8);

    /**
     * Constructor creates all the buttons within the window etc
     * @param theFrameName title bar for frame
     */
    public GraphicExample6(String theFrameName)
    {
        super(theFrameName);

        // Set bounds x,y,width, height all in pixels
        this.setBounds (100, 50, 300, 150);

        // We are using BorderLayout as the manager of our layout
        getContentPane().setLayout(new GridLayout(3,1));

        // add a window listener for window events
        this.addWindowListener(new WindowHandler());

        //setBackground(Color.blue);

        // Now we define our various components

        /**
         * First row PUSH button  <pushText>
         */

        // currentPanel will hold an entire row
        // Within the panel we will use flowlayout (which is the default)
        JPanel currentPanel = new JPanel(new FlowLayout());

        JButton pushButton = new JButton("PUSH");
        pushButton.addActionListener(new ButtonListener());
        currentPanel.add(pushButton);

        // pushText defined as new TextArea
        currentPanel.add(pushText);

        getContentPane().add(currentPanel);

        /**
         * Second row TOP <top of stack> POP button  
         */

        // currentPanel will hold an entire row
        // Within the panel we will use flowlayout (which is the default)
        currentPanel = new JPanel(new FlowLayout());

        JLabel topLabel = new JLabel("TOP ");
        currentPanel.add(topLabel);

        // popText defined as new TextArea
        currentPanel.add(topText);

        JButton popButton = new JButton("POP");
        popButton.addActionListener(new ButtonListener());
        currentPanel.add(popButton);

        getContentPane().add(currentPanel);

        /**
         * Third row DEPTH <depth of stack>
         */

        // currentPanel will hold an entire row
        // Within the panel we will use flowlayout (which is the default)
        currentPanel = new JPanel(new FlowLayout());

        JLabel depthLabel = new JLabel("DEPTH ");
        currentPanel.add(depthLabel);

        // depthText defined as new TextField
        currentPanel.add(depthText);

        getContentPane().add(currentPanel);
    }

    /** Exit the Application */
    private void exitTool()
    {
        System.out.println("Ending tool");
        System.exit (0);
    }

    public static void main (String [] args)
    {
        GraphicExample6 theWindow =
            new GraphicExample6("Welcome to Mark's Stack");

        theWindow.setVisible(true);
    }

    /**
     * Inner class definition *
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

            String enteredString = pushText.getText();
            try
            {
                if (buttonString.equals("PUSH"))
                {
                    myStack.push(enteredString);
                    pushText.setText("");
                    depthText.setText(" " + myStack.getDepth() +" ");
                    topText.setText((String)myStack.topOf());
                }
                if (buttonString.equals("POP"))
                {
                    myStack.pop();
                    depthText.setText(" " + myStack.getDepth() + " ");
                    topText.setText((String)myStack.topOf());
                }
            }
            catch (Exception e)
            {
                System.out.println("Ignoring exception");
            }

        }
    }
}
