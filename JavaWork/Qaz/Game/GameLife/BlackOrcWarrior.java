package Ian.Game.GameLife;

/**
 * @auther: Ian Wallis	email: ifw9@aber.ac.uk
 * @version: last revised 01/01/2000
 * 
 * @purpose: This is the final Blueprint for a BlackOrc,
 * 			 the toughest of all the GreenSkin races
 */
 
public class BlackOrcWarrior extends GreenSkin
{
	private static int boCount = 0; // a count of the number of Black Orcs

	private Animal itsRide;; // the Animal it is riding
    /**
	 * the constructor creates a new black orc
	 * taking in parameters of it's Name, Gender, Age, Hitpoints and Ride
	 * and setting the default parameters for all other stats,
	 * It also increments the Black Orc Count.
	 */
	public BlackOrcWarrior(String theName,
						   char theGender,
						   int theAge,
						   int theHp)
   {
			// calling the constructor of 
		super(theName,theGender,"Black Ork Warrior",theAge,4,4,
			  3,4,4,theHp,2,1,8);
		
		boCount++; // Increments the number of black orcs
    }
	
	public void setRide(Animal theAnimal)
	{
		itsRide = theAnimal;
	}
	
	public Animal getRide()
	{
		return itsRide;
	}
	
	public static int getBoCount()
	{
		return boCount;
	}
	
	public static void reduceBoCount()
	{
		boCount--;
	}
	
	public String toString()
	{
		String tempString = "A " + gender + " " + race +
                    " called " + name + " that is " + age + "years old" +
                    "\n Its curent stats are;" +
                    "\n Movement = " + mv + "\t Weapon Skill = " + ws +
                    "\t Ballistic Skill = " + bs + "\n Strength = " + st + 
					"\t Toughness = " + tu + "\t Hit Points = " + hp + 
					"\n Initiative = " + ini + "\t Attacks = " + att + 
					"\t Leadership = " + ld;
		if (itsRide != null)
		{
			tempString = tempString + "\n" + name + " is riding "; // itsRide.toString();
		}
		
		return tempString;
	}
	
	public void death()
	{
		boCount--;
		super.death();
	}
}