import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class RMIPoemServer extends UnicastRemoteObject implements PoemInterface {

    Poem poems [];
    
public static void main(String args[]) throws RemoteException {
	            new RMIPoemServer();
    }
    public RMIPoemServer() throws RemoteException {
    
		// Create and install a security manager
                System.setSecurityManager(new RMISecurityManager());
                try {
                        Naming.rebind("rmi://moin.dcs.aber.ac.uk:8000/PoemServer", this);
                        
                } catch (Exception e) {
                        System.out.println("RMIPoemServer err: " + e.getMessage());
                        e.printStackTrace();
                }
                System.out.println("RMIPoemServer: I'm registered");

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
            
    }
        
    public int getNumber(){
        return poems.length;
    }

    public String [] getPoem(int poemNumber){
        
        if (poemNumber >= 0 && poemNumber < poems.length )
            return poems[poemNumber].getPoem();
        else return null;
    }

    public String [] getTitles(){
        
        String [] titles;
        
        titles = new String[poems.length];
        for (int i = 0 ; i < poems.length ; i++ )
           titles[i] = poems[i].getTitle();
        
        return titles;
    }
}
    
class Poem {
    private String poemLines [];
    private String title;
    public Poem(String [] text, String t) {
        poemLines = text;
        title = t;
    }

    public String [] getPoem(){

        return poemLines;
        
    }

    public String getTitle() {
        return title;
    }
}
