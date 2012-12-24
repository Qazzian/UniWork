
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

/**
 * Class Pack is a collection of Cards using a arrayed List. It
 * contains methods for creating standard Packes, adding cards to a
 * Pack and shuffaling the Pack.
 *
 * @see Card
 * @see List
 */
public class Pack{
	// Attributes
	protected List allCards;
	
	// Constructors
	/**
	 * Creates a new Empty pack of cards
	 */
	public Pack(){
		
    }
	
	/**
	 * Creates a standard pack of cards with the specified number of
	 * Jokers in it
	 * @param int The number of jokers to put in the Pack
	 */
	public Pack(int jokerNum){
		this.freshPack(jokers);
	}
	
	// Methods
	
	public void freshPack(){
		this.freshPack(0);
	}
	
	public void freshPack(int jokerNum){
		allCards = new List(52+jokerNum);
		for(int i=0; i< //// continu after addCard Method compleat
	}

    public void shufflePack(){
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
											SuiteInvalidException{
		Card newCard = new Card(theValue,theSuit);
		allCards.addItem(newCard);
    }

    public void removeCard(){
    }

    public Iterator iterator(){
 	return allCards.iterator();
	}

	// Other

}