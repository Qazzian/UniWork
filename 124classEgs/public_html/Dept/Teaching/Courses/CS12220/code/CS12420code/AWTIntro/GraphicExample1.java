import java.awt.*;
import java.awt.event.*;
import javax.swing.*;     // Bring in the Swing classes

public class GraphicExample1 extends JFrame
{

    /**
     * Constructor to create a new Example1 AWT
     */
    public GraphicExample1()
    {
        super("This is example 1");

        this.setBounds(50,50,200,100);   // x, y offset, width, height

        getContentPane().setBackground(Color.orange);

        this.addWindowListener(new WindowHandler());
    }

    /** Exit the Application */
    private void exitTool()
    {
        System.out.println("Ending tool");
        System.exit (0);
    }

    public static void main (String [] args)
    {
        GraphicExample1 firstWindow = new GraphicExample1();
        firstWindow.setVisible(true);
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