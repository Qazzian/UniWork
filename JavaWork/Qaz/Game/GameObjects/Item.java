package Ian.Game.GameObjects;

/**
 * @auther: Ian Wallis	email: ifw9@aber.ac.uk
 * @version: last revised 29/12/1999
 * 
 * @purpose: The super class for all usable objects in the game world
 */
 
public class Item
{
	// A few Constant Valuesfor use with the game
	
	public static final int RACE_NUM = 13; // number of races
	public static final int RACE_TYPE = 3; // normal, chaos, magician
	
	public static final int STAT_COUNT = 14; // Number of Stats
	public static final int MV_CODE = 0;	// Movement code
	public static final int WS_CODE = 1;	// WeponSkill code
	public static final int BS_CODE = 2;	// Ballistic Skill code
	public static final int ST_CODE = 3;	// Strength code
	public static final int TU_CODE = 4;	// Toughness code
	public static final int HP_CODE = 5;	// HitPoint code
 	public static final int INI_CODE = 6;	// Initiative code
	public static final int LD_CODE = 7;	// Leadership code
	public static final int ATT_CODE = 8;	// AttackNo code
	public static final int MG_CODE = 9;	// Magic Level code
	public static final int GC_CODE = 10;	// GoldCoin code
	public static final int CC_CODE = 11;	// Carryingcappacity code
	public static final int LUCK_CODE = 12;	// Luck code
	public static final int EXP_CODE = 13;	// Experiance Level code
	
	public static final int LIMB_COUNT = 6; // Number of limbs on a humanoid
	public static final int PART_COUNT = 11; // number of parts on any limb
	public static final int INJURY_COUNT = 5;// max number of injuries
	
	
	
	private String Name;	// the name of the Item
	private String Description; // a description of the Item(what it is/does)
	private boolean[][] raceCode = new boolean[RACE_NUM][RACE_TYPE]
	private int[] StatBonus = new int[STAT_COUNT]
	private int iSize;		// the size of the Item 
							// indicates how much space it uses up
							// and how many hands are needed to carry it
	
	/**
	 * Deafault Constructor MUST ALWAYS have <code>iName</code> defined
	 */
	public Item(String theName)
	{
		Name = theName;
	}
	
	/**
	 * Standard constructor Sets iName, iDescription and iSize.
	 */
	public Item(String theName, String iDescription)

	{
		iName = theName;
		iDescription = iDescription;
	}
	
	public void setName(String theName)
	{
		iName = theName;
	}
	
	public String getName()
	{
		return iName;
	}
	
	public void setDiscrip(String theDis)
	{
		iDescription = theDis;
	}
	
	public String getDiscrip()
	{
		return iDescription;
	}
	
	public void setStatBonus(int theStat, int theInc)
	{
		StatBonus[theStat] = theInc
	}
	
	public int getStatBonus(int theStat)
	{
		return StatBonus[theStat];
	}
	
	public int[] getAllBonuses()
	{
		return StatBonus;
	}
	
	public String getDiscrip()
	{
		return iDescription;
	}
	
	// the methods to set the bonuses
	
	public String toString()
	{
		String tempString = iName + "\n\t" + iDescription + 
							"\nIt has a value of " + StatBonus[GC_CODE] + 
							"Gc.";
		return tempString;
	}
	
}