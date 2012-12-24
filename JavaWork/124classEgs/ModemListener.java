
import java.io.*;
import java.util.*;
import javax.comm.*;

/**
     To use this, you simply have an Observer with an update() method
     update() is called automatically when button is pressed
*/
public class ModemListener  extends Observable
                            implements SerialPortEventListener {
    static CommPortIdentifier portId;
    static Enumeration portList;
    static final Integer RESET = new Integer(0);
    Integer lastPressed = RESET;

    /**
    When the button changes, update() is called on Observer
    It is passed an Integer Object with the values as follows
    */
    public static final Integer CD  = new Integer(1);
    public static final Integer DSR = new Integer(2);
    public static final Integer RI  = new Integer(3);   // is this going to work?
    public static final Integer CTS = new Integer(4);
    
    SerialPort modemPort;

    public ModemListener() {
        System.out.println("New listener on modem");
        try {
            modemPort = (SerialPort) portId.open("Modem", 2000);
            modemPort.setDTR(false);  // Turn off power to buttons
                   // Above must be false WHY????
        }
        catch (PortInUseException e) {
            System.out.println("Port is busy");
        }
	    try 
        {
             modemPort.addEventListener(this);
	    }
        catch (TooManyListenersException e) {
            System.out.println("Too many listeners");
        }
        System.out.println("Listener in place");
        modemPort.notifyOnCarrierDetect(true);
        modemPort.notifyOnCTS(true);
        modemPort.notifyOnDSR(true);
        modemPort.notifyOnRingIndicator(true);
    }

    public void buttonPressed(Integer theButton)
    {
        if (lastPressed.equals(theButton)) {
            lastPressed = RESET; // ignore second press
        }
        else {
            lastPressed = theButton;
            System.out.println("Button pressed " + theButton);
            this.setChanged();  	// anything observing time is
                               // notified of change
            this.notifyObservers(theButton);
        }
    }

    public void serialEvent(SerialPortEvent ev) {
        System.out.println("Checking out event");
        switch(ev.getEventType()){
            case SerialPortEvent.CD:
               buttonPressed(CD);
               break;
            case SerialPortEvent.DSR:
               buttonPressed(DSR);
               break;
            case SerialPortEvent.RI:
               buttonPressed(RI);
               break;
            case SerialPortEvent.CTS:
               buttonPressed(CTS);
               break;
            default:
               System.out.println("Something else");
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting listener test now");
        portList = CommPortIdentifier.getPortIdentifiers();

        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                System.out.println(portId.getName());
                if (portId.getName().equals("COM2")) {
                    System.out.println("Connecting to COM2");
                    ModemListener portWaiter = new ModemListener();
                }
            }
        }
    }
}