import java.awt.*;
import java.awt.event.*;
import javax.swing.*;     // Bring in the Swing classes
import java.io.*;         // Required for standard input from keyboard

public class GraphicExample7 extends JFrame
{
    /**
     * Constructor to create a new example demonstrating Swing
     */
    public GraphicExample7()
    {
        super("Radio Buttons Demo");

        this.setBounds(50,50,600,100);   // x, y offset, width, height

        getContentPane().setBackground(Color.orange);

        /*
         * The easiest way to build up an interface is to define a panel.
         * Each panel can have its own layout manager than governs layout.
         */

        JPanel minorPanel = new JPanel (new GridLayout(1,5));

        // Create the radio buttons.
        // But first, they all belong inside a ButtonGroup
        ButtonGroup radioGroup = new ButtonGroup();

        // And let's create a listener for the radio buttons.
        RadioListener myListener = new RadioListener();

        JRadioButton birdButton = new JRadioButton("Birdy");
        birdButton.setActionCommand("Birdy");
        birdButton.addActionListener(myListener);
        minorPanel.add(birdButton);
        radioGroup.add(birdButton);

        JRadioButton catButton = new JRadioButton("Pussy");
        catButton.addActionListener(myListener);
        catButton.setActionCommand("Pussy");
        minorPanel.add(catButton);
        radioGroup.add(catButton);

        JRadioButton dogButton = new JRadioButton("Doggy");
        dogButton.addActionListener(myListener);
        dogButton.setActionCommand("Doggy");
        minorPanel.add(dogButton);
        radioGroup.add(dogButton);

        JRadioButton rabbitButton = new JRadioButton("Bunny");
        rabbitButton.setActionCommand("Bunny");
        rabbitButton.addActionListener(myListener);
        minorPanel.add(rabbitButton);
        radioGroup.add(rabbitButton);


        JRadioButton pigButton = new JRadioButton("Piggy");
        pigButton.setActionCommand("Piggy");
        pigButton.addActionListener(myListener);
        minorPanel.add(pigButton);
        radioGroup.add(pigButton);

        // We have "none" to be selected first - ie no choice
        JRadioButton resetButton = new JRadioButton("none");
        resetButton.setActionCommand("none");
        pigButton.addActionListener(myListener);
        // This button isn't put on display so don't add to minorPanel
        resetButton.setSelected(true);
        radioGroup.add(resetButton);

        // Now we add the panel to the window
        getContentPane().add(minorPanel);

        // add a window listener for window events
        this.addWindowListener (new WindowHandler());
    }

    /** Exit the Application */
    private void exitTool()
    {
        System.exit (0);
    }


    public static void main (String [] args)
        throws IOException
    {
        GraphicExample7 fifthWindow = new GraphicExample7();

        fifthWindow.setVisible(true);
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
     * Listen for radio button pressed
     */
    class RadioListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event) 
        {
            String radioString = event.getActionCommand();
            System.out.println(radioString);
        }
    }
}