/** 
 * @author Ian Wallis</A>
 * @email: <A HREF="mailTo:ifw9@aber.ac.uk>ifw9@aber.ac.uk</A>
 * or <A HREF="mailTo:ifw9@aber.ac.uk>Qaz_Wallis@yahoo.com</A>
 * @version last updated 20 November 2000
 * @since jdk 1.3
 *
 * This code is the property of me, Ian Wallis.  You are free to use 
 * this code in your own projects but I ask that you aknowledge my  
 * part in them and that you include my original code, or the means 
 * to get it, with your distribution. I accept no responsibility for  
 * any damage caused in any way by my code and I assure you that if it 
 * does cause damage then it not intensional.  I would also be 
 * gratefull for any comments, suggestions or bug reports that you 
 * have regarding my code. <BR> Thank you.
 */

import java.util.Random;

/**
 * Class Pack is a collection of Cards using a arrayed List. 
 * It contains methods for creating standard Packes, adding cards to a 
 * Pack and shuffaling the Pack.
 *
 * @see Card
 * @see List
 */
public class Pack{
	
	// ////////// //
	// Attributes //
	// ////////// //
	
	/** The List of the Cards in the Pack **/
	protected List allCards;
	
	/** The current position of the dealing **/
	protected int dealPos;
	
	/** This is the Number seed to use with class Random. 
		Using null will set the seed to the current time. **/
	protected long numberSeed = 1L;
	
	/** The Random number generator **/
	protected Random randomizer;
	
	// //////////// //
	// Constructors //
	// //////////// //
	
	/**
	 * Creates a new Empty pack of cards
	 */
	public Pack(){
 		allCards = new List();
	}
	
	/**
	 * Creates a standard pack of cards with the specified number of Jokers in it.
	 * @param int The number of jokers to put in the Pack.
	 */
	public Pack(int jokerNum)throws ListFullException,
                                    ValueOutOfRangeException,
                                    Exception{
		this.freshPack(jokerNum);
	}
	
	// /////// //
	// Methods //
	// /////// //
	
	/**
	 * Refreshes the Pack with an orderd set of all the standard Cards
	 * but without the Jokers. Simply calls <CODE>freshPack(0)</CODE>
	 * @exception ListFullException Only occurs on an Internal error.
	 */
	public void freshPack()throws ListFullException,
                                  ValueOutOfRangeException,
                                  Exception{
		this.freshPack(0);
	}
	
	/**
	 * Refreshes the Pack with an orders set of all the standard Cards with the number of Jokers specified.
	 * @param int The number of jokers to add to the Pack.
	 * @exception ListFullException Only occurs on an Internal error.
	 */
	public void freshPack(int jokerNum)throws ListFullException,
											  ValueOutOfRangeException,
											  Exception{
	  	int numOfCards = 52 + jokerNum;
		allCards = new List(numOfCards);
		for(int i=0; i<4; i++){
			char theSuite = 'a';
			switch(i){	// Select the suite for the cards
				case 0:
					theSuite = Card.CLUB;
					break;
				case 1:
					theSuite = Card.DIAMOND;
					break;
				case 2:
					theSuite = Card.HEART;
					break;
				case 3:
					theSuite = Card.SPADE;
					break;
			} // end of switch
			for(int theValue=1; theValue<14; theValue++){  // Select the value of the Card
			  try{
				Card newCard = new Card(theValue,theSuite);
				this.addCard(newCard);
			  }
			  catch(ValueOutOfRangeException e){
			  	
			  }
			} // end Value loop
		} // end Suite loop
		if(jokerNum != 0){ // ie If there are some jokers to add
			for(int i=0; i<jokerNum; i++){
				Card newCard = new Card(0,Card.JOKER);
				this.addCard(newCard);
 			}
		}
	  dealPos = 0;
	}

