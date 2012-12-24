import java.awt.*;
import java.awt.event.*;
import javax.swing.*;     // Bring in the Swing classes
import java.io.*;         // Required for standard input from keyboard

public class GraphicExample4 extends JFrame
{
    private JTextArea userInput = new JTextArea("Default Value",20,20);
    private JLabel dayType = new JLabel("This is a weekend ");
    /**
     * Constructor to create a new example demonstrating Swing
     */
    public GraphicExample4()
    {
        super("This is example 4");

        this.setBounds(50,50,600,100);   // x, y offset, width, height

        getContentPane().setBackground(Color.orange);

        /*
         * First define a layout manager for window
         */
        getContentPane().setLayout(new BorderLayout(5,5)); // gap of 5 between components
        /*
         * The easiest way to build up an interface is to define a panel.
         * Each panel can have its own layout manager than governs layout.
         */
        JPanel masterPanel = new JPanel(new BorderLayout());
        masterPanel.add(new Label("Your data: "), BorderLayout.NORTH);
        masterPanel.add(userInput, BorderLayout.CENTER);
        // Now we add the panel to the window
        getContentPane().add(masterPanel, BorderLayout.CENTER);

        JPanel minorPanel = new JPanel (new GridLayout(1,5));
        for (int count=0; count < 5; count++)
        {
            JButton temp = new JButton ("Button " + count);
            temp.addActionListener(new ButtonListener());
            minorPanel.add(temp);
        }
        // Now we add the panel to the window
        getContentPane().add(minorPanel, BorderLayout.SOUTH);

        // add a window listener for window events
        this.addWindowListener (new WindowHandler());
    }

    public void setString(String theData)
    {
        userInput.setText(theData);
    }

    /** Exit the Application */
    private void exitTool()
    {
        System.exit (0);
    }


    public static void main (String [] args)
        throws IOException
    {

        String theString;

        BufferedReader stdin =  new BufferedReader(
                                 new InputStreamReader(System.in));


        GraphicExample4 fifthWindow = new GraphicExample4();

        fifthWindow.setVisible(true);

        fifthWindow.setString("Hello Betty");

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

            if (buttonString.equals("Button 0"))
            {
                System.out.println("Button 0 pressed");
                return;
            }
            if (buttonString.equals("Button 1"))
            {
                System.out.println("Button 1 pressed");
                return;
            }
            if (buttonString.equals("Button 2"))
            {
                System.out.println("Button 2 pressed");
                return;
            }
            System.out.println("Some other Button pressed");
        }
    }
}