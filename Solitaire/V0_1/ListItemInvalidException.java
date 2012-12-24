// package Qaz.util;

public class ListItemInvalidException extends ListException
{
	public ListItemInvalidException()
	{
		super("The Item is invalid for this list!");
	}
	
	public ListItemInvalidException(String s)
	{
		super(s);
	}
}