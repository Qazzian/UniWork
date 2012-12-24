import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;
import javax.media.*;
import javax.media.control.*;

public class WaitPlayer extends Frame
implements ControllerListener, ActionListener {

    Player player = null;
    
    /* These next two variables are used to help
       the coe that waits for specified states to be reached */

    Object waitSync = new Object();
    boolean stateTransitionOK = true;

    Component visualComponent;          // visual component of player
    Component controlPanelComponent;    // visual control panel component

    Control jmdControl; // a SUN Proprietary Control
    Component jmdVisualComponent; // a SUN Proprietary Control
    
    String urlName; // the name supplied for the file or url

    Control [] playerControls;
    
    /* Now a quit button and a rewind and play button */

    Button quitButton = new Button("Quit");
    Button rewindAndPlayButton = new Button("Rewind and Play");
    
    public static void main(String args []) {
        WaitPlayer first = new WaitPlayer("file:bluescreen2.mov");
        first.commence();
    }
    
    public WaitPlayer(String f) {
        super("Wait Player Example");
        urlName = f;
    }

    public void commence() {
        
        /* give us a quit Button */

        quitButton.addActionListener(this);
        add(quitButton,BorderLayout.WEST);
        
        /* give us a rewind and play again Button */
        
        rewindAndPlayButton.addActionListener(this);
        add(rewindAndPlayButton,BorderLayout.NORTH);

        /* make URL out of name supplied */

        URL url = null;
        try {
            url = new URL(urlName);
        } catch (MalformedURLException mue) {
        }

        // Create an instance of a player for this media

        try {
            player = Manager.createPlayer(url);
        } catch (NoPlayerException e) {
                Fatal("Could not create player for " + url);
        } catch (MalformedURLException e) {
            Fatal("Invalid media file URL!");
        } catch (IOException e) {
            Fatal("IO exception creating player for " + url);
        }

        // Add ourselves as a listener for a player's events

        player.addControllerListener(this);
        
        // Ask the player to Realize

        player.realize();
        
        /* Now just block on next line until state reached */
        if ( waitForState(player.Realized) ) {
            // so we now know we can ask for the visual component
            visualComponent = player.getVisualComponent();
            if (visualComponent != null ) {
                add(visualComponent,BorderLayout.CENTER);
            }
            // and get the control panel visual component too
            controlPanelComponent = player.getControlPanelComponent();
            if (controlPanelComponent != null ) {
                add(controlPanelComponent ,BorderLayout.SOUTH);
            }
            /* Now I'll try to get Sun's proprietary component that
               shows me how the JMF has plugged the application together */
            if ((jmdControl = player.getControl("com.sun.media.JMD")) != null) {
                System.err.println("Found Sun's JMD Control");
                if ((jmdVisualComponent = jmdControl.getControlComponent()) != null ) {
                    add(jmdVisualComponent, BorderLayout.EAST);
                }
            }
            pack();
        }

        pack();
        setVisible(true);
        
        // Ask the player to prefetch and fill it's buffers etc
        
        player.prefetch();

        // Now block and wait for that to prefetch to complete
        
        if ( waitForState(player.Prefetched) )
            System.err.println("Player now Prefetched");
        else
            System.err.println("Player failed to Prefetch");


    }
    
    
    /**
     * Block until the processor has transitioned to the given state.
     * Return false if the transition failed.
     * This is taken from Sun's examples.
     */
    boolean waitForState(int state) {
        synchronized (waitSync) {
            try {
                while (player.getState() != state && stateTransitionOK)
                    waitSync.wait();
            } catch (Exception e) {}
        }
        return stateTransitionOK;
    }



    /**
     * Controller Listener.
     * This is taken from Sun's examples.
     */
    public void controllerUpdate(ControllerEvent evt) {

        if (evt instanceof ConfigureCompleteEvent ||
            evt instanceof RealizeCompleteEvent ||
            evt instanceof PrefetchCompleteEvent) {
            synchronized (waitSync) {
                stateTransitionOK = true;
                waitSync.notifyAll();
            }
        } else if (evt instanceof ResourceUnavailableEvent) {
            synchronized (waitSync) {
                stateTransitionOK = false;
                waitSync.notifyAll();
            }
        } else if (evt instanceof EndOfMediaEvent) {
            System.err.println("End of Media Reached");
        } else System.err.println(" Event raised was " + evt.getClass());
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

         /* So a button was pressed, check which one */

        if ( e.getSource() == quitButton ) {
            System.out.println("He pressed the button");
            if (player != null) player.close();
            System.exit(0);
        } else if (e.getSource() == rewindAndPlayButton ) {
            /* make sure we are not started */
            if (player.getState() != player.Started) {
                player.setMediaTime( new Time(0L));       // set media time back to zero
                player.prefetch();                        // prefetch the media
                if ( waitForState(player.Prefetched) ) {
                    System.err.println("Player now Prefetched");
                    player.start();                       // and start
                } else
                    System.err.println("Player failed to Prefetch");
            }
        }
    }
    
}

