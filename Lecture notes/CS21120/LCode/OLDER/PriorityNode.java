// A node for a priority queue

public class PriorityNode implements Comparable
{
	// A priority queue node is a piece of data with a priority
   // The priority is often an integer, but can be of any comparable type
	private Object data;
	private Comparable priority;

	public PriorityNode(Object data, Comparable priority)
	{
		this.data = data;
		this.priority = priority;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public Object getData()
	{
		return data;
	}

   public void setPriority(Comparable priority)
   {
		this.priority = priority;
   }

	public Comparable getPriority()
	{
		return priority;
	}

	public int compareTo(Object otherNode)
   {
		return getPriority().compareTo(
			((PriorityNode) otherNode).getPriority());
   }

   public String toString()
   {
   	return getData() + " (priority " + getPriority() + ")";
   }
}