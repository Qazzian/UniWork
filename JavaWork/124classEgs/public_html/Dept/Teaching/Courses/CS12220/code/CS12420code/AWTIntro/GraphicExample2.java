import java.awt.*;
import java.awt.event.*;
import javax.swing.*;     // Bring in the Swing classes
import java.io.*;         // Required for standard input from keyboard


public class GraphicExample2 extends JFrame
{

    private JTextField userInput = new JTextField("Your input goes here");
    /**
     * Constructor to create a new Example1 AWT
     */
    public GraphicExample2()
    {
        super("This is example 3");

        this.setBounds(50,50,200,100);   // x, y offset, width, height

        getContentPane().setBackground(Color.orange);
        userInput.setBackground(Color.blue);
        getContentPane().add(userInput);

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

        System.out.println("Enter your string now please");

        theString = stdin.readLine();  // throws IOException

        GraphicExample2 thirdWindow = new GraphicExample2();

        thirdWindow.setVisible(true);

        thirdWindow.setString(theString);

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