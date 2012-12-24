import javax.media.*;
import javax.media.datasink.*;
import javax.media.format.*;
import javax.media.protocol.*;
import javax.media.control.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ModelCapture extends Frame implements ControllerListener, ActionListener,
    DataSinkListener {
    
    Processor processor;
    DataSink outSink;

    Button stopButton = null;
    Button quitButton = null;
    
    Component visualComponent = null;
    Component controlPanelComponent = null;

    boolean stateTransitionOK = true;

  
    public static void main(String args []) {
        ModelCapture first = new ModelCapture();

        Format [] formats = new Format[2]; /* so we will specify two formats */
        formats[0] = new AudioFormat(AudioFormat.IMA4);
        formats[1] = new VideoFormat(VideoFormat.JPEG);

        FileTypeDescriptor outputType = new FileTypeDescriptor(FileTypeDescriptor.QUICKTIME);
        
        ProcessorModel processorModel = new ProcessorModel(formats,outputType);
        
        String filename = "file:newfile.mov";

        first.capture(processorModel, filename);

    }
    
    public ModelCapture() {
        super("Simple Capture Program");
        add( stopButton = new Button("Stop"), BorderLayout.WEST);
        stopButton.addActionListener(this);
        add( quitButton = new Button("Quit"), BorderLayout.EAST);
        quitButton.addActionListener(this);
        pack();
        setVisible(true);
    }

    
    public void capture(ProcessorModel pModel, String outputFile) {

            try {
                processor = Manager.createRealizedProcessor(pModel);
            } catch (Exception e) {
                System.out.println("Exception" + e);
            }
            processor.addControllerListener(this);
            
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
    
    public void dataSinkUpdate(DataSinkEvent dse) { 
        if (dse instanceof EndOfStreamEvent) {
            System.out.print("EndOfStream event");
            outSink.close();
        } else {
            System.out.print("datasink event was " + dse);
        }
    }
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == stopButton) {
            if (processor != null) {
                processor.stop(); // NOTE: stop() is a synchronous method !
                processor.close();
                waitForClosed();
                try {
                    Thread.currentThread().sleep(1000); // added to delay because of bugs
                } catch (Exception e) {
                    System.out.println("Exception while sleeping");
                }
            }
        } else {
            System.out.print("processor Closed - Exiting");
            System.exit(0);
        }
    }

}
