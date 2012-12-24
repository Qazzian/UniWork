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
public class Card implements Listable{

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
	
	// ////////////////// //
    // Instance Variables //
    // ////////////////// //
	
	/** The face value of the card. **/
	protected int value;
	
	/** The suite that the Card belongs to. **/
	protected char suite;
	
	/** 
	 * The colour of the card. 
	 * NB. I have used the British spelling of colour 
	 */
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
	public int getValue(){
		return value;
	}
	
	/**
	 * Returns a String representation of the Value of the Card.
	 * @return A String representation of the value of the Card.
	 */
	public String getValueString(){
		String tmpS = "";
		switch(value){
			case 0:
				tmpS = "";
				break;
			case 1:
				tmpS = "A ";
				break;
			case 2: case 3: case 4: case 5:  
			case 6: case 7: case 8: case 9: 		// for cases 2 to 9 print the value with a space avfter it
				tmpS = Integer.toString(value) + " ";
				break;
			case 10:
				tmpS = "10";
				break;
			case 11:
				tmpS = "J ";
				break;
			case 12:
				tmpS = "Q ";
				break;
			case 13:
				tmpS = "K ";
				break;
		}
		return tmpS;
	}

	/**
	 * Sets/changes the Suite of the card and the colour to match.
	 * c = CLUB, d = DIAMOND, h = HEART, s = SPADE, J = JOKER.
	 * Either us these char's or the statics defined in this class.
	 * @param char s The suite code for the card.
	 * @exception SuiteInvalidException if the given character does 
	 * not corrispond with any of the set suite codes.
	 */
	public void setSuite(char s)throws SuiteInvalidException{
		switch(s){
			case CLUB:
				suite = s;
				colour = BLACK;
				break;
			case DIAMOND:
				suite = s;
				colour = RED;
				break;
			case HEART:
				suite = s;
				colour = RED;
				break;
			case SPADE:
				suite = s;
				colour = BLACK;
				break;
			case JOKER:
				suite = s;
				value = MIN_VALUE;
				colour = JOKER;
				break;
			default:
				throw new SuiteInvalidException(s +"Is not a valid Suite code!");
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
	 * Returns a String representation of the Suite.
	 * @return String the Name of the Suite
	 */
	public String getSuiteString(){
		String tmpS = "";
		switch(suite){
			case CLUB:
				tmpS += "Clubs.";
				break;
			case DIAMOND:
				tmpS += "Diamonds.";
				break;
			case HEART:
				tmpS += "Hearts.";
				break;
			case JOKER:
				tmpS = "Joker";
				break;
			case SPADE:
				tmpS += "Spades.";
				break;
			default:
				break;
		}
		return tmpS;
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
	 * This method is only invoked when this card is tested against 
	 * another Listable Object which isn't a Card. Therefor will 
	 * always return false.
	 * @return true If the cards are the same value and suite.
	 * @return false If the cards are not equale in this way.
	 */
	public boolean isEqual(Listable other){
		return false; 
	}
	
	/**
	 * Tests to see if This card is equal in value and suite to the given Card.
	 * This replaces the isEqual in the Listable interface.
	 * @param Card other The Card to test against.
	 * @return true If the Cards have equal values of suite and value.
	 * @return false If the Cards are not equal in both suite and value.
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
	 * Use 'c' to test colour, 's' for suite and 'v' for value.
	 * @param Card the card to test this one against.
	 * @param char The attribute to test against.
	 * @exception Exception if the test code is invalid.
	 */
	public boolean isEqual(Listable other,char att)throws Exception{
		return false;
	}
	
	/**
	 * Tests to see if this Card and the given card have the same value in the selected attribute.
	 * The character codes for selecting attributes are;
	 * <BR>		'c' for Colour, 's' for suite, 'v' for value.
	 * <BR>NB. The code is case sensative.
	 * @param Card other The card to test against.
	 * @param char att The selection code.
	 * @return true if the Cards are equal in the selected attribute.
	 * @exception Exception If the selected code does not do anything.
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
	 * @param Card other The card to tst this one against.
	 * @return true If this card has a value less than the other Card.
	 * @return false If this card has a value equal to or greater than the other Card.
	 */
	public boolean isLessThan(Card other){
		return (this.value < other.value);
	}
	
	/**
	 * Tests to see if this Card has a value which is greater than the value of the given Card
	 * @param Card other The Card to test this one against.
	 * @return true If this card has a value greater than the other Card.
	 * @return false If this card has a value equal to or less than the other Card.
	 */
	public boolean isGreaterThan(Card other){
		return (this.value > other.value);
	}
	
	/**
	 * Returns a string giving the value and suite of the card in the format vvs<BR>
	 * where vv = the Value of the Card, and s = the suite.
	 * @return String The value and suite of the Card.
	 */
	public String toString(){
		String tmpS = getValueString() + getSuite();
		return tmpS;
	}
	
	/**
	 * A quick test harness to assist with debugging.
	 */
	public static void main(String[] args)throws Exception{
		Card c1 = new Card(2,'C');
		System.out.println(c1.toString());
	
	}
}