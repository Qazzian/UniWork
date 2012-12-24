package Ian.Game.GameObjects;

/**
 * outfit is an array of Clothing and Armour
 */

public class Outfit
{
	private Clothing[] itsClothes;
	private int clothCount = 0;
	private Armour[] itsArmour;
	private int armourCount = 0;
	
	private int protectionCount = 0;
	
	/**
	 * Default constructor <p>
	 * Creates room for 30 pieces of clothing and 10 pieces of Armour
	 */
	public Outfit()
	{
		itsClothes = new Clothing[30];
		itsArmour = new Armour[10];
	}
	
	public void addCloth(Clothing theCloth)
	{
		itsClothes[clothCount] = theCloth;
		protectionCount = protectionCount + theCloth.getProtection;
		clothCount++;
	}
	
	public void addArmour(Armour theArmour)
	{
		itsArmour[armourCount] = theArmour;
		protectionCount = protectionCount + theArmour.getProtection;
		armourCount++;
	}
	
	public String clothString()
	{
		String cString = "\nis wearing; ";
		
		for(int i=0; i<clothCount; i++)
		{
			cString = cString + itsClothes[i].toString();
		}
		return cString;
	}
	
	public String armourString()
	{
		String aString = "\nis protected by; ";
		
		for(int i=0; i<armourCount; i++)
		{
			aString = aString + itsArmour[i].toString();
		}
		return aString;
	
	}
	
	public String toString()
	{
		String tempString = "\nis wearing; ";
		
		for(int i=0; i<clothCount; i++)
		{
			tempString = tempString + itsClothes[i].toString();
		}
		
		tempString = tempString + "\nis protected by; ";
		for(int i=0; i<armourCount; i++)
		{
			tempString = tempString + itsArmour[i].toString();
		}


	}
}