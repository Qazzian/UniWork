
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
 * Class Card holds attributes for suite, value and color can be 
 * used for creating card games. Can also represent a Joker.
 * @see Pack
 */
public class Card //implements Sortable (This will only work with all
{				// the apropriate interfaces imported from my package)
	// /////////////// //
    // Class Constants //
    // /////////////// //
	
	/** The minimum value of any card **/
	protected final static int MIN_VALUE = 0; // 0 = Joker
	/** The maximum value of any card **/
	protected final static int MAX_VALUE = 13; //13 = King

	/** The character code for the Suite of Clubs = C **/
	protected final static char CLUB 	= 'C';
	/** The character code for the Suite of Diamonds = D **/
	protected final static char DIAMOND	= 'D';
	/** The character code for the Suite of Hearts = H **/
	protected final static char HEART 	= 'H';
	/** The character code for the Suite of Spades = S **/
	protected final static char SPADE 	= 'S';
	/** The character code for Jokers = J (also used in colour) **/
	protected final static char JOKER	= 'J';

	/** The character code for the colour Black = B **/
	protected final static char BLACK	= 'B';
	/** The character code for the colour Red = R **/
	protected final static char RED 	= 'R';
	
	// ///////// //
    // Variables //
    // ///////// //
	
	/** The face value of the card. **/
	protected int value;
	
	/** The suite that the Card belongs to. **/
	protected char suite;
	
	/** The colour of the card. **/
	protected char colour;

    /** The Name of the .gif file which represeents this card **/
    protected String fileName;
	
	
    // //////////// //
    // Constructors //
    // //////////// //
	
	/**
	 * Creates a blank card
	 */
	public Card(){
	}
	
	/**
	 * Creates a card with the given value.
	 * @param int vl The Value to be given to the Card.
	 * @exception ValueOutOfRangeException If the value is out of
	 * range.
	 */
	public Card(int vl)throws ValueOutOfRangeException{
		this.setValue(vl);
	}
	
	/**
	 * Creates a card with the given Suite.
	 * @param char su The Suite code to be given to the Card.
	 * @exception SuiteInvalidException If the Suite code is not 
	 * valid
	 */
	public Card(char su)throws 	SuiteInvalidException{
		this.setSuite(su);
	}
	
	/**
	 * Creates a card of given value and suite.
	 * @parm int vl The Value to be given to the Card.
	 * @parm char su The Suite code to be given to the Card.
	 * @exception ValueOutOfRangeException If the value is out of
	 * range.
	 * @exception SuiteInvalidException If the Suite code is not 
	 */
	public Card(int vl,char su)throws ValueOutOfRangeException,
									SuiteInvalidException{
		this.setValue(vl);
		this.setSuite(su);
	}

    // /////// //
    // Methods //
    // /////// //

	/**
	 * Sets/changes the face Value of the card.
	 * @parm int vl The Value to be given to the Card.
	 * @exception ValueOutOfRangeException If the value is out of
	 * range (ie. If below MIN_VALUE or above MAX_VALUE)
	 */
	public void setValue(int vl)throws ValueOutOfRangeException{
		if( (vl > MAX_VALUE) || (vl < MIN_VALUE) ){
			throw new ValueOutOfRangeException("Cards can not have "+
					"a value of " + vl + "!");
		}
		value = vl;
	}
	
	/**
	 * Returns the face value of the card.
	 * @return int The value of the card.
	 */
	public int getvalue(){
		return value;
	}

	/**
	 * Sets/changes the Suite of the card and the colour to match.
	 * c = CLUB, d = DIAMOND, h = HEART, s = SPADE, J = JOKER.
	 * Either us these char's or the statics defined in this class.
	 * @param char s The suite code for the card.
	 * @exception SuiteInvalidException if the given character does 
	 * not corrispond with any of the set suite codes.
	 */
	public void setSuite(char s)throws 	SuiteInvalidException{
		if( (s!=CLUB) | (s!=DIAMOND) | (s!=HEART) | (s!=SPADE) | 
					(s!=JOKER) ){
			throw new SuiteInvalidException();
		}
		suite = s;
		if(s == JOKER){
			value = MIN_VALUE;
			colour = JOKER;
		}
		if( (s == CLUB) || (s == SPADE) ){
			colour = BLACK;
		}
		else{
			colour = RED;
		}
	}
	
	/**
	 * Returns the Suite code of the card.
	 * Check against the codes defined above.
	 * @return char The suite code for the card
	 */
	public char getSuite(){
		return suite;
	}
	
	/**
	 * Returns the colour code for this card.
	 * Check against the codes defined above.
	 * @return the colour of the card
	 */
	public char getColour(){
		return colour;
	}
	/**
	 * Tests to see if two cards are equal in value and suite.
	 * @return true If the cards are the same value and suite.
	 * @return false If the cards are not equale in this way.
	 */
	public boolean isEqual(Card other){
		if(this.value != other.value){
			return false;
		}
		if(this.suite != other.suite){
			return false;
		}
		return true;
	}
	
	/**
	 * This uses a character switch to test equality of diffrent 
	 * attributes of the card. For Instance if you wanted to see if 
	 * the colours of two cards were equal the second veriable would 
	 * be 'c'.
	 * Use 'c' to test colour, s for suite and v for value.
	 * @param Card the card to test this one against.
	 * @parm char The type of test to preform.
	 * @exception Exception if the test code is invalid.
	 */
	public boolean isEqual(Card other,char att)throws Exception{
		switch(att){
			case 'c': // colour
				return (this.colour == other.colour);
			case 's': // suite
				return (this.suite == other.suite);
			case 'v': // value
				return (this.value == other.value);
			default: // if an unsupported char is used
				throw new Exception
					("That is not a valid test code!");
 		}
	}
	
	/**
	 * Tests to see if the value of this card is less than the value 
	 * of the other card.
	 */
	public boolean isLessThan(Card other){
		return (this.value < other.value);
	}
	
	public boolean isGreaterThan(Card other){
		return (this.value > other.value);
	}
	
	public String toString(){
		String tmpS = "The " + value;
		switch(suite){
			case 'c':
				tmpS += " of Clubs.";
				break;
			case 'd':
				tmpS += " of Diamonds.";
				break;
			case 'h':
				tmpS += " of Hearts.";
				break;
			case 'j':
				tmpS = "Joker";
				break;
			case 's':
				tmpS += " of Spades.";
				break;
			default:
				break;
		}
		return tmpS;
	}

}