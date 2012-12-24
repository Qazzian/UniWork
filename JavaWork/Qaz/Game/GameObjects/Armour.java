package Ian.Game.GameObjects;

public class Armour extends Item
{
	private String aName;
	private String aDescription;
	
	private int headProtection;
	private int leftHandProtection;
	private int rightHandProtection;
	private int torsoProtection;
	private int legProtection;
	private int leftFootProtection;
	private int rightFootProtection;
	
	private MagicSpell itsSpell;
	
	public Armour(String theName, String theDescription,
				  int theHP, int theLHP, int theRHP, int theTP,
				  int theLP, int theLFP, int theRFP)
	{
		aName = theName;
		aDescription = theDescription;
		
		headProtection = theHP;
		leftHandProtection = theLHP;
        rightHandProtection = theRHP;
        torsoProtection = theTP;
        legProtection = theLP;
        leftFootProtection = theLFP;
        rightFootProtection = theRFP;
	}
	
	public void setSpell(MagicSpell theSpell)
	{
		itsSpell = theSpell;
	}
	
	public MagicSpell getSpell()
	{
		return itsSpell;
	}
	
	public String toString()
	{
		String tempString = "\n" + aName + aDescription;
		
		if(itsSpell != null)
			tempString = tempString + itsSpell.toString;
		
		return tempString;
	}
}