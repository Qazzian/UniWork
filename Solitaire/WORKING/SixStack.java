import java.io.*;

/**
 * SixStack is a text based Card Game
 */
public class SixStack{

	// /////////////// //
	// Class Constants //
	// /////////////// //
	
	/** This game is played with 0 JOKERS **/
	protected final static int NUM_OF_JOKERS = 0;
	
	/** The Number of small select Stacks to have **/
	protected final static int STACK_NUM = 6;
	
	/** The set size of the small select Stacks **/
	protected final static int SMALL_STACK_SIZE = 6;
	
	/** The size of the TurnPile **/
	protected final static int TURN_PILE_SIZE = 16;
	
	/** The size of the DiscardPile **/
	protected final static int DISCARD_PILE_SIZE = 52;
	
	// ////////////////// //
	// Instance Variables //
	// ////////////////// //
	
	/** The piles to select cards from **/
	protected Stack[] gameStacks;	
	
	/** This is the deck of cards **/
	protected Pack gamePack;
	
	/** Recieves input from the keyboard **/
	protected BufferedReader stdin =  new BufferedReader(new InputStreamReader(System.in));

	
	// /////////// //
	// Constructor //
	// /////////// //

	/**
	 * Creates a new instance of SixStack so that the game is ready to play. 
	 * Invokes and shuffles a new Pack of cards.
	 * Also calls <CODE>setUpGame()</CODE> & <CODE>dealGame()</CODE>.
	 */
	public SixStack()throws Exception{
		try{
		gamePack = new Pack(NUM_OF_JOKERS);
		gamePack.shufflePack();
		setUpGame();
		dealGame();
		}
		catch(Exception e){
			
		}
	}
	
	
	// /////// //
	// Methods //
	// /////// // 
	
	/**
	 * Creates the Stacks for the cards. Called by the Constructor.
	 */
	protected void setUpGame()throws IndexInvalidException,
								DealException,
								ListFullException,
								ListEmptyException{
			// Set up the Stacks
		gameStacks = new Stack[STACK_NUM];
		for(int i=0; i<STACK_NUM; i++){
			if(i==0){
				gameStacks[i] = new Stack(TURN_PILE_SIZE);
			}
			if( i == (STACK_NUM - 1) ){
				gameStacks[i] = new Stack(DISCARD_PILE_SIZE);
			}
			else{
				gameStacks[i]= new Stack(SMALL_STACK_SIZE);
			}
		}
	}
	
	/**
	 * Deals the cards to the six select stacks then to the turnover pile. 
	 * Also turns over the top card ready for play.
	 * @exception DealException If there is a Problem with the deal
	 */
	public void dealGame()throws DealException,
										  ListFullException,
										  ListEmptyException,
										  IndexInvalidException{
	 int whichStack = 0;
	 try{	
		for(int i=0; i< ((STACK_NUM-2) * SMALL_STACK_SIZE); i++){
			Card toDeal = gamePack.dealCard();
			whichStack = i % (STACK_NUM - 1);
			gameStacks[whichStack].push(toDeal);
		}
		whichStack = 0;
		for(int i=0; i<TURN_PILE_SIZE; i++){
			gameStacks[0].push(GamePack.dealCard());
		}
		whichStack = STACK_NUM;
		gameStacks[STACK_NUM].push(gameStacks[0].pop());
	 }catch(ListFullException e){
    	throw new DealException("Stack " + whichStack + " is full!");
	 }
	}
	
	
	
	/**
	 * Carries out the selected move if it is allowd. 
	 * Accepts an int for the Stack number. 
	 * 0 is the first stack in the array, 5 is the last. 6 is the turn pile
	 * Returns a boolean value to say weather the move was successfull or not.
	 * @param the number value of the move
	 */
	public void doUserMove(int stackNum)throws ListFullException,
												ListEmptyException{
		switch(stackNum){
			case 0:	case 1:	case 2: case 3:	case 4:	case 5: 			// As in cases 1-6 it will be the same
				Card selectedCard = (Card)selectPile[stackNum].peek();	// Get handle on top card of selected Stack
				int cardValue = selectedCard.getValue();				// Find the value fo that Card
				if(this.isValid(cardValue)){ 							// ie. if the move is allowd
					discardPile.push(this.selectPile[stackNum].pop());	// do the move
				}
				break;
			case 6:										// If the turnPile selected
				if(!turnPile.isEmpty()){				// Check that it isnot empty
					discardPile.push(turnPile.pop());	// move the top turn Card onto the discard
				}
				else{
					System.out.println(displayLayout()+ "There are no more cards to turn over");
				}
				break;
			default:
				break;
		}
	
	}
	
	/**
	 * Checks to see if the value of the chosen card is valid for the move.
	 * @param int theValue The value of the chosen Card.
	 * @return true if the move is valid.
	 * @return false if the move is valid.
	 */
	public boolean isValid(int theValue){
	 try{
		Card topCard = (Card)discardPile.peek();
		int topValue = topCard.getValue();
/*	 
	 catch(ListEmptyException e){
		System.out.println(e);
	 }*/
		if(theValue == (topValue-1)%13 ){
			return true;
		}
		if(theValue == (topValue+1)%13 ){
			return true;
		}
		
	 }catch(ListEmptyException e){System.out.println(e);}
	 return false;
	}
	
	/**
	 * This method returns a string value of the board layout.
	 * @return The layout of the board.
	 */
	public String displayLayout(){
		String tmpS = "\n\t\tGame board\n\n";
		for(int i=0; i<SMALL_STACK_NUM; i++){			// This for loop prints out each stack in turn, on a line
			tmpS += i + ".";								// Prints the selection number.
			if(!selectPile[i].isEmpty()){					// If the Stack is not empty
				int stackDepth = selectPile[i].depth();// Finds the Depth of the current stack
				for(int j=1; j<stackDepth; j++){			// this loop prints out each hidden card in the Stack
					tmpS += "\t XXX";							// Prints XXX if there are hidden cards
				}
			 try{
				tmpS += "\t" + selectPile[i].peek().toString() + "\n";		// Prints the top card
			 }catch(ListEmptyException e){System.out.println(e);}	// This should never need to be caught
			} // end if(stack not empty)
			else{ tmpS += "OOO"; 	// If the Stack is Empty
			}
		} // end for loop
		if(! turnPile.isEmpty()){ 	// If the Turn Pile is not Empty
			tmpS += "\n\t\tXXX"; 		// Print the Hidden cards
		}
		else{ tmpS += "\n\t\tOOO";}	// If it is Empty Print OOO
		 try{
			tmpS += "\t\t" + discardPile.peek().toString(); 		 // Print the card on top of the discardPile
		 }catch(ListEmptyException e){System.out.println(e);} 		 // This should never need to be caught
		 
		tmpS += "\n";  				// Just to round off the display
		return tmpS;					// Return the Display as a String
	}
	
//		catch(ValueOutOfRangeException e){System.out.println(e);} // This should never need to be caught	
	
	public static void main(String[] args) throws Exception{
		SixStack myGame = new SixStack();
		System.out.println(myGame.displayLayout());
	}
}