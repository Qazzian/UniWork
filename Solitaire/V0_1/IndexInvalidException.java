// // package Qaz.util;

public class IndexInvalidException extends IndexException
{
	public IndexInvalidException()
	{
		super("The Index is Invalid!");
	}
	
	public IndexInvalidException(String s)
	{
		super(s);
	}
}