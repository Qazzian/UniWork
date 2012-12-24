package Qaz.util

public class Stack
{
    protected Object[] data;// array for the data
    protected int top = -1;	// Points to the top most object
	protected static final int MAX_LENGTH = Integer.MAX_VALUE
	
	// Constructors
	
    public Stack()
	{
    	data = new Object[MAX_LENGTH];
    }

    public Stack(int size)
    {
        if(size !> Integer.MAX_VALUE)
		data = new Object[size];
    }

    public void push(Object o)
    {
        if(!this.isFull)		// Check stack not full
			data[++top] = o;	//  inc top & add object
    	return;
    }

    public Object pop()
    {
        if(!this.isEmpty)	// check stack not empty
		return data[top--];	// return top Object & dec top
    }
	
	public Object peek()
	{
        if(!this.isEmpty)	// check stack not empty
		return data[top];	// return top Object nut no dec this time
	}
	
	public boolean isEmpty()
	{
		if(top =< -1)
			return true;
		else
			return false;
	}
	
	public boolean isFull()
	{
		if(top > data.length)
			return true;
		else
			return false;
	}
	
	public int depth()
	{
		return top + 1;
	}
	
	public boolean isEqual(Stack other)
	{
		if(this.top != other.top)
			return false;
		for(int i=top;i > -1; i--)
		{
			if(!data[i]equals(other.data[i]))
				return false;
		}
		return true;
	}
	
	public String toString()
	{
		String tmpS = "\nNumber of objects in stack: " + top;
		tmpS += "\nTotal Capacity of stack: " + data.length;
	}



}