import javax.media.*;
import javax.media.datasink.*;
import javax.media.format.*;
import javax.media.protocol.*;
import javax.media.control.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Convert extends Frame implements ControllerListener, ActionListener,
    DataSinkListener {
    
    Processor processor;
    DataSink outSink;

    Button quitButton = null;
    
    Component visualComponent = null;
    Component controlPanelComponent = null;
    
    boolean stateTransitionOK = true;

    public static void main(String args []) {
        Convert first = new Convert();

        first.capture("file:kayaker.avi", "file:newfile.mov",
                new VideoFormat(VideoFormat.CINEPAK));
    }
    
    public Convert() {
        super("Simple Capture Program");
        add( quitButton = new Button("Quit"), BorderLayout.EAST);
        quitButton.addActionListener(this);
        pack();
        setVisible(true);
    }

    
    public void capture(String inFile, String outputFile, VideoFormat targetFormat) {

        MediaLocator inLocator = new MediaLocator(inFile);
        System.out.println("Media locator is " + inLocator);

            try {
                processor = Manager.createProcessor(inLocator);
            } catch (Exception e) {
                System.out.println("Exception" + e);
            }
            processor.addControllerListener(this);

            processor.configure();
            waitForState(Processor.Configured);
            
            TrackControl [] trackControls = processor.getTrackControls();
            
            if (trackControls != null && trackControls.length != 0 ) {
                System.out.println("There are " + trackControls.length + " Tracks");
                for (int i = 0 ; i < trackControls.length ; i++ ) {
                    if ( trackControls[i].getFormat() instanceof VideoFormat ) {
                        Format [] supportedFormats = trackControls[i].getSupportedFormats();
                        for ( int j = 0 ; j < supportedFormats.length ; j++ ) {
                            if ( supportedFormats[j].matches(targetFormat) ) {
                                System.out.println("Matched " + supportedFormats[j]);
                                trackControls[i].setFormat(supportedFormats[j]);
                                break;
                            } else {
                                System.out.println("Failed to match " + supportedFormats[j]);
                            }
                        }
                    }
                }
            } else {
                System.out.println("There are no Tracks");
            }
            
            processor.setContentDescriptor(new FileTypeDescriptor(FileTypeDescriptor.QUICKTIME));

            processor.realize();
            waitForState(Processor.Realized);
            
            DataSource outputData = processor.getDataOutput(); // get the datasource from the processor
            
            MediaLocator outMediaLocator = new MediaLocator(outputFile); 
            
            try {
                outSink = Manager.createDataSink(outputData, outMediaLocator);
                outSink.open();
            } catch (Exception e) {
                System.out.println("Exception occured " + e);
                System.exit(0);
            }

            outSink.addDataSinkListener(this);

            System.out.println("About to call start on processor");
            addVisualElements();
            pack();
            setVisible(true);
            try {
                outSink.start();
                processor.start();
            } catch (Exception e) {
                System.out.println("Exception on start " + e);
            }
            System.out.println("Called start on processor");
            waitForState(processor.Started);
            System.out.println("processor started");
            waitForClosed();
            System.out.println("processor closed");
    }
    
    public void addVisualElements() {

         // so we now we can ask for the visual component
        visualComponent = processor.getVisualComponent();
        if (visualComponent != null ) {
            add(visualComponent,BorderLayout.CENTER);
        }
        // and get the control panel visual component too
        controlPanelComponent = processor.getControlPanelComponent();
        if (controlPanelComponent != null ) {
            add(controlPanelComponent ,BorderLayout.SOUTH);
        }
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
                while (processor.getState() != state && stateTransitionOK)
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
                evt instanceof StartEvent ) {
            System.err.println(" Event raised was " + evt.getClass());
            synchronized (this) {
                stateTransitionOK = true;
                this.notifyAll();
            }
        } else if (evt instanceof ControllerClosedEvent) {
            synchronized (this) {
                stateTransitionOK = false;
                this.notifyAll();
            }
        } else if (evt instanceof DurationUpdateEvent) {
            System.out.println("DurationUpdateEvent, duration is " +
                    ((DurationUpdateEvent)evt).getDuration().getSeconds() + " seconds");
        } else if (evt instanceof StopEvent) {
            System.out.println("STOP Event Occurred");
            processor.close();
        } else System.err.println(" Event raised was " + evt.getClass());
    }
    
    public void dataSinkUpdate(DataSinkEvent dse) { 
        if (dse instanceof EndOfStreamEvent) {
            System.out.print("EndOfStream event");
            outSink.close();
        } else {
            System.out.print("datasink event was " + dse);
        }
    }
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == quitButton) {
            System.out.println("Exiting");
            System.exit(0);
        }
    }

}
