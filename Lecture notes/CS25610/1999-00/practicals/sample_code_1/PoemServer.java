import java.io.*;
import java.net.*;

public class PoemServer{
    Poem poems [];

    public static void main(String[] args) throws IOException {
        new PoemServer();
    }
    
    public PoemServer(){

        String tempPoem0 [] = { "Mary had a little lamb",
            "Its fleece was as white as snow", "Everywhere that Mary went",
            "The lamb was sure to go."};
            
        String tempPoem1 [] = { "Ba, Ba Black Sheep",
            "Have you any wool,", "Yes Sir, Yes Sir,",
            "Three bags Full", "One for the master", "And one for the dame",
            "And for for the little boy that lives down the lane."};
            
        String tempPoem2 [] = { "Mary, Mary, quite contrary",
            "How does your garden grow?", "With silver bells and cockle-shells",
            "And pretty maids all in a row."};
            
            poems = new Poem[3];
            
            poems[0] = new Poem(tempPoem0, "Mary's Lamb");
            poems[1] = new Poem(tempPoem1, "Black Sheep");
            poems[2] = new Poem(tempPoem2, "Contrary Mary");
        
        ServerSocket mySocket = null;
        
        try {
            mySocket = new ServerSocket(4000,3);
        } catch (IOException e) {
            System.err.println("IO exception on port");
            System.exit(1);
        }
        
        System.out.println("Have ServerSocket about to wait for Call");
        
        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = mySocket.accept();
            } catch (IOException e) {
                System.err.println("accept of incoming client call failed");
            }

            System.out.println("Incoming Call Accepted");
            
            processCommand(clientSocket);

            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("closing client socket failed");
            }
        }
    }
    public boolean processCommand(Socket socket){

        InputStreamReader inputStream = null;
        StreamTokenizer sTokenizer = null;
        int commandToken=0, typeToken=0, itemToken=0;

        try {
            PrintWriter outWriter = new PrintWriter(
                    socket.getOutputStream(),true);

            inputStream = new InputStreamReader (
                    socket.getInputStream());
            sTokenizer = new StreamTokenizer(inputStream);

            commandToken = sTokenizer.nextToken();

            switch (commandToken) {
                case StreamTokenizer.TT_WORD:
                    if ( sTokenizer.sval.equals("GET")) {
                        typeToken = sTokenizer.nextToken();
                        switch (typeToken) {
                            case StreamTokenizer.TT_WORD:
                                if ( sTokenizer.sval.equals("POEM") ){
                                    itemToken = sTokenizer.nextToken();
                                    switch (itemToken) {
                                        case StreamTokenizer.TT_NUMBER:
                                            int poemNumber = (int) sTokenizer.nval;
                                            if ((poemNumber >= 0) && (poemNumber < poems.length) ) {
                                                poems[poemNumber].sendTitle(outWriter);
                                                poems[poemNumber].sendPoem(outWriter);
                                                break;
                                            } else return false;
                                        case StreamTokenizer.TT_WORD:
                                            String itemString = sTokenizer.sval;
                                            if ( itemString.equals("NUMBER") ){
                                                outWriter.println("There are " + poems.length + " poems available");
                                                return true;
                                            } else {
                                                if ( itemString.equals("TITLES") ) {
                                                    for (int i = 0 ; i < poems.length ; i++ )
                                                        poems[i].sendTitle(outWriter);
                                                }
                                            }
                                        default: return false;
                                    }
                                } else {
                                    return false;
                                }
                            default: return false;
                        }
                    }
                default:
                    return false;
            }
        } catch  (IOException e) {
            System.err.println("command processing failed");
            return false;
        }
    }
}
                
class Poem {
    private String poemLines [];
    private String title;
    public Poem(String [] text, String t) {
        poemLines = text;
        title = t;
    }

    public boolean sendPoem(PrintWriter outWriter) {


        try {
        
        for(int line = 0; line < poemLines.length ; line ++)
            outWriter.println(poemLines[line]);
        } catch (Exception e) {
            return false;
        }
        return true;
        
    }

    public boolean sendTitle(PrintWriter outWriter) {

        try {
        
            outWriter.println(title);
        } catch (Exception e) {
            return false;
        }
        return true;
        
    }

    public String getTitle() {
        return title;
    }
}
