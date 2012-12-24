import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;
import javax.media.*;
import javax.media.control.*;
import javax.media.rtp.*;

public class SimpleControls extends Frame
implements ControllerListener, ActionListener {

    Player player = null;

    Component visualComponent;
    Component controlPanelComponent;

    GainControl gainControl;
    Component gainVisualComponent;
    
    Control jmdControl; // a SUN Proprietary Control
    Component jmdVisualComponent; // a SUN Proprietary Control
    
    String urlName; // the name supplied for the file or url

    Control [] playerControls;
    
    public static void main(String args []) {
        SimpleControls first = new SimpleControls("file:bluescreen2.mov");
        first.commence();
    }
    
    public SimpleControls(String f) {
        super("Simple Controls Example");
        urlName = f;
    }

    public void commence() {
        
        /* give us a quit Button */

        Button quitButton = new Button("Quit");
        quitButton.addActionListener(this);
        add(quitButton,BorderLayout.WEST);

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

        /* Now let's see what Control items this player supports */

        playerControls = player.getControls();

        if (playerControls.length == 0) {
            System.out.println("This player claims to have no associated controls");
        } else {
            System.out.println("This player has " + playerControls.length + " controls");
            for (int i=0 ; i < playerControls.length ; i++ ) {
                System.out.println("This control's toString gives: " +
                        playerControls[i].getClass());
                whichControl( playerControls[i]);
            }
        }

        // Add ourselves as a listener for a player's events

        player.addControllerListener(this);
        pack();
        setVisible(true);
        
        // Ask the player to Realize

        player.realize();
    }


    public synchronized void controllerUpdate(ControllerEvent event) {

        System.err.println("Listener called");

        if (event instanceof RealizeCompleteEvent) {
            System.err.println("RealizeCompleteEvent");

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

            /* Here's an example of getting a control
               which has a specific "get" method of its own */

            gainControl = player.getGainControl();
            if (gainControl != null) {
                gainControl.setLevel(1.0F);
                System.out.println("Gain level set to " + gainControl.getLevel() );
                gainVisualComponent = gainControl.getControlComponent();
                if (gainVisualComponent != null) {
                    add(gainVisualComponent,BorderLayout.EAST);
                } else {
                System.err.println("Gain Control but no Visual Component");
                }
            } else {
                System.err.println("No Gain Control");
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
        if (player != null) player.close();
        System.exit(0);
    }
    
    public void whichControl(Object c) {
        if ( c instanceof BitRateControl) {
            System.out.println("BitRateControl");
        } else if ( c instanceof BufferControl) {
            System.out.println("BufferControl");
        } else if (c instanceof CachingControl) {
            System.out.println("CachingControl");
        } else if (c instanceof ExtendedCachingControl) {
            System.out.println("ExtendedCachingControl");
        } else if (c instanceof FormatControl) {
            System.out.println("FormatControl");
        } else if (c instanceof FrameGrabbingControl) {
            System.out.println("FrameGrabbingControl");
        } else if (c instanceof FramePositioningControl) {
            System.out.println("FramePositionControl");
        } else if (c instanceof FrameProcessingControl) {
            System.out.print("FrameProcessingControl");
        } else if (c instanceof FrameRateControl) {
            System.out.print("FrameRateControl");
        } else if (c instanceof GainControl) {
            System.out.println("GainControl");
        } else if (c instanceof H261Control) {
            System.out.println("H261Control");
        } else if (c instanceof H263Control) {
            System.out.println("H263Control");
        } else if (c instanceof KeyFrameControl) {
            System.out.println("KeyFrameControl");
        } else if ( c instanceof MonitorControl) {
            System.out.println("MonitorControl");
        } else if (c instanceof MpegAudioControl) {
            System.out.println("MpegAudioControl");
        } else if ( c instanceof PacketSizeControl) {
            System.out.println("PacketSizeControl");
        } else if (c instanceof PortControl) {
            System.out.println("PortControl");
        } else if (c instanceof QualityControl) {
            System.out.println("QualityControl");
        } else if (c instanceof RTPControl) {
            System.out.println("RTPControl");
        } else if ( c instanceof SilenceSuppressionControl) {
            System.out.println("SilenceSuppressionControl");
        } else if ( c instanceof StreamWriterControl) {
            System.out.println("StreamWriterControl");
        } else if (c instanceof TrackControl) {
            System.out.println("TrackControl");
        } else System.out.println("***UnknownControl***");
    }


}
