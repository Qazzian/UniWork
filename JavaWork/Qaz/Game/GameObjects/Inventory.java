package Ian.Game.GameObjects;

/**
 * Inventory is a store of items for a character or position 
 * or anything else for that matter
 * 
 * @auther Ian Wallis
 * @version last revised 03/01/2000
 */

public class Inventory
{
	private Item[]	theItems;
	private int		itemCount;
	
	/**
	 * Default constructor
	 * sets the size of the Inventory to 50
	 */
	public Inventory()
	{
		theItems = new Item[50];
	}
	
	/**
	 * Constructor to create an Inventory of a known Size
	 */
	public Inventory(int theSize)
	{
		theItems = new Item[theSize];
	}
	
	/**
	 * Add items to the Inventory
	 */
	public void addItem(Item theItem)
	{
		theItems[itemCount] = theItem;
		itemCount++;
	}
	
	/**
	 * get Items form <code>Inventory</code>
	 * @param String thename the Name of the Item
	 * @return theItems[i] The Item Found
	 */
	public Item getItem(String theName)
	{
		for (int i=0; i<itemCount; i++)
		{
			if (theItems[i].getName.equals(theName))
			{
				return theItems[i];
			}
			else continue;
		}
	}
	
	/**
	 * remove Item from the Inventory
	 * @param String theName the Name of the Item to be removed
	 */
	public void removeItem(String theName)
	{
		for (int i = 0; i<itemCount; i++)
		{
			if (theItems[i].getname.equals(theName))
			{
				theItems[i] = theItems[itemCount-1];
				theItems[itemCount-1] = null;
				itemCount--;
				break;
			}
			else continue;
		}
	}
	
	public String toString()
	{
		String tempString = "";
		for(int i=0; i<itemCount; i++)
		{
			tempString = tempString + "\n" +
						 theIems[i].toString;
		}
	}
}