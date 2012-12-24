import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;
import javax.media.*;

public class WebSimplePlayer extends Frame
implements ControllerListener, ActionListener {
    Player player = null;
    
    public static void main(String args []) {
        WebSimplePlayer first = new WebSimplePlayer();
        first.go();
    }
    
    public WebSimplePlayer() {
        super("Daves' Frame");
    }

    public void go() {
        
        Button quitButton = new Button("Quit");
        quitButton.addActionListener(this);
        add(quitButton,"East");

        URL url = null;
        String mediaFile = null;
        try {
            url = new URL("http://www.aber.ac.uk/~dap/JMF/sample.mov");
        } catch (MalformedURLException mue) {
        }

        try {
            // Create an instance of a player for this media
            try {
                player = Manager.createPlayer(url);
            } catch (NoPlayerException e) {
                System.out.println(e);
                Fatal("Could not create player for " + url);
            }
        } catch (MalformedURLException e) {
            Fatal("Invalid media file URL!");
        } catch (IOException e) {
            Fatal("IO exception creating player for " + url);
        }
        // Add ourselves as a listener for a player's events
        player.addControllerListener(this);
        player.start();
        pack();
        setVisible(true);
    }


    public synchronized void controllerUpdate(ControllerEvent event) {

        Component visualComponent;

        System.err.println("Listener called");

        if (event instanceof RealizeCompleteEvent) {
            System.err.println("RealizeCompleteEvent");
            visualComponent = player.getVisualComponent();
            add(visualComponent);
            visualComponent.setBounds(0, 0, 200, 100);
            pack();

        } else {
            System.err.println("Another Event " + event.toString());
        }
    }

    void Fatal (String s) {
        // Applications will make various choices about what
        // to do here. We print a message
        System.err.println("FATAL ERROR: " + s);
        throw new Error(s); // Invoke the uncaught exception
                            // handler System.exit() is another
                            // choice.

    }
    

    
    public void actionPerformed(ActionEvent e) {
        System.out.println("He pressed the button");
        System.exit(0);
    }

}
