import javax.media.*;
import java.util.*;

public class ListDevices{
  
    public static void main(String args []) {
        ListDevices first = new ListDevices();
        first.go();
    }
    
    public ListDevices() {
    }

    public void go() {

        reportCaptureDevices(null);
    }
    
    public void reportCaptureDevices(Format f) {

        Vector captureDeviceVector = CaptureDeviceManager.getDeviceList(f);
        System.out.println("CaptureDeviceManager reports " + captureDeviceVector.size() + " devices");
        
        for( int i = 0 ; i < captureDeviceVector.size(); i++ ){
            System.out.println("***********************************************************");
            CaptureDeviceInfo deviceInfo = (CaptureDeviceInfo)captureDeviceVector.elementAt(i);
            System.out.println("CaptureDevice is " + deviceInfo.getName() );
            System.out.println("-----------------------------------------------------------");
            Format [] formatsSupported = deviceInfo.getFormats() ;
            System.out.println("Supports " + formatsSupported.length + " formats");
            for (int findex = 0 ; findex < formatsSupported.length; findex++ ) {
                System.out.println("Unique encoding name is " + formatsSupported[findex].getEncoding());
                System.out.println("Format attributes are " + formatsSupported[findex]);
            }
            System.out.println("-----------------------------------------------------------");
            System.out.println("Media Locator is " +
                    ((CaptureDeviceInfo)captureDeviceVector.elementAt(i)).getLocator() );
            System.out.println("***********************************************************");
        }
    }
}
