/**
 * This class handles the desision making for the computer player in the game 
 * TicTacToe. t use this method You first have to ensure that the computer and human 
 * player numbers have been set. The maximum depth is an optional setting but is
 * recommended so that the Computers moves will not take an incredably long time to
 * calculate. Once these values have been set a call to <code>findBestMove(TttBoard,
 * int)</code> will find the best move for the the player represented by the
 * <code>int</code> on the <code>TttBoard</code> which is also given to the method.
 *
 * @auther Ian Wallis
 * @copyright Ian Wallis Dept Comp Sci University of Wales, Aberystwyth
 */

public class Minimax {

	// /////////////////// //
   // Instance variables. //
   // /////////////////// //

	/* The integer value representing the computer player. */
	private int compPlayer;
	/* The integer value representing the human player. */
	private int otherPlayer;
	/* The maximum depth for the Minimax method to search through the tree. */
	private int maxDepth = 4;
	/* The current depth that the search has got to. */
	private int currDepth = 0;
	
	// ///////////// //
   // Constructors. //
   // ///////////// //
	
	/**
	 * Creates a new instance of the minimax class keeping all values 
	 * at there default values. both player code numbers will be null and 
	 * maxDepth is set to 4 by default.
	 */
	public Minimax(){
	}
	
	/**
	 * Creates a new Instance of Minimax setting the player code numbers 
	 * based on the given integer representing the computer player. Leaves 
	 * the depth settings on their default values.
	 * @param theCompPlayer		The integer representing the computers 
	 * 								player number.
	 */
	public Minimax(int theCompPlayer) throws Exception {
		this.setPlayers(theCompPlayer);
	}
	
	/**
	 * Creates a new instance of Minimax. Sets the player codes based on
	 * the given player code representing the computer player and sets 
	 * the Max depth of the minimax search.
	 * @param theCompPlayer		The integer representing the computers 
	 * 								player number.
	 * @param theMaxDepth		The number of levels to search in the minimax.
	 *									(Time complexity increses by theMaxDepth 
	 *									factorial)
	 */
	public Minimax(int theCompPlayer, int theMaxDepth) throws Exception {
		this.setPlayers(theCompPlayer);
		this.setMaxDepth(theMaxDepth);
	}
	
	// ////////////////////// //
   // Read/Write properties. //
   // ////////////////////// //
	
	/**
	 * Sets the maximum depth for the minimax to search. 
	 * The time complexity of the search increases by 9! - (9-theDepth)!
	 * <code>maxDepth</code> is initially set to 4.
	 * @param theDepth 		The number of Node generations to search through.
	 */
	public void setMaxDepth(int theDepth) {
		maxDepth = theDepth;
	}
	
	/**
	 * Returns the maximum depth that the minimax will search to.
	 * @return 		The number of Node generations that will be 
	 *					searched through.
	 */
	public int getMaxDepth() {
		return maxDepth;
	}
	
	/**
	 * Sets the player number codes based on the given number representing 
	 * the computer player. The Integer must be a 1 or a 2 in order for 
	 * this class to work.
	 * @param theCompPlayer		The Integer representing the computer player.
	 * @exception Exception 	If the number given is not a 1 or a 2.
	 */
	public void setPlayers(int theCompPlayer) throws Exception {
		switch(theCompPlayer) {
			case 1:
				compPlayer = 1;
				otherPlayer = 2;
				break;
			case 2:
				compPlayer = 2;
				otherPlayer = 1;
				break;
			default:
				throw new Exception("That player number is invalid");
		}
	}
	
	/**
	 * Returns the code number representing the computer player
	 * @return int 		 The Number code for the computer player
	 */
	public int getCoputerPlayer() {
		return compPlayer;
	}

	/**
	 * Returns the code number representing the human player
	 * @return int 		 The Number code for the human player
	 */	
	public int getHumanPlayer() {
		return otherPlayer;
	}
	
	// //////// //
   // Methods. //
   // //////// //
	
