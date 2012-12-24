public interface PoemInterface extends java.rmi.Remote {

    public int getNumber() throws java.rmi.RemoteException;
    public String [] getPoem(int poemNumber) throws java.rmi.RemoteException;
    public String [] getTitles() throws java.rmi.RemoteException;
}
