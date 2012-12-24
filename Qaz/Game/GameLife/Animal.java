package Ian.Game.GameLife;

/**
 * @auther: Ian Wallis	email: ifw9@aber.ac.uk
 * @version: last revised 01/01/2000
 * 
 * @purpose: This is the final Blueprint for a BlackOrc,
 * 			 the toughest of all the GreenSkin races
 */
public class Animal extends LifeForm
{
	private static int animalCount = 0;
	
		 // What it is waring inc Armour 
	protected Outfit itsOutfit = new Outfit();


	public Animal(String theName,
				  char theGender,
				  String theRace,
				  int theAge,
				  int theMv,int theWs,int theBs,
				  int theSt,int theTu,int theHp,
				  int theIni,int theAtt,int theLd)
	{
		super(theName,theGender,theRace,theAge, 
			  theMv,theWs,theBs,theSt,theTu, 
			  theHp,theIni,theAtt,theLd);
			  
		animalCount++;
	}
	
	public static void reduceAnimalCount()
	{
		animalCount--;
	}
	
	public static int getAnimalCount()
	{
		return animalCount;
	}
    
	public String toString()
	{
		String tempString = "u have created an animal";
		return tempString;
	}
	
	public void death()
	{
		Animal.reduceAnimalCount();
		LifeForm.reduceLfCount();
	}	
}