	/**
	 * Draws all the possible children of the given TttNode. 
	 * Returns the given Node with all the children drawn and the 
	 * best moves calculated. This is a recursive function, calling 
	 * itself to work out the children of children... etc. 
	 * Uses a depth first strategy.
	 * 
	 * @param theNode				The node to draw all the children from.
	 * @return 						The cooridinates of the best move to take as an 
	 * 								array of an integer in the form {h,w} where h = the
 	 * 								height and w = the width.
	 * @exception Exception 	If there are problems with the player numbers. 
	 * 								Print the stack for more details.
	 */
	public int[] getBestMove(TttBoard theBoard,int nextPlayer) throws Exception {
		// First, keep account of how deep we are getting.
		currDepth++;
		// Now create some temperary variables.
		// The parent Node with the move we have already looked at
		TttNode pNode = new TttNode(new TttBoard(theBoard.getLayout()));
		pNode.getData().setPlayer(theBoard.getPlayer());
		// The player whos move we are looking at
		int player = nextPlayer;
		// The Node in evaluation. will become a child of pNode.
		TttNode cNode;
		// The node with the highest score found so far.
		TttNode highNode = new TttNode();
		// Set at imposible values so they will be changed at the first available move
		highNode.setScore(-11);  
		int[] highMove = new int[3];
		// The node with the lowest score found so far.
		TttNode lowNode = new TttNode();
		lowNode.setScore(11);	
		int[] lowMove = new int[3];
		
		// Start looping through all the positions in the board
		for(int h=0; h < TttConstants.BOARD_HEIGHT; h++) {
		 for(int w=0; w < TttConstants.BOARD_WIDTH; w++) {
 			System.out.println("Now checking move "+h+","+w+" at depth "+currDepth+".");

			// If the for-loops represent an empty position look at that move.
			if( pNode.getData().canMove(h,w) ) {
				System.out.println("Can move in "+h+","+w+" at depth "+currDepth+".");

				TttBoard tmpB = new TttBoard(pNode.getData().getLayout());
				tmpB.addMove(h,w,player);
				cNode = new TttNode(tmpB); // current or child node
				pNode.addChild(cNode);
				
				// If the current move we are looking at means someone will win then 
				// we need to set and return the score for that player winning
				if( tmpB.hasWon(player) ) {  
					if(player == compPlayer ) {
						cNode.getData().setValue(10);
					} else if(player == otherPlayer) {
						cNode.getData().setValue(-10);
					} else {
						throw new Exception("Invalid Player number!");
					}	
					System.out.println("Player "+player+" has won at this node!");
					System.out.println(tmpB.toString());
				} else {
					if(currDepth == maxDepth){ // if reached depth, evaluate current board
						cNode.getData().calcValue(compPlayer);
					}else {  // Evaluate the scores of its children
						int [] theMove = getBestMove(cNode.getData(),
														cNode.getData().getOtherPlayer(player));
						cNode.getData().addMove(theMove[0],theMove[1],player);
						cNode.setScore(theMove[2]);
					}
					System.out.println("Node has been evaluated to "+cNode.getScore());
					System.out.println(cNode.getData().toString());
				}

				// Having found the score of the current child node,
				// compare it with the other child node scores.
				// If high, the computer will choose the move.
				if( cNode.getData().getValue() > highNode.getData().getValue() ){ 
					highNode = cNode;
					highMove[0] = h; 
					highMove[1] = w;
					highMove[2] = cNode.getData().getValue();
					System.out.println(cNode.getData().toString());
				}	// If low, it is likly that human will choose move.
				if( cNode.getData().getValue() < lowNode.getData().getValue() ) { 
					lowNode = cNode;
					lowMove[0] = h;
					lowMove[1] = w;
					lowMove[2] = cNode.getData().getValue();
					System.out.println(cNode.getData().toString());
				}
			} // End if(canMove)
		}}	// End of positional for-loops

		// Now return the parent node with the score based on whos turn we were 
		// looking at.
		if(player == compPlayer) {
         pNode.setScore( highNode.getData().getValue() );
         System.out.println(highNode.getData().toString());
         currDepth--;
         return highMove;
		} else if(player == otherPlayer) {
			pNode.setScore( lowNode.getScore() );
			currDepth--;
			return lowMove;
		} else {
			throw new Exception("That Player number is invalid!");
		}
	
	} // End method
	
	// ///////////// //
   // Test methods. //
   // ///////////// //
	
	public static void main(String args[]){
		Minimax mm = new Minimax();
	}
	
} // End class