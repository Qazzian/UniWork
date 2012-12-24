import java.io.*;

public class TextInputReader {

	protected String inputText;
	
	protected BufferedReader stdin = new BufferedReader(
												new InputStreamReader(System.in));

	public String readText() throws IOException{
		return stdin.readLine();
	}
	
	public String readText(String msgTxt) throws IOException{
		System.out.println(msgTxt);
		return stdin.readLine();
	}
	
	public int readInt(String msgTxt) throws IOException, 
														NumberFormatException {
		return Integer.parseInt(readText(msgTxt));
	}
	
	public int readInt() throws IOException, 
										NumberFormatException {
		return Integer.parseInt(readText());
	}
}