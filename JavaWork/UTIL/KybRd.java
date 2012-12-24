// package Qaz.util;

import java.io.*;

/**
 * Privides a Class for reading Input from the Keyboard
 *
 * @see IOException
 * 
 * @author Ian Wallis
 * @version 1.00, 20th March 2000
 */
public class KybRd
{
	BufferedReader stream = new BufferedReader(
							new InputStreamReader(
							system.in));
	/*
	public KybRd()
	{
		super(System.in);
	}	*/

	/**
	 * Prints out a prompt and reads input from the Keyboard 
	 * returning it as a String.<BR>
	 * Also used to read for the other methods in the Class
	 * @param String s The prompt to be printed on the screen
	 * @return String The input from the Keyboard.
	 */
	public String readString(String s) throws IOException
	{
		System.out.println(s); 
		return stream.readLine();
	}
	
	/**
	 * Uses the readString method to read from the Keyboard and takes 
	 * the first character as the user input.
	 * @param String s The prompt to be printed to the screen.
	 * @return char The First character input through the keyboard.
	 */
	public char readChar(String s) throws IOException
	{
		return readString(s).charAt(0);
	}
	
	public byte readByte(String s) throws IOException
	{
		try
		{
			return Byte.parseByte( readString(s) );
		}
		catch(NumberFormatException e)
		{
			throw new IOException("Tat is not a valid Byte!");
		}
	}
	
	/**
	 * Uses the readString method to read from the Keyboard and converts
	 * the result to an Integer.
	 * @param String s The prompt to be printed on the screen.
	 * @return Int The integer input through the keyboared.
	 * @exception IOException
	 */
	public int readInt(String s) throws IOException
	{
        try
		{
			return Integer.parseInt( readString(s) );		
		}
		catch(java.lang.NumberFormatException e)
		{
			throw new IOException("That is not a valid integer!");
		}
	}
	

	
	
    

}