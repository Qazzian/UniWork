import java.io.*;

/**
 * SixStack is a text based Card Game
 */
public class SixStack{

	// /////////////// //
	// Class Constants //
	// /////////////// //
	
	/** This game is played with 0 JOKERS **/
	protected final static int NUM_OF_JOKERS = 2;
	
	/** The total number of Stacks in the game **/
	protected final static int STACK_NUM = 8;
	
	/** The Number of small select Stacks to have **/
	protected final static int SMALL_STACK_NUM = 6;
	
	/** The set size of the small select Stacks **/
	protected final static int SMALL_STACK_SIZE = 6;
	
	/** The size of the TurnPile **/
	protected final static int TURN_PILE_SIZE = 18;
	
	/** The size of the DiscardPile **/
	protected final static int DISCARD_PILE_SIZE = 54;
	
	/** The Number seed to use for shuffling **/
	protected final static long RANDOMIZER_SEED = 0L;
	
	// ////////////////// //
	// Instance Variables //
	// ////////////////// //
	
	/** The Game Stacks which contains all the cards **/
	protected Stack[] selectPile;	
	
	/** This is the Pack of cards to use **/
	protected Pack GamePack;
	

	// /////////// //
	// Constructor //
	// /////////// //

	/**
	 * Creates a new instance of SixStack. Creates the Pack of cards and sets up a new game.
	 */
	public SixStack()throws Exception{
		try{
		GamePack = new Pack(NUM_OF_JOKERS);
		GamePack.setNumberSeed(RANDOMIZER_SEED);
		}
		catch(Exception e){
			throw new Exception("Prob with Constructor! new pack\n" + e);
		}
		try{
		newGame();
		}
		catch(Exception e){
			throw new Exception("Prob with Constructor! new game \n" + e);
		}
//		playGame();
		
	}
	
	
	// /////// //
	// Methods //
	// /////// // 
	
	/**
	 * Clears all the Stacks, shuffles the Pack of cards and calls dealGame().
	 * 
	 */
	public void newGame()throws DealException{
		selectPile = new Stack[STACK_NUM];
		for(int i=1; i<=SMALL_STACK_NUM; i++){ 			// The small stacks
		 try{	
			selectPile[i]= new Stack(SMALL_STACK_SIZE);	
		 }catch(IndexInvalidException e){					//	This Exception should never be thrown, But in case...
		  	System.out.println("Internal Problem with SMALL_STACK_NUM!\n" + e); 
		 }
		}
		try{
			selectPile[0] = new Stack(TURN_PILE_SIZE); 	// The turn pile
			selectPile[ (STACK_NUM-1) ] = new Stack(DISCARD_PILE_SIZE); // the discard pile
		}catch(IndexInvalidException e){ 					// This Exception should never be thrown, but in case...
			System.out.println("Problem with the stack Size!"); 
		}
		GamePack.shufflePack();  // Implement this later when all else works
		dealGame();
	}
	
	/**
	 * Deals the cards to the six select stacks then to the discard pile.
	 * @exception DealException If there is a Problem with the deal
	 */
	public void dealGame()throws DealException{
		for(int i=1; i<=SMALL_STACK_SIZE; i++){ // increment through the levels
			for(int j=1; j<=SMALL_STACK_NUM; j++){ // increment through the stacks
				Card toDeal = GamePack.dealCard(); // Get next card to deal
			 try{
			 	selectPile[j].push(toDeal);			// Put the card on the next place
			 }catch(ListFullException e){
			 	throw new DealException("Cannot deal a card to that Pile!\n" + e); 
			 }
			}// go to next stack
		}// go to next level
		for(int i=0; i<TURN_PILE_SIZE; i++){
		  try{
			selectPile[0].push(GamePack.dealCard());
		  }catch(Exception e){
		  	throw new DealException("Cannot deal to the turn Pile!" + e);
		  }
		}
		try{
		selectPile[STACK_NUM - 1].push(selectPile[0].pop());
		}catch(ListException e){
			System.out.println("Cannot turn over first card!");
		}
	}
	
	
	
