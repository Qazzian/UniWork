import java.io.*;
import java.net.*;

public class GetPoem {
    public static void main(String[] args) throws IOException {
        
        Socket mySocket = null;
        BufferedReader incoming = null;
        
        BufferedReader keyboardReader = new BufferedReader(
                new InputStreamReader(System.in) );

        String host;
        int portNumber;

        System.out.println("Please provide hostname for PoemServer:");
        host = keyboardReader.readLine();

        System.out.println("Please provide portnumber for PoemServer:");
        portNumber = Integer.parseInt(keyboardReader.readLine());

        

        String keyboardCommand;
        
        System.out.println("Please provide command:");

        while ((keyboardCommand = keyboardReader.readLine()) != null) {
            try {
                mySocket = new Socket(host, portNumber);
                incoming = new BufferedReader(new InputStreamReader(
                        mySocket.getInputStream()));
            } catch (UnknownHostException e) {
                System.err.println("Can't locate Host");
                System.exit(1);
            } catch (IOException e) {
                System.err.println("IO exception accessing Host");
                System.exit(1);
            }
            PrintWriter outgoing = null;
            try {
                outgoing = new PrintWriter(
                        mySocket.getOutputStream(), true);
            } catch (IOException e) {
                System.err.println("getOutputStream failed");
            }
            outgoing.println(keyboardCommand);
            String poemLine;
            while ( (poemLine = incoming.readLine()) != null) {
                System.out.println(poemLine);
            }
            System.out.println("Reply from Poem Server has ended.");
            incoming.close();
            mySocket.close();
            System.out.println("Please provide command:");
        }
    }
}
                
