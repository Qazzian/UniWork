import java.awt.*;
import javax.swing.*;     // Bring in the Swing classes

public class GraphicExample0 extends JFrame
{

    /**
     * Constructor to create a new Example1 AWT
     */
    public GraphicExample0()
    {
        super("This is example 0");

        /*
         * Now let's create the graphical display
         */

        this.setBounds(50,50,200,100);   // x, y offset, width, height

        getContentPane().setBackground(Color.orange);
    }

    public static void main (String [] args)
    {
        GraphicExample0 testWindow = new GraphicExample0();
        testWindow.setVisible(true);
    }
}