	/**
	 * Carries out the selected move if it is allowd. 
	 * Accepts an int for the Stack number. 
	 * 0 is the first stack in the array, 5 is the last. 6 is the turn pile
	 * Returns a boolean value to say weather the move was successfull or not.
	 * @param int stackNum the index of the stack to take the card from.
	 * @return boolean If the move was succesfull or not.
	 */
	public boolean doUserMove(int stackNum){
		boolean success = false;
	 try{
		switch(stackNum){
			case 1:	case 2: case 3:	case 4:	case 5:	case 6: 			// As in cases 1-6 it will be the same
				if(selectPile[stackNum].isEmpty()){
					success = false;
					break;
				}
				Card selectedCard = (Card)selectPile[stackNum].peek();	// Get handle on top card of selected Stack
				int cardValue = selectedCard.getValue();				// Find the value fo that Card
				if(this.isValid(cardValue)){ 							// ie. if the move is allowd
					selectPile[STACK_NUM-1].push(this.selectPile[stackNum].pop());	// do the move
					success = true;
				}
				else success = false;
				break;
			case 0:										// If the turnPile selected
				if(!selectPile[0].isEmpty()){				// Check that it isnot empty
					selectPile[STACK_NUM-1].push(selectPile[0].pop());	// move the top turn Card onto the discard
					success = true;
				}
				else{
					success = false;
				}
				break;
			default:
				success = false;
		}
	 }catch(ListException e){
	 	success = false;
	 }
	 return success;
	}
	
	/**
	 * Checks to see if the value of the chosen card is valid for the move.
	 * @param int theValue The value of the chosen Card.
	 * @return true if the move is valid.
	 * @return false if the move is valid.
	 */
	public boolean isValid(int theValue){
	 try{
		if(theValue == 0){ // ie If the card is a joker
			return true;
		}
		theValue = theValue%13;
		Card topCard = (Card)selectPile[STACK_NUM-1].peek(); // the card at the top of the discard pile
		int topValue = topCard.getValue();		// The value of the top card on discard pile
		if(topValue == 0){ // ie The top of the Discard pile is a joker
			return true;
		}
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
		String tmpS = "\n\tSix Stack v1.0\t\t\t(c)Ian Wallis of UWA\n";
		for(int i=1; i <= SMALL_STACK_NUM; i++){		// This for loop prints out each stack in turn, on a line
			tmpS += "\n" + Integer.toString(i) + ".";			
			if(selectPile[i].isEmpty()){ 					// If the Stack is Empty
				tmpS += " ---\n"; 			
			} 				
			else{														// If the Stack is empty
				int stackDepth = selectPile[i].depth();	// Finds the Depth of the current stack
				for(int j=1; j<stackDepth; j++){				// this loop prints out each hidden card in the Stack
					tmpS += " XXX";								// Prints XXX if there are hidden cards
				}
			 try{
				tmpS += " " + selectPile[i].peek().toString() + "\n";	// Prints the top card
			 }catch(ListEmptyException e){System.out.println(e);}			// This should never need to be caught
			}
		} // end for loop
		if(selectPile[0].isEmpty()){ 		// If the turn pile is empty
			tmpS += "\n\t0. ---";				// Show it is empty
		}
		else{ 									// If the Turn Pile is not Empty
			tmpS += "\n\t0. XXX"; 			// Show there are Hidden cards
		}		
	 try{
		tmpS += "\t\t" + selectPile[STACK_NUM-1].peek().toString();	// Print the card on top of the discardPile
	 }catch(ListEmptyException e){
		 tmpS += "\t\t";					// If the Discard Pile is Empty
	 }
		return tmpS;			// Return the Display as a String
	}
	
//		catch(ValueOutOfRangeException e){System.out.println(e);} // This should never need to be caught	
	
	public static void main(String[] args)throws Exception{
		SixStack myGame = new SixStack();
		BufferedReader stdin =  new BufferedReader(new InputStreamReader(System.in));
		int theMove = 1;
		while(theMove != -1){
         System.out.println(myGame.displayLayout());
         System.out.println("Select stack to move from (-1 to quit, -2 for new Game");
         String tmpS = stdin.readLine();
         theMove = Integer.parseInt(tmpS);
			if(theMove == -2){
				myGame.newGame();
			}
			else{
         	myGame.doUserMove(theMove);
			}
		}
	}
}