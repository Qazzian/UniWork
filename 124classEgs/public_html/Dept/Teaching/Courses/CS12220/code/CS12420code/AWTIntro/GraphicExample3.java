import java.awt.*;
import java.awt.event.*;
import javax.swing.*;     // Bring in the Swing classes
import java.io.*;         // Required for standard input from keyboard


public class GraphicExample3 extends JFrame
{
       // trainlisting shows all passengers on train
    private JTextArea userInput = new JTextArea("Default Value",20,20);
    private JLabel dayType = new JLabel("This is not a weekend ");
    /**
     * Constructor to create a new Example1 AWT
     */
    public GraphicExample3()
    {
        super("This is example 1");

        this.setBounds(50,50,200,100);   // x, y offset, width, height

        getContentPane().setBackground(Color.orange);

        /*
         * The easiest way to build up an interface is to define a panel.
         * Each panel can have its own layout manager than governs layout.
         */
        JPanel masterPanel = new JPanel(new BorderLayout());
        masterPanel.add(new Label("Your data: "), BorderLayout.WEST);
        masterPanel.add(userInput, BorderLayout.CENTER);
        userInput.setBackground(Color.red);
        masterPanel.add(dayType, BorderLayout.NORTH);
        // Now we add the panel to the window
        getContentPane().add(masterPanel);

        this.addWindowListener(new WindowHandler());
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


        GraphicExample3 fourthWindow = new GraphicExample3();

        fourthWindow.setVisible(true);

        System.out.println("Enter you string now please");

        theString = stdin.readLine();  // throws IOException




        fourthWindow.setString(theString);

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
}