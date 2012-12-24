package Ian.Game.GameLife;

import Ian.Game.GameObjects.*;

/**
 * @auther: Ian Wallis	email: ifw9@aber.ac.uk
 * @version: last revised 01/01/2000
 * 
 * @purpose: The super class for all living organisms in the game world
 */

abstract class LifeForm
{
	private static int lfCount = 0; // a count of all the life forms
	
	// these stats only change permonantly (magic, trianing, etc)
	protected String name;  // Name of the characture
	protected char gender;  // [M]ale/[F]emale/[N]ot-applicable
	protected String race;  // The race of the lifeform
	protected boolean[] raceCode = new boolean[13];
	protected int age;		// the age of the LifeForm
	
	protected int mv;		// Movement
	protected int ws;		// Wepon Skill
	protected int bs;		// Balistic Skill
	protected int st;		// Strength
	protected int tu;		// Toughness
	protected int hp;		// Wounds or Hit Points
	protected int ini;		// Initiativeini
	protected int att;		// Attacks
	protected int ld;		// Leadership
    
	protected int ms;		// Magical Skill
	protected int gc;		// Gold Coins
	protected int cc;		// Carrying capacity
	protected int luck;		// Luck
	protected int exp;		// Experiance
		
    protected int currentDamage = 0; // a count of the damage accumilated by the lifeform

	// these are the modifid stats by tempery measures (Items etc)
	protected int bmv   = 0;
	protected int bws   = 0;
	protected int bbs   = 0;
	protected int bst   = 0;
	protected int btu   = 0;
	protected int bhp   = 0;
	protected int bini  = 0;
	protected int batt  = 0;
	protected int bld   = 0;
	
	protected int bms   = 0;
	protected int bgc   = 0;
	protected int bcc   = 0;
	protected int bluck = 0;
	protected int bexp  = 0;
	
	
	//  protected Inventory
	
	/**
	 * LifeForm creates a new LifeForm
	 * Can only be called by a sub class
	 * Must have all these parameters input
	 * Name, Gender, Race, Movement, WeaponSkill,
	 * BallisticSkill, Strength, Toughness, HitPoints,
	 * Initative, No. of Attacks, Leadership
	 */ 
	public LifeForm(String theName,
					char theGender,
					String theRace,
					int theAge,
					int theMv,int theWs,int theBs,
					int theSt,int theTu,int theHp,
					int theIni,int theAtt,int theLd)
	{
		name = theName;
		gender = theGender;
		race = theRace;
		age = theAge;
		mv = theMv;
		ws = theWs;
		bs = theBs;
		st = theSt;
		tu = theTu;
		hp = theHp;
		ini = theIni;
		att = theAtt;
		ld = theLd;
		
		for(int i=0; i<12; i++)
		{
			for(int j=0; j<3; j++)
			{
				raceCode[i][j] = false;
			}
		}
		
		lfCount++; // increments Life Form count
	}
	
	/**
	 * death is called when the life form dies
	 * Must be redifined for all character
	 */
	public void death()
	{
		lfCount--;
	}
		
 	/**
	 * toString is to give details of the life form
	 * Listing all stats, carried items etc.
	 */
	public abstract String toString();
		// Must be redifined to give details of
		// the character
	
	/**
	 * getLfCount returns the number of <code>LifeForms</code>
	 * allive at the time of calling
	 * @return lfCount The number of <code>LifeForm</code>s.
	 */
	public static int getLfCount()
	{
		return lfCount;
	}
	
	public static void reduceLfCount()
	{
		lfCount--;
	}
    
 // Here are all the set Methods ======================================
	
	/**
	 * setName changes the name of the lifeFrom
	 * @param String theName, the name of the life form.
	 */
	
	public void setName(String theName)
	{
		name = theName;
	}
	
	/**
	 * setGender changes the gender of the LifeForm
	 * M for Male, F for Fmail, N for Not-applicable,
	 * @param char theGender, the gender of the life form.
	 */
	
	public void setGender(char theGender)
	{
		gender = theGender;
	}
		
	/**
	 * setRace changes which type of being the life form is
	 * @param String theRace, the type of life form
	 */
	
	public void setRace(String theRace)
	{
		race = theRace;
	}
	
	public void changeRaceCode(int theRace, boolean fOT)
	{
		raceCode[theRace] = fOT;
	}
			
	/**
	 * setAge changes the age of the life form
	 * @param int theAge, the age in years
	 */
	
	public void setAge(int theInt)
	{
		age = theInt;
	}
	
	public void setMv(int theInt)
	{
		mv = theInt;
	}

	
	public void setWs(int theInt)
	{
		ws = theInt;
	}
			
	public void setBs(int theInt)
	{
		bs = theInt;
	}
			
	public void setSt(int theInt)
	{
		st = theInt;
	}
			
	public void setTu(int theInt)
	{
		tu = theInt;
	}
			
	public void setHp(int theInt)
	{
		hp = theInt;
	}

	
	public void setIni(int theInt)
	{
		ini = theInt;
	}
	
	public void setAtt(int theInt)
	{
		att = theInt;
	}
			
	public void setLd(int theInt)
	{
		ld = theInt;
	}
			
	public void setMs(int theInt)
	{
		ld = theInt;
	}
			
	public void setGc(int theInt)
	{
		ld = theInt;
	}
	
	public void setCc(int theInt)
	{
		ld = theInt;
	}
			
	public void setLuck(int theInt)
	{
		ld = theInt;
	}

	public void setExp(int theInt)
	{
		ld = theInt;
	}

	// Here are all the get methods ======================================
			
	/**
	 *
	 */
	public String getName()
	{
		return name;
	}
			
	/**
	 *
	 */
	public char getGender()
	{
		return gender;
	}
			
	/**
	 *
	 */
	public String getRace()
	{
		return race;
 	}
			
	/**
	 *
	 */
	public int getAge()
	{
		return age;
	}
			
	/**
	 *
	 */
	public int getMv()
	{
		return mv;
	}
			
	/**
	 *
	 */
	public int getWs()
	{
		return ws;
	}
			
	/**
	 *
	 */
	public int getBs()
	{
		return bs;
	}
			
	/**
	 *
	 */
	public int getSt()
	{
		return st;
	}
		
	/**
	 *
	 */
	public int getTu()
	{
		return tu;
	}
		
	/**
	 *
	 */
	public int getHp()
	{
		return hp;
	}
			
	/**
	 *
	 */
	public int getIni()
	{
		return ini;
	}
			
	/**
	 *
	 */
	public int getAtt()
	{
		return att;
	}
			
	/**
	 *
	 */
	public int getLd()
	{
		return ld;
	}

}