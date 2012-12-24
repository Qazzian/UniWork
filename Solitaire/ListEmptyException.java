// package Qaz.util;

public class ListEmptyException extends ListException
{
	public ListEmptyException()
	{
		super("List is Empty!");
	}
	
	public ListEmptyException(String s)
	{
		super(s);
	}
	
}