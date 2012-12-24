package Ian.Game.GameEngine;

/**
 * constants is just a class to keep all the constants for the game
 * There will never be an instance of this class 
 * but other classes will access this to find all the basic constant 
 * attributes in the game such as the number of Stats held by any character
 * Mainly used for creating arrays and editing the basic elements which are
 * vital to the games funcioning.
 *
 * @auther Ian Wallis
 * @version created 06/01/2000
 */

public class Const
{
	public static final int RACE_NUM = 13; // number of races
	public static final int RACE_TYPE = 3; // normal, chaos, magician

	public static final int STAT_COUNT = 14; // Number of Stats
	
	public static final int LIMB_COUNT = 6; // Number of limbs on a humonoid
	public static final int PART_COUNT = 11; // number of parts on any limb
	public static final int INJURY_COUNT = 5;// max number of injuries
	
	public static final int DUNNO = 10; // I DON'T KNOW WHAT THIS IS FOR
	
}