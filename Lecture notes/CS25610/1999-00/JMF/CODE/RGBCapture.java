import javax.media.*;
import javax.media.format.*;
import javax.media.protocol.*;
import javax.media.control.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class RGBCapture extends Frame implements ControllerListener, ActionListener {
    
    DataSource captureSource;
    Player player;
    Component visualComponent = null;
    Component controlPanelComponent;
    Control jmdControl;
    Component jmdVisualComponent = null;
    Button stopButton = null;
    Button quitButton = null;

    boolean stateTransitionOK = true;

  
    public static void main(String args []) {
        RGBCapture first = new RGBCapture();
        VideoFormat rgbFormat = new VideoFormat(VideoFormat.RGB,
                new Dimension(384,288),Format.NOT_SPECIFIED,Format.byteArray,Format.NOT_SPECIFIED);
        first.capture(rgbFormat);
    }
    
    public RGBCapture() {
        super("Simple Capture Program");
        add( stopButton = new Button("Stop"), BorderLayout.WEST);
        stopButton.addActionListener(this);
        add( quitButton = new Button("Quit"), BorderLayout.EAST);
        quitButton.addActionListener(this);
        pack();
        setVisible(true);
    }

    
    public void capture(Format format) {

        Vector deviceList = CaptureDeviceManager.getDeviceList(format);
        if ( deviceList.size() > 0 ) {
            System.out.println("There are " + deviceList.size() + " devices supporting format "
                    + format );
            CaptureDeviceInfo deviceInfo = (CaptureDeviceInfo) deviceList.firstElement();
            System.out.println("-----------------------------------------------------------");
            System.out.println("CaptureDevice Name is " + deviceInfo.getName());
            System.out.println("-----------------------------------------------------------");
            System.out.println("Media Locator is " + deviceInfo.getLocator() );
            System.out.println("***********************************************************");
            try {
                captureSource = Manager.createDataSource(deviceInfo.getLocator());
                FormatControl [] formatControlArray = ((CaptureDevice) captureSource).getFormatControls();
                if (formatControlArray != null && formatControlArray.length > 0) {
                    System.out.println("Found " + formatControlArray.length + " FormatControls");
                    if ( formatControlArray[0].setFormat(format) == null ) {
                        System.out.println("Failed to set Format " + format);
                        System.exit(0);
                    }
                } else {
                    System.out.println("Failed to find FormatControls");
                    System.exit(0);
                }

                player = Manager.createPlayer(captureSource);
            } catch (NoDataSourceException ndse) {
                System.out.println("IOException");
            } catch (java.io.IOException e) {
                System.out.println("IOException");
            } catch (NoPlayerException npe) {
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
        } else System.out.println("No devices support " + format);
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
        // if ((jmdControl = player.getControl("com.sun.media.JMD")) != null) {
            // System.err.println("Found Sun's JMD Control");
            // if ((jmdVisualComponent = jmdControl.getControlComponent()) != null ) {
                // add(jmdVisualComponent, BorderLayout.NORTH);
            // }
        // }
        pack();
    }

    /**
     * Block until the processor has transitioned to the given state.
     * Return false if the transition failed.
     * This is taken from Sun's examples.
     */

    boolean waitForState(int state) {
        synchronized (this) {
            try {
                while (player.getState() != state && stateTransitionOK)
                    this.wait();
            } catch (Exception e) {}
        }
        return stateTransitionOK;
    }
    
    boolean waitForClosed() {
        synchronized (this) {
            try {
                while (stateTransitionOK)
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
            synchronized (this) {
                stateTransitionOK = true;
                this.notifyAll();
            }
        } else if (evt instanceof ControllerClosedEvent) {
            synchronized (this) {
                stateTransitionOK = false;
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
            waitForClosed();
            try {
                captureSource.stop();
            } catch (Exception e) {
                System.out.println("Exception stoping data source " + e);
            }
            try {
                Thread.currentThread().sleep(1000); // added to delay because of bugs
            } catch (Exception e) {
                System.out.println("Exception while sleeping");
            }
            System.out.print("Player Closed - Exiting");
            System.exit(0);
        }
    }

}
