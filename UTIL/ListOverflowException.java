package Qaz.util;

public class ListOverflowException extends ListException
{
	public ListOverflowException()
	{
		super("List is full!");
	}
	
	public ListOverflowException(String s)
	{
		super(s);
	}
	
}