package Ian.Game.GameLife;

import Ian.Game.GameObjects.*;
import java.lang.MathQaz;	// rem dRound(double a) & random()

/**
 * @auther: Ian Wallis	email: ifw9@aber.ac.uk
 * @version: last revised 01/01/2000
 * 
 * @purpose: an extension to <code>LifeForm<code>
 * to create a Greenskin such as an orc or gobolin
 */

abstract class GreenSkin extends LifeForm
{
	private static int gsCount = 0;	// A count of all the GreenSkins
	
	// bodyparts is a code for all injuries to parts of thr body
	protected Injury[][][] bodyParts = new Injury[6][11][10];
	
		// the Items It is carrying
	protected Inventory itsInv = new Inventory();
	// The Wepons it is carrying
	protected Armory itsArmory = new Armory();
		// What it is waring inc Armour
	protected Outfit itsOutfit = new Outfit();
		// The spells it knows
	protected SpellBook itsSpellBook = new SpellBook();
		
	protected int animosityValue; // a value for suseptabillity to Animosity
	// 0=not affected, 1=always affected, higher=decreas in probability
	// worked out by; randomNumber*10%animosityVaulue, if 1 then affected
	protected Animal itsRide;	// This is the animal it is riding, 
								// set to null if not riding

	/*
	 * The Constructor for creating a GreenSkin LifeForm
	 * Must have all the basic paramiters and anything else that is needed
	 * eg will need to put in Inventory Items etc
	 */	
	public GreenSkin(String theName,
					char theGender,
					String theRace,
					int theAge,
					int theMv,int theWs,int theBs,
					int theSt,int theTu,int theHp,
					int theIni,int theAtt,int theLd)
	{
		super(theName,theGender,theRace,theAge,theMv,theWs,
			  theBs,theSt,theTu,theHp,theIni,theAtt,
			  theLd);
		gsCount++;
		
		// will put in code to fill up arrays etc 
	}
	
	/**
	 * setAnimosityValue changes the effect of <code>animosity</code> 
	 * on the <code>GreenSkins</code>.
	 * <p>
	 * 0 indicates that the character is never affected
	 * 1 indicates that it is always affected
	 * From there on the higher the number the 
	 * less likly it is to be affected .
	 *
	 * @param theInt the value to be set.
	 */
	public void setAnimosityValue(int theInt)
	{
		animosityValue = theInt;
	}
	
	/**
	 * getAnimosityValue returns the effectivness of <code>animosity</code>
	 * on the <code>GreenSkins</code>.
	 *
	 * @return animosityValue the effectivness of <code>animosity</code>.
	 */
	public int getAnimosityValue()
	{
		return animosityValue;
	}
	
	/**
	 * animosityTest is a random test to see if the <code>GreenSkin</code>
	 * is affected by <code>animosity</code>
 	 * <p>
	 * The following equation is used to see if <code>animosity</code>
	 * takes effect
	 * <p> Math.round( Math.random*100 ) % (AnimosityValue+theMod)
	 * an output of 1 indicates that the <code>GreenSkin</code> is affected
	 * <p> 
	 */
	public boolean animosityTest(int theMod)
	{
		boolean affected = false;
		
		double d = MathQaz.random()*100;
		int i = MathQaz.dRound(d);
		int j = animosityValue + theMod;
		int a = i % j;
		
		if (a==1)
		{
			affected = true;
		}
		
		return affected;
	}
	
	/**
	 * This test is for unmodified animosity tests
	 *
	 * @return affected wether the <code>GreenSkin</code> has been affected
	 * <p>
	 * uses same equation as previous method.
	 */
	
	public boolean animosityTest()
	{
		boolean affected = false;
		
		double d = MathQaz.random()*100;
		int i = MathQaz.dRound(d);
		int a = i % animosityValue;
		
		if (a==1)
		{
			affected = true;
		}
		
		return affected;
	}
 
	
	public void setRide(Animal theAnimal)
	{
		itsRide = theAnimal;
	}
	
	public Animal getRide()
	{
		return itsRide;
	}
	
	public static int getGsCount()
	{
		return gsCount;
	}
	
	public static void reduceGsCount()
	{
		gsCount--;
	}
	
	public String toString()
	{
		String tempString = "";
		
		return tempString;
	}

	public void death()
	{
		super.death();
		gsCount--;		
	}
}