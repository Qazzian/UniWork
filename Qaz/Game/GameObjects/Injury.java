package Ian.Game.GameObjects;

/**
 * Injury represents a serious wound which hinders a LifeForm 
 * untill it is healed
 */

public class Injury extends Item
{
	private Sting name;
	private String description;
	
	private LifeForm victim; // who it has affected
	private int area; // which limb of the body
	private int point; // which part of the limb
	//effect
	
	public Injury(String theName, String theDescription)
	{
		name = theName;
		description = theDescription;
	}
	
	public void setVictim(LifeForm theLF)
	{
		victim = theLF;
	}
	
	//setArea
	//setPoint
	
	public void apply()
	{
		
	}
	
	public void heal()
	{
	
	}
}