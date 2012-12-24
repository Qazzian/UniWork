package Ian.Game.GameEngine;

/**
 * @auther: Ian Wallis	email: ifw9@aber.ac.uk
 * @version: last revised 29/12/1999
 * 
 * @purpose: This is the basic game board which holds all the pieces
 * 			 and information for the game
 */

public class Gameboard
{
	private Position[][] thePositions;
	private int allX;
	private int allY;
	
	public Gameboard(int theXNum, int theYNum)
	{
		allX = theXNum;
		allY = theYNum;
		thePositions = new Position[allX][allY];
		for (int i=0; i<allX; i++)
		{
			for (int j=0; j<allY; j++)
			{
				thePositions[i][j] = new Position();
			}
		}
	}
	
	public void addPosition(Position thePosition)
	{
		
	}
}