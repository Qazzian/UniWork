package Ian.Game.GameEngine;

/**
 * @author: Ian Wallis	email: ifw9@aber.ac.uk
 * @version: last revised 29/12/1999
 * 
 * @purpose: positions on the game board
 * includes arrays of what the the position contains etc
 */
 
public class Position
{
	// the coordinates of the Position
	private int xCoord;
	private int yCoord;
	// Array of objects with count
	private Object[] contents = new Object[10];
	private int contentsCount = 0;
	// Array of items with count
	private Item[] theItems = new Item[5];
	private int itemCount  = 0;
	// only one life form
	private LifeForm theLifeForm;
	// only one piece of big terrain
	private TerrainBig theBigTerrain;
	// array of small terrain with count
	private TerrainSmall[] theSmallTerrain = new TerrainSmall[5];
	private int smallTerrainCount = 0;
	// boolean to say if position is occupied
	private boolean occupied = false;
	
	public void addObject(Object theObject)
	{
		// add object
		// increment count
	}
	
	public void removeObject(String theObjectID)
	{
		// search the array
		// set a handle to the index
		// set index to index[count - 1]
		// set index[count - 1] to null
		// decrement contentsCount
		// pass back object handle
	}
	
	public String toString()
	{
		String tempString = "";
		/** 
		 An inventory for the Position including all Objects inside it.
		 */
		 return tempString;
	}
}