import java.io.*

public class TextInputReader {

	protected String inputText;
	
	protected BufferedReader stdin = new BufferedReader(
												new InputStreamReader(System.in));

	public String readText() throws TextIOException{
		return readText("Type String Now :> ");
	}
	
	public String readText(String msgTxt) throws TextIOException{
		System.out.println(msgTxt);
		return stdin.readLine();
	}
	
	public int readInt(String msgTxt) throws TextIOException, 
																	NumberFormatException {
		return Integer.parseInt(readText(msgTxt));
	}
}