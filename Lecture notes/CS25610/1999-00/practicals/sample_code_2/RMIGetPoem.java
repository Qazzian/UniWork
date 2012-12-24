import java.rmi.*;
import java.rmi.RMISecurityManager;
import java.io.*;

public class RMIGetPoem{
    public static void main(String args[]) throws IOException{
        RMIGetPoem rmiGetPoem = new RMIGetPoem();
        rmiGetPoem.start();
    }

    public void start() throws IOException{

        // Create and install a security manager
        System.setSecurityManager(new RMISecurityManager());

        BufferedReader incoming = null;
        
        BufferedReader keyboardReader = new BufferedReader(
                new InputStreamReader(System.in) );

        String host;
        int portNumber;

        System.out.println("Please provide hostname for PoemServer:");
        host = keyboardReader.readLine();

        System.out.println("Please provide portnumber for PoemServer:");
        portNumber = Integer.parseInt(keyboardReader.readLine());

        PoemInterface remoteObject = null;

        try {
            Object serverObj = Naming.lookup("rmi://" + host + ":" + portNumber
                    + "/PoemServer");
            remoteObject = (PoemInterface) serverObj;
        } catch (Exception e) {
            System.out.println("RMIGetPoem problem " +
                    e.getMessage());
            e.printStackTrace();
        }

        StreamTokenizer sTokenizer = null;
        sTokenizer = new StreamTokenizer(keyboardReader);
        
        int commandToken=0, typeToken=0, itemToken=0;
        boolean finished = false;
        String [] stringArray = null;

        while (! finished) {
            System.out.println("Please provide command:");
            try {
                commandToken = sTokenizer.nextToken();
                switch (commandToken) {
                    case StreamTokenizer.TT_WORD:
                        String commandWord = sTokenizer.sval;
                        System.out.print("Command Word was " + commandWord);
                        if ( commandWord.equals("GET")) {
                            typeToken = sTokenizer.nextToken();
                            switch (typeToken) {
                                case StreamTokenizer.TT_WORD:
                                    if ( sTokenizer.sval.equals("POEM") ){
                                        itemToken = sTokenizer.nextToken();
                                        switch (itemToken) {
                                            case StreamTokenizer.TT_NUMBER:
                                                int poemNumber = (int) sTokenizer.nval;
                                                stringArray = remoteObject.getPoem(poemNumber);
                                                for (int i = 0 ; i < stringArray.length ; i++ )
                                                    System.out.println(stringArray[i]);
                                                break;
                                            case StreamTokenizer.TT_WORD:
                                                String itemString = sTokenizer.sval;
                                                if ( itemString.equals("NUMBER") ){
                                                    System.out.println("There are " + remoteObject.getNumber() + " poems available");
                                                } else {
                                                    if ( itemString.equals("TITLES") ) {
                                                        stringArray = remoteObject.getTitles();
                                                        for (int i = 0 ; i < stringArray.length ; i++ )
                                                            System.out.println(stringArray[i]);
                                                    }
                                                }
                                        }
                                    }
                            } 
                        } else if ( commandWord.equals("EXIT"))  finished = true; 
                }
            } catch  (IOException e) {
                System.err.println("command processing failed");
            }
        }
    }
}
                