	/**
	 * This rearranges the cards in the Pack so that they are in a 
	 * random order. does this by calling <CODE>allCards.removeAt(int)
	 * </CODE> and adding the removed card to a new List.  
	 * Uses <CODE>randomizer</CODE> to pick the index values. 
	 * Also resets the dealPos so that dealing can start again.
	 */
	public void shufflePack()throws ListEmptyException,
									ListFullException,
									IndexInvalidException{
		if(numberSeed != 0){ // Set the Randomizer and the Seed
			randomizer = new Random(numberSeed);
		}
		else{
			randomizer = new Random();
		}
		int NumOfCards = allCards.getLength(); // Find Num of cards in the Pack
		List shuffledList = new List(NumOfCards); // List to put shuffled cards in
		for(int i=NumOfCards; i>0; i--){
		    int theIndex = randomizer.nextInt(i);
			shuffledList.addItem(allCards.removeItemAt(theIndex));
		} // End of for loop
		allCards = shuffledList;
		dealPos = 0;
	}
	
	/**
	 * Deals a card and increments the dealPos.
	 * @return The next Card in the List to Deal.
	 * @exception DealException If there is no more Cards to deal.
	 * @exception ListEmptyException Only if there is a coding error.
	 * @exception IndexInvalidException Only if there is a coding error.
	 */
	public Card dealCard()throws DealException{
		if(dealPos > allCards.getLength()){
			throw new DealException("All the cards have been dealt!");
		}
	  try{
		Card dealtCard = (Card)allCards.getItemAt(dealPos++);
	  }catch(IndexInvalidException e){
	  		System.out.println("Internal error with dealing!\n" + e);
			throw new DealException("All the cards have been dealt!");
	  }catch(ListEmptyException e){
	  		System.out.println("There are no cards to deal!\n" + e);
			throw new DealException("All the cards have been dealt!");
	  }
		return dealtCard;
	}

	/**
	 * Adds a card to the Pack with the given value and suite.
	 * @param int The vale of the card.
	 * @param char The suite of the card.
	 * @exception ValueOutOfRangeException If the value is out of
	 * range.
	 * @exception SuiteInvalidException If the Suite code is not
	 * @see Card for valid values.
	 */
	public void addCard(int theValue,char theSuit)
									throws ValueOutOfRangeException,
											SuiteInvalidException,
											ListFullException
	{
		Card newCard = new Card(theValue,theSuit);
		try{ // This is just so the Exception has a meaningfull message
		  allCards.addItem((Listable)newCard);
		}
		catch(ListFullException e){
		  throw new ListFullException
			("The Pack of cards is Full! cannot add any more!");
		}
	}
	
	public void addCard(Card theCard)throws ListFullException{
		allCards.addItem(theCard);
	}

	/**
	 * Returns the card at the given index.
	 * @param int the Index of the Card.
	 * @return Card The Card at the given index.
	 */
	public Card getCardAt(int theIndex)throws ListEmptyException,
											  IndexInvalidException{
		Card tmpCard = (Card)allCards.getItemAt(theIndex);
		return tmpCard;
	}
	
	/**
	 * Removes and returns the Card at the given index.
	 * @param int The index of the card to remove.
	 * @return Card The removed Card at the given Index.
	 *
	public Card removeCardAt(int theIndex){
	}*/
	
	/**
	 * Sets the seed for randomizer. Use 0 if you want to use the 
	 * current time.
	 * @param long theSeed The number to set the Seed as.
	 */
	public void setNumberSeed(long theSeed){
		numberSeed = theSeed;
	}

	/**
	 * Returns the List of cards as an Iterator
	 * @return Iterator This pack as an iterater
	 */
	public Iterator iterator(){
 		return allCards.iterator();
	}

	public String toString(){
		String tmpS = "The pack contains " + allCards.getLength() + 
					" cards.\n";
		for(int i=0; i<allCards.getLength(); i++){
		  try{
			tmpS += getCardAt(i).toString();
		  }
		  catch(Exception e){
		  	System.out.println("An Exception was caught in the " +
					"toString method of class Pack!\n" + e);
		  }
		}
		return tmpS;
	}
	

	public static void main(String[] args) throws Exception{
		Pack myPack = new Pack(0);
		System.out.println(myPack.toString());
		myPack.setNumberSeed(1L);
		myPack.shufflePack();
		System.out.println(myPack.toString());
	}
}