import javax.media.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCapture extends Frame implements ControllerListener, ActionListener {
    
    Player player;
    Component visualComponent = null;
    Component controlPanelComponent;
    Control jmdControl;
    Component jmdVisualComponent = null;
    Button stopButton = null;
    Button quitButton = null;

    /* These next two variables are used to help
       the code that waits for specified states to be reached */

    // Object waitSync = new Object();
    boolean stateTransitionOK = true;

  
    public static void main(String args []) {
        SimpleCapture first = new SimpleCapture();
        // first.capture("SunVideo");
        first.capture("sunvideo:/dev/rtvc0");
    }
    
    public SimpleCapture() {
        super("Simple Capture Program");
        add( stopButton = new Button("Stop"), BorderLayout.WEST);
        stopButton.addActionListener(this);
        add( quitButton = new Button("Quit"), BorderLayout.EAST);
        quitButton.addActionListener(this);
        pack();
        setVisible(true);
    }

    
    public void capture(String device) {

        CaptureDeviceInfo deviceInfo = CaptureDeviceManager.getDevice(device);
            System.out.println("-----------------------------------------------------------");
            System.out.println("CaptureDevice Name is " + deviceInfo.getName());
            System.out.println("-----------------------------------------------------------");
            System.out.println("CaptureDevice is " + deviceInfo);
            System.out.println("-----------------------------------------------------------");
            Format [] formatsSupported = deviceInfo.getFormats() ;
            System.out.println("Supports " + formatsSupported.length + " formats");
            for (int findex = 0 ; findex < formatsSupported.length; findex++ ) {
                System.out.println("Unique encoding name is " + formatsSupported[findex].getEncoding());
                System.out.println("Format attributes are " + formatsSupported[findex]);
            }
            System.out.println("-----------------------------------------------------------");
            System.out.println("Media Locator is " + deviceInfo.getLocator() );
            System.out.println("***********************************************************");
            try {
                player = Manager.createPlayer(deviceInfo.getLocator());
            } catch (java.io.IOException e) {
                System.out.println("IOException");
            } catch (javax.media.NoPlayerException npe) {
                System.out.println("NoPlayerException");
            }
            player.addControllerListener(this);

            System.out.println("About to call start on player");
            player.realize();
            waitForState(player.Realized);
            addVisualElements();
            setVisible(true);
            player.start();
            System.out.println("Called start on player");
            waitForState(player.Started);
            System.out.println("Player started");
    }
    
    public void addVisualElements() {

         // so we now we can ask for the visual component
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
                add(jmdVisualComponent, BorderLayout.NORTH);
            }
        }
        pack();
    }

    /**
     * Block until the processor has transitioned to the given state.
     * Return false if the transition failed.
     * This is taken from Sun's examples.
     */

    boolean waitForState(int state) {
        // synchronized (waitSync) {
        synchronized (this) {
            try {
                while (player.getState() != state && stateTransitionOK)
                    // waitSync.wait();
                    this.wait();
            } catch (Exception e) {}
        }
        return stateTransitionOK;
    }
    

    /**
     * Controller Listener.
     * This is taken from Sun's examples.
     */

    public void controllerUpdate(ControllerEvent evt) {

        System.err.println("Listener called");

        if (evt instanceof ConfigureCompleteEvent ||
            evt instanceof RealizeCompleteEvent ||
            evt instanceof PrefetchCompleteEvent ||
            evt instanceof StartEvent ||
            evt instanceof StopEvent) {
            // synchronized (waitSync) {
            synchronized (this) {
                stateTransitionOK = true;
                // waitSync.notifyAll();
                this.notifyAll();
            }
        } else if (evt instanceof ControllerClosedEvent) {
            // synchronized (waitSync) {
            synchronized (this) {
                stateTransitionOK = false;
                // waitSync.notifyAll();
                this.notifyAll();
            }
        } else System.err.println(" Event raised was " + evt.getClass());
    }

    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == stopButton) {
            if (player != null) {
                player.stop(); // NOTE: stop() is a synchronous method !
            }
        } else {
            player.stop(); // NOTE: stop() is a synchronous method !
            player.close();
            // waitForState(player.Closed);
            System.out.print("Player Closed - Exiting");
            System.exit(0);
        }
    }

}
