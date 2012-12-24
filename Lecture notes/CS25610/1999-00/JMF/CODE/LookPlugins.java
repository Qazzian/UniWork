import javax.media.*;
import java.util.*;

public class LookPlugins{
  
    public static void main(String args []) {
        LookPlugins first = new LookPlugins();
        first.go();
    }
    
    public LookPlugins() {
    }

    public void go() {

        reportPlugIns(PlugInManager.DEMULTIPLEXER, "DEMULTIPLEXER");
        reportPlugIns(PlugInManager.CODEC, "CODEC");
        reportPlugIns(PlugInManager.EFFECT, "EFFECT");
        reportPlugIns(PlugInManager.RENDERER, "RENDERER");
        reportPlugIns(PlugInManager.MULTIPLEXER, "MULTIPLEXER");
    }
    
    public void reportPlugIns(int plugInType, String name) {

        Vector plugInVector = PlugInManager.getPlugInList(null,null,plugInType);
        System.out.println("PlugInManager reports " + plugInVector.size() +
                " PlugIns of type " + name);
        
        for( int i = 0 ; i < plugInVector.size(); i++ ){
            System.out.println("Plugin is " + plugInVector.elementAt(i) );
            //System.out.println("Vector item class is " + plugInVector.elementAt(i).getClass());
        }
    }
